package it.revo.restaranserver.entity;

import it.revo.restaranserver.entity.enums.AwareStatus;
import it.revo.restaranserver.entity.template.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Aware extends AbsNameEntity {
    @Column(length = 1000)
    private String link;

    @Enumerated(EnumType.STRING)
    private AwareStatus awareStatus;

    public Aware(String nameUz, String nameRu, String nameEn, String link, AwareStatus awareStatus) {
        super(nameUz, nameRu, nameEn);
        this.link = link;
        this.awareStatus = awareStatus;
    }
}
