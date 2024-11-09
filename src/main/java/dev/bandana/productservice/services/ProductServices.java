package dev.bandana.productservice.services;

import dev.bandana.productservice.dtos.CreateProductRequestDto;
import dev.bandana.productservice.models.Product;

import java.util.List;

public interface ProductServices {
    List<Product> getAllProducts();
    Product getSingleProduct(long id);
    Product CreateProduct(CreateProductRequestDto createProductRequestDto);
}
