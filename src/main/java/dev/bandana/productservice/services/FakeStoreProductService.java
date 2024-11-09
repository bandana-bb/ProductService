package dev.bandana.productservice.services;

import dev.bandana.productservice.dtos.CreateProductRequestDto;
import dev.bandana.productservice.dtos.FakeStoreProductDto;
import dev.bandana.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class FakeStoreProductService implements ProductServices{

    RestTemplate restTemplate;//using this you will be able to call third party api
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product getSingleProduct(long id) {
        FakeStoreProductDto fakeStoreProductDto=restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public Product CreateProduct(CreateProductRequestDto createProductRequestDto) {
        return null;
    }
}
