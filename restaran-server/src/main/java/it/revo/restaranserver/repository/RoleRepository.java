package it.revo.restaranserver.repository;

import it.revo.restaranserver.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
