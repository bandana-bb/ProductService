package dev.bandana.productservice.controllers;

import dev.bandana.productservice.dtos.CreateProductRequestDto;
import dev.bandana.productservice.models.Product;
import dev.bandana.productservice.services.ProductServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {


    public ProductServices productServices;

    ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }
    /*
    * Api= method in my controller
    * */

    /**
     * GET /Product/
     */
    @RequestMapping("/products")
    public List<Product>getAllProducts(){
       return  productServices.getAllProducts();
    }
    /*
    * GET /products/{id}
    * */
    @RequestMapping("/products/{id}")
    public Product getSingleProduct(@PathVariable("id") int id){
        return productServices.getSingleProduct(id);
    }

    /*
    * Create a product
    *{
    * title:
    * description:
    * price:
    * category:
    *
    * POST /products/
    * */

    @PostMapping("/products/")
    public Product createProduct(@RequestBody CreateProductRequestDto createProductRequestDto){
        return productServices.CreateProduct(createProductRequestDto);
    }
}
