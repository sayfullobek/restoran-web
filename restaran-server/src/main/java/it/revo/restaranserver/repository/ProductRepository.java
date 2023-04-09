package it.revo.restaranserver.repository;

import it.revo.restaranserver.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.UUID;

@CrossOrigin("http://127.0.0.1:5173")
public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsProductByNameUzEqualsIgnoreCaseAndNameEnEqualsIgnoreCaseAndNameRuEqualsIgnoreCase(String nameUz, String nameEn, String nameRu);

    List<Product> findAllProductByCategoryId(UUID category_id);
}
