package it.revo.restaranserver.service;

import it.revo.restaranserver.entity.Product;
import it.revo.restaranserver.payload.ApiResponse;
import it.revo.restaranserver.payload.ProductDto;
import it.revo.restaranserver.repository.CategoryRepository;
import it.revo.restaranserver.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse addProduct(ProductDto productDto) {
        boolean b = productRepository.existsProductByNameUzEqualsIgnoreCaseAndNameEnEqualsIgnoreCaseAndNameRuEqualsIgnoreCase(productDto.getNameUz(), productDto.getNameEn(), productDto.getNameRu());
        if (!b) {
            Product product = new Product();
            product.setNameUz(productDto.getNameUz());
            product.setNameEn(productDto.getNameEn());
            product.setNameRu(productDto.getNameRu());
            product.setPrice(productDto.getPrice());
            product.setCategory(categoryRepository.findById(productDto.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("getCategory")));
            product.setDescription(productDto.getDescription());
            product.setImg(productDto.getImg());
            productRepository.save(product);
            return new ApiResponse("successfully saved product", true);
        }
        return new ApiResponse("Bunday malumot mavjud", false);
    }

    public List<ProductDto> getProductByCategory(UUID uuid) {
        List<Product> allProductByCourseId = productRepository.findAllProductByCategoryId(uuid);
        List<ProductDto> resProductsList = new ArrayList<>();
        for (Product product : allProductByCourseId) {
            ProductDto resProducts = new ProductDto(
                    product.getId(),
                    product.getNameUz(),
                    product.getNameRu(),
                    product.getNameEn(),
                    product.getPrice(),
                    product.getCategory(),
                    product.getDescription(),
                    product.getImg()
            );
            resProductsList.add(resProducts);
        }
        return resProductsList;
    }

    public ApiResponse delProduct(UUID uuid) {
        Optional<Product> byId = productRepository.findById(uuid);
        if (byId.isPresent()) {
            Product product = byId.get();
            productRepository.delete(product);
            return new ApiResponse("successfully deleted product", true);
        }
        return new ApiResponse("this is not found id", false);
    }

    public Product getOne(UUID uuid) {
        Optional<Product> byId = productRepository.findById(uuid);
        return byId.orElse(null);
    }
}
