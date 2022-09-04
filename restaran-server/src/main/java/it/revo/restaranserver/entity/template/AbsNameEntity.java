package it.revo.restaranserver.entity.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbsNameEntity {
    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nameUz;

    @Column(nullable = false, unique = true)
    private String nameRu;

    @Column(nullable = false, unique = true)
    private String nameEn;

    public AbsNameEntity(String nameUz, String nameRu, String nameEn) {
        this.nameUz = nameUz;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
    }
}
