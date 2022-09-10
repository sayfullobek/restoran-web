package it.revo.restaranserver.entity;

import it.revo.restaranserver.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Zakaz extends AbsEntity {
    @ManyToOne(optional = false)
    private Product product;

    private Integer nechtaProduct;

    private Double price;

    @ManyToOne(optional = false)
    private User user;
}
