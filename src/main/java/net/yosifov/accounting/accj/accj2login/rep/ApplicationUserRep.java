package net.yosifov.accounting.accj.accj2login.rep;



import net.yosifov.accounting.accj.accj2login.entities.SecApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ApplicationUserRep extends JpaRepository<SecApplicationUser, Long> {
    SecApplicationUser findApplicationUserByUsername(String name);
}
