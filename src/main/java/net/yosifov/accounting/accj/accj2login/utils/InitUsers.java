package net.yosifov.accounting.accj.accj2login.utils;


import net.yosifov.accounting.accj.accj2login.entities.SecApplicationUser;
import net.yosifov.accounting.accj.accj2login.entities.SecGrntAuthority;
import net.yosifov.accounting.accj.accj2login.entities.SecRole;
import net.yosifov.accounting.accj.accj2login.repositories.ApplicationUserRep;
import net.yosifov.accounting.accj.accj2login.repositories.GrntAutortyRep;
import net.yosifov.accounting.accj.accj2login.repositories.RolesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static net.yosifov.accounting.accj.accj2login.utils.C.ROLE_ACC;
import static net.yosifov.accounting.accj.accj2login.utils.C.ROLE_ADMIN;


@Component
public class InitUsers {

    @Autowired
    private RolesRep rolesRep;
    @Autowired
    private GrntAutortyRep grntAutortyRep;
    @Autowired
    private ApplicationUserRep applicationUserRep;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createRoles() {
        SecRole rStudent = new SecRole("ROLE_STUDENT");
        rolesRep.save(rStudent);

        SecRole rAdmin = new SecRole("ROLE_ADMIN");
        rolesRep.save(rAdmin);
    }

    public void createGrntAutorities() {
        SecGrntAuthority secGrntAuthority = new SecGrntAuthority("ROLE_STUDENT");
        grntAutortyRep.save(secGrntAuthority);
        secGrntAuthority = new SecGrntAuthority("ROLE_ADMIN");
        grntAutortyRep.save(secGrntAuthority);
    }

    public void createAppAdmin() {
        Set<SecGrntAuthority> secGrntAuthoritySet = createAuthorites(ROLE_ADMIN, ROLE_ACC);

                SecApplicationUser aUser = new SecApplicationUser("annasmith",
                passwordEncoder.encode("password"),
                        secGrntAuthoritySet,
                true);

        applicationUserRep.save(aUser);
    }

    public void createAppUser(String username, String password, String ...roles) {
        Set<SecGrntAuthority> secGrntAuthoritySet = createAuthorites(roles);
        SecApplicationUser aUser = new SecApplicationUser(username,
                passwordEncoder.encode(password),
                secGrntAuthoritySet,
                true);
        applicationUserRep.save(aUser);
    }

    private Set<SecGrntAuthority> createAuthorites(String ...args) {
        Set<SecGrntAuthority> secGrntAuthoritySet = new HashSet<>();
        for(String s: args) {
            SecGrntAuthority gAuthority = grntAutortyRep.findByAuthority(s);
            if(null == gAuthority) {
                gAuthority = new SecGrntAuthority(s);
                grntAutortyRep.save(gAuthority);
            }
            secGrntAuthoritySet.add(gAuthority);
        }
        return secGrntAuthoritySet;
    }


    public void adminLogin() {
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = new HashSet<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        Map<String,Object> principalDataMap = new HashMap<>();
        principalDataMap.put("username", "admin");

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principalDataMap,
                null,
                simpleGrantedAuthorities
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
