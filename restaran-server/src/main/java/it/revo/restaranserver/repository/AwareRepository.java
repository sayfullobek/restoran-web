package it.revo.restaranserver.repository;

import it.revo.restaranserver.entity.Aware;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AwareRepository extends JpaRepository<Aware, UUID> {
    boolean existsProductByNameUzEqualsIgnoreCaseAndNameEnEqualsIgnoreCaseAndNameRuEqualsIgnoreCase(String nameUz, String nameEn, String nameRu);

    boolean existsProductByNameUzEqualsIgnoreCaseAndNameEnEqualsIgnoreCaseAndNameRuEqualsIgnoreCaseAndIdNot(String nameUz, String nameEn, String nameRu, UUID id);
}
