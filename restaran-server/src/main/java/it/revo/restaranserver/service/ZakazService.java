package it.revo.restaranserver.service;

import it.revo.restaranserver.entity.Product;
import it.revo.restaranserver.entity.User;
import it.revo.restaranserver.entity.Zakaz;
import it.revo.restaranserver.payload.ApiResponse;
import it.revo.restaranserver.payload.ZakazDto;
import it.revo.restaranserver.repository.AuthRepository;
import it.revo.restaranserver.repository.ProductRepository;
import it.revo.restaranserver.repository.ZakazRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ZakazService {
    @Autowired
    ZakazRepository zakazRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AuthRepository authRepository;

    public ApiResponse zakazQilish(ZakazDto zakazDto) {
        Optional<Product> byId = productRepository.findById(zakazDto.getProductId());
        if (byId.isPresent()) {
            Optional<User> byId1 = authRepository.findById(zakazDto.getUserId());
            if (byId1.isPresent()) {
                Product product = byId.get();
                Double narxi = zakazDto.getNechtaProduct() * product.getPrice();
                User user = byId1.get();
                Zakaz zakaz = new Zakaz(
                        product,
                        zakazDto.getNechtaProduct(),
                        narxi,
                        user
                );
                zakazRepository.save(zakaz);
                return new ApiResponse("successfully zakaz qilindi", true);
            }
            return new ApiResponse("bunday user joq", false);
        }
        return new ApiResponse("bunday product joq", false);
    }
}
