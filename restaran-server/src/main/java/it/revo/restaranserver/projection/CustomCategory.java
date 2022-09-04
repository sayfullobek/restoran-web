package it.revo.restaranserver.projection;

import it.revo.restaranserver.entity.Category;
import org.springframework.data.rest.core.config.Projection;

import java.util.UUID;

@Projection(name = "customCategory", types = Category.class)
public interface CustomCategory {
    UUID getId();

    String getNameUz();

    String getNameRu();

    String getNameEn();

}
