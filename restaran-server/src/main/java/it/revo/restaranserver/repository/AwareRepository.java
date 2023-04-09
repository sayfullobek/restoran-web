package it.revo.restaranserver.repository;

import it.revo.restaranserver.entity.Aware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;

@CrossOrigin("http://127.0.0.1:5173")
public interface AwareRepository extends JpaRepository<Aware, UUID> {
    boolean existsProductByNameUzEqualsIgnoreCaseAndNameEnEqualsIgnoreCaseAndNameRuEqualsIgnoreCase(String nameUz, String nameEn, String nameRu);

    boolean existsProductByNameUzEqualsIgnoreCaseAndNameEnEqualsIgnoreCaseAndNameRuEqualsIgnoreCaseAndIdNot(String nameUz, String nameEn, String nameRu, UUID id);
}
