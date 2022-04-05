package net.yosifov.accounting.accj.accj2login.auth;

import net.yosifov.accounting.accj.accj2login.entities.SecApplicationUser;
import net.yosifov.accounting.accj.accj2login.rep.ApplicationUserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("jpa")
public class JpaUserDao implements ApplicationUserDao {

    @Autowired
    private ApplicationUserRep applicationUserRep;

    @Override
    public Optional<SecApplicationUser> selectApplicationUserByUsername(String username) {
        SecApplicationUser applicationUser = applicationUserRep.findApplicationUserByUsername(username);
        if (applicationUser == null) throw new NullPointerException();
        return Optional.of(applicationUser);
    }
}
