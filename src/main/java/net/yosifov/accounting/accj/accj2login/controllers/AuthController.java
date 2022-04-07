package net.yosifov.accounting.accj.accj2login.controllers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import net.yosifov.accounting.accj.accj2login.auth.ApplicationUserDao;
import net.yosifov.accounting.accj.accj2login.auth.AuthRequest;
import net.yosifov.accounting.accj.accj2login.auth.AuthResp;
import net.yosifov.accounting.accj.accj2login.auth.RefreshRequest;
import net.yosifov.accounting.accj.accj2login.entities.SecApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SecretKey appSecretKey;

    @Value("${application.jwt.tokenExpirationAfterMinutes}")
    private Integer tokenExpirationAfterMinutes;
    @Autowired
    private @Qualifier("jpa")
    ApplicationUserDao applicationUserDao;
    @Autowired
    private SecretKey appKey;


//    public String auth(@RequestBody UsernameAndPasswordAuthenticationRequest authRequest,
//                       @RequestHeader Map<String, String> headers ) {
    @PostMapping("login")
    public AuthResp auth(@RequestBody AuthRequest authRequest) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                                                                                authRequest.getPassword()
                                                                                );

        Authentication authResult = authenticationManager.authenticate(authentication);
        SecApplicationUser applicationUser = (SecApplicationUser)authResult.getPrincipal();
        return tokens(applicationUser);
    }

    private AuthResp tokens(SecApplicationUser applicationUser) {
        Date issuedAt = new Date();
        Date expDate = new Date(issuedAt.getTime() + tokenExpirationAfterMinutes * 60 * 1000);
        String token = Jwts.builder()
                .setSubject(applicationUser.getUsername())
                .claim("authorities", applicationUser.getAuthorities())
                .claim("id", applicationUser.getId())
                .setIssuedAt(issuedAt)
                .setExpiration(expDate)
                .signWith(appSecretKey)
                .compact();

        String refreshToken = Jwts.builder()
                .setSubject(applicationUser.getUsername())
                .claim("refreshToken", true)
                .setIssuedAt(issuedAt)
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusYears(1)))
                .signWith(appSecretKey)
                .compact();


        return new AuthResp(token,
                            issuedAt.getTime(),
                            expDate.getTime(),
                            refreshToken,
                            issuedAt.toString(),
                            expDate.toString());
    }

    @PostMapping("refresh")
    public AuthResp refresh(@RequestBody RefreshRequest refreshRequest) {

        String refreshToken = refreshRequest.getRefreshToken();

        Jws<Claims> claimsJws;
        SecApplicationUser applicationUser;
        try {
            claimsJws = Jwts
                    .parserBuilder()
                    .setSigningKey(appSecretKey)
                    .build()
                    .parseClaimsJws(refreshToken);
            Claims body = claimsJws.getBody();
            String sub = body.get("sub",String.class);
            Boolean isRefreshToken = body.get("refreshToken", Boolean.class);
            long exp = body.get("exp", Long.class);
            if(null == isRefreshToken || !isRefreshToken) {
                throw new IllegalStateException(String.format("No refresh Token provided: %s", refreshToken));
            }
            if( exp * 1000 < new Date().getTime()) {
                throw new IllegalStateException(String.format("Token has expired: %s", refreshToken));
            }

            Optional<SecApplicationUser> oAppUser = applicationUserDao.selectApplicationUserByUsername(sub);
            if(oAppUser.isEmpty()) {
                throw new IllegalStateException("User not found!");
            }
            applicationUser = oAppUser.get();
            if(!applicationUser.isAccountNonExpired() ||
               !applicationUser.isAccountNonLocked() ||
               !applicationUser.isCredentialsNonExpired() ||
               !applicationUser.isEnabled() ) {
                throw new IllegalStateException(String.format("User's account problem: %s", sub));
            }
            System.out.println();
        } catch (JwtException e) {
            throw new IllegalStateException(String.format("Token cannot be trusted: %s", refreshToken));
        }
        return tokens(applicationUser);
    }

}
