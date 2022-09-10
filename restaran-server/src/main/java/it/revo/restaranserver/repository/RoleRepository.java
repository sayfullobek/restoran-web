package it.revo.restaranserver.repository;

import it.revo.restaranserver.entity.Role;
import it.revo.restaranserver.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findRoleByRoleName(RoleName roleName);
}
