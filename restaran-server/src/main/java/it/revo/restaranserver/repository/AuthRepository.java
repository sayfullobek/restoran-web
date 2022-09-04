package it.revo.restaranserver.repository;

import it.revo.restaranserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<User, UUID> {
    boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findUserByPhoneNumber(String phoneNumber);
}
