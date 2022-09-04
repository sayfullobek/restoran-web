package it.revo.restaranserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ProductDto {
    private UUID id;

    private String nameUz;

    private String nameRu;

    private String nameEn;

    private Double price;

    private UUID categoryId;

    private String description;

    private String img;

    public ProductDto(String nameUz, String nameRu, String nameEn, Double price, UUID categoryId, String description, String img) {
        this.nameUz = nameUz;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.price = price;
        this.categoryId = categoryId;
        this.description = description;
        this.img = img;
    }

    public ProductDto(UUID id, String nameUz, String nameRu, String nameEn, Double price, UUID categoryId, String description, String img) {
        this.id = id;
        this.nameUz = nameUz;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.price = price;
        this.categoryId = categoryId;
        this.description = description;
        this.img = img;
    }
}
