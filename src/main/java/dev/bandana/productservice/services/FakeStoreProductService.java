package dev.bandana.productservice.services;

import dev.bandana.productservice.dtos.FakeStoreProductDto;
import dev.bandana.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class FakeStoreProductService implements ProductServices{

    RestTemplate restTemplate;//using this you will be able to call third party api
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductDto[] fakeStoreProductDtos= restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            Product p = fakeStoreProductDto.toProduct();
            products.add(p);
        }
        return products;
    }

    @Override
    public Product getSingleProduct(long id) {
        FakeStoreProductDto fakeStoreProductDto=restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public Product CreateProduct(String title,String description,String imageUrl,String category ,int price)
    {
        FakeStoreProductDto fakeStoreProductDto=new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setImage(imageUrl);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);
             FakeStoreProductDto fakestoreDto1=   restTemplate.postForObject("https://fakestoreapi.com/products",fakeStoreProductDto,FakeStoreProductDto.class);
        return fakestoreDto1.toProduct();
    }
}
