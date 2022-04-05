package net.yosifov.accounting.accj.accj2login.repositories;

import net.yosifov.accounting.accj.accj2login.entities.SecRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRep extends JpaRepository<SecRole, Long> {
}
