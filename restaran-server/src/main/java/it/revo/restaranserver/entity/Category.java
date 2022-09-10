package it.revo.restaranserver.entity;

import it.revo.restaranserver.entity.template.AbsNameEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nameUz", "nameRu", "nameEn"})
})
public class Category extends AbsNameEntity {
}
