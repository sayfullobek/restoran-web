package it.revo.restaranserver.payload;

import it.revo.restaranserver.entity.enums.AwareStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AwareDto {
    private UUID id;

    private String nameUz;

    private String nameRu;

    private String nameEn;

    private String link;

    private AwareStatus awareStatus;

    public AwareDto(String nameUz, String nameRu, String nameEn, String link, AwareStatus awareStatus) {
        this.nameUz = nameUz;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.link = link;
        this.awareStatus = awareStatus;
    }
}
