package it.revo.restaranserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZakazDto {
    private UUID id;

    private UUID productId;
    private Integer nechtaProduct;
    private UUID userId;

    public ZakazDto(UUID productId, Integer nechtaProduct, UUID userId) {
        this.productId = productId;
        this.nechtaProduct = nechtaProduct;
        this.userId = userId;
    }
}
