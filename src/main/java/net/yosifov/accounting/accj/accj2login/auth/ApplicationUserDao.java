package net.yosifov.accounting.accj.accj2login.auth;



import net.yosifov.accounting.accj.accj2login.entities.SecApplicationUser;

import java.util.Optional;


public interface ApplicationUserDao  {

    Optional<SecApplicationUser> selectApplicationUserByUsername(String username);

}
