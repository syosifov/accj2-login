package net.yosifov.accounting.accj.accj2login.rep;



import net.yosifov.accounting.accj.accj2login.entities.SecGrntAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrntAutortyRep extends JpaRepository<SecGrntAuthority,Long> {
    SecGrntAuthority findByAuthority(String authority);
}
