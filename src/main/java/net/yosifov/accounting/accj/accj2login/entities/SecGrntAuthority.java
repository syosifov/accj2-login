package net.yosifov.accounting.accj.accj2login.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SecGrntAuthority implements GrantedAuthority {

    @Id
    @GeneratedValue
    Long id;
    @Column(unique=true)
    String authority;

    public SecGrntAuthority() {
    }

    public SecGrntAuthority(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public SecGrntAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof SecGrntAuthority) {
            return authority.equals(((SecGrntAuthority) obj).authority);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.authority.hashCode();
    }

    @Override
    public String toString() {
        return this.authority;
    }

}
