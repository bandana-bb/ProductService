package dev.bandana.productservice.dtos;


import dev.bandana.productservice.models.Category;
import dev.bandana.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private int id;
    private String title;
    private String description;
    private int price;
    private String image;
    private String category;

    public Product toProduct(){
       Product product = new Product();
       product.setTitle(title);
       product.setDescription(description);
       product.setPrice(price);
        Category category1 = new Category();
        category1.setTitle(category);

        product.setCategory(category1);
        product.setImageUrl(image);
        return product;

    }
}
