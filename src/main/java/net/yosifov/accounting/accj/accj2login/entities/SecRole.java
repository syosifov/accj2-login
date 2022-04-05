package net.yosifov.accounting.accj.accj2login.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sec_roles")
public class SecRole {

    @Id
    @GeneratedValue
    Long id;

    String name;

    @OneToMany
    Set<SecGrntAuthority> authorities;

    public SecRole() {
        authorities = new HashSet<>();
    }

    public SecRole(Long id, String name, Set<SecGrntAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.authorities = authorities;
    }

    public SecRole(String name, Set<SecGrntAuthority> authorities) {
        this.name = name;
        this.authorities = authorities;
    }

    public SecRole(String name) {
        this();
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SecGrntAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<SecGrntAuthority> authorities) {
        this.authorities = authorities;
    }
}
