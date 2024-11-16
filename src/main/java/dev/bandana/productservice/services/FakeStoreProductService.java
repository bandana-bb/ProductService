package dev.bandana.productservice.services;

import dev.bandana.productservice.dtos.FakeStoreProductDto;
import dev.bandana.productservice.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        //Without Respose Entuty
//        FakeStoreProductDto fakeStoreProductDto=restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
//        return fakeStoreProductDto.toProduct();


        //Response entity:-

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity=
                restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

        if(fakeStoreProductDtoResponseEntity.getStatusCode()!=HttpStatus.valueOf(200)){
            //handle this exception.

        }
        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();
        if(fakeStoreProductDto==null){
            return null;
        }

    return fakeStoreProductDto.toProduct();
    }

    public List<String> getAllCategory(){
        return restTemplate.getForObject("https://fakestoreapi.com/products/categories", List.class);
    }

    @Override
    public Product CreateProduct(String title,String description,double price,String category ,String imageUrl)
    {
        FakeStoreProductDto fakeStoreProductDto=new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setImage(imageUrl);

             FakeStoreProductDto fakestoreDto1=   restTemplate.postForObject("https://fakestoreapi.com/products",fakeStoreProductDto,FakeStoreProductDto.class);
        return fakestoreDto1.toProduct();
    }

    @Override
    public Product UpdateProduct(String title, String description, double price, String category, String imageUrl, int id) {
        FakeStoreProductDto fakeStoreProductDto=new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setImage(imageUrl);
        HttpEntity<FakeStoreProductDto> result = new HttpEntity<>(fakeStoreProductDto);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity=
                restTemplate.exchange("https://fakestoreapi.com/products/{id}",
                        HttpMethod.PUT,result, FakeStoreProductDto.class,id);
// update entry by id
        Product p=fakeStoreProductDtoResponseEntity.getBody().toProduct();
          p.setId(id);
          return p;
    }
}
