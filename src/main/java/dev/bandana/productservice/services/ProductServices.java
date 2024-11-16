package dev.bandana.productservice.services;

import dev.bandana.productservice.models.Category;
import dev.bandana.productservice.models.Product;

import java.util.List;

public interface ProductServices {
    List<Product> getAllProducts();
    List<String> getAllCategory();
    Product getSingleProduct(long id);
    Product CreateProduct(String title,String description,double price,String category,String imageUrl );
    Product UpdateProduct(String title, String description, double price, String category, String imageUrl, int id);
}
