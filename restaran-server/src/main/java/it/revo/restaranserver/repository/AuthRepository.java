package it.revo.restaranserver.repository;

import it.revo.restaranserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
public interface AuthRepository extends JpaRepository<User, UUID> {
    boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findUserByPhoneNumber(String phoneNumber);
}
