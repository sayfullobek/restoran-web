package it.revo.restaranserver.entity;

import it.revo.restaranserver.entity.template.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends AbsNameEntity {
    @NotNull
    private Double price;

    @ManyToOne(optional = false)
    private Category category;

    @Column(length = 10000)
    private String description;

    @Column(nullable = false, unique = true, length = 10000)
    private String img;
}
