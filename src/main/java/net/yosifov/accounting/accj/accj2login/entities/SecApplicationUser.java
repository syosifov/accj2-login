package net.yosifov.accounting.accj.accj2login.entities;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.Set;

@Entity
public class SecApplicationUser implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    private  String username;
    private  String password;
    @ManyToMany
    private  Set<SecGrntAuthority> grantedAuthorities;
    private  boolean isAccountNonExpired;
    private  boolean isAccountNonLocked;
    private  boolean isCredentialsNonExpired;
    private  boolean isEnabled;

    public SecApplicationUser() {
    }

    public SecApplicationUser(String username,
                              String password,
                              Set<SecGrntAuthority> grantedAuthorities,
                              boolean isEnabled ) {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = isEnabled;
    }

    public SecApplicationUser(String username,
                              String password,
                              Set<SecGrntAuthority> grantedAuthorities,
                              boolean isAccountNonExpired,
                              boolean isAccountNonLocked,
                              boolean isCredentialsNonExpired,
                              boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    public SecApplicationUser(Long id,
                              String username,
                              String password,
                              Set<SecGrntAuthority> grantedAuthorities,
                              boolean isAccountNonExpired,
                              boolean isAccountNonLocked,
                              boolean isCredentialsNonExpired,
                              boolean isEnabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
    }

    @Override
    public Collection<SecGrntAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(long l) {
        this.id = l;
    }
}
