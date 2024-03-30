package tn.esprit.devops_project.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.Iservices.IProductService;
import tn.esprit.devops_project.services.ProductServiceImpl;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")

class ProductServiceImplTest {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IProductService productService;

    @Test
    public void AddProductTest() {

        Product product1 = new Product();
        product1.setTitle("Test Product 1");
        product1.setPrice(10.0f);
        product1.setQuantity(5);
        product1.setCategory(ProductCategory.ELECTRONICS);

        Product product2 = new Product();
        product2.setTitle("Test Product 2");
        product2.setPrice(20.0f);
        product2.setQuantity(8);
        product2.setCategory(ProductCategory.CLOTHING);

        Product product3 = new Product();
        product3.setTitle("Test Product 3");
        product3.setPrice(30.0f);
        product3.setQuantity(10);
        product3.setCategory(ProductCategory.BOOKS);

        Product addedProduct1 = productService.addProduct(product1, 1L);
        Product addedProduct2 = productService.addProduct(product2, 2L);
        Product addedProduct3 = productService.addProduct(product3, 3L);

        assertNotNull(addedProduct1);
        assertNotNull(addedProduct2);
        assertNotNull(addedProduct3);

        assertEquals("Test Product 1", addedProduct1.getTitle());
        assertEquals("Test Product 2", addedProduct2.getTitle());
        assertEquals("Test Product 3", addedProduct3.getTitle());
    }
    @Test
    public void retrieveAllProductsTest(){
        List<Product> products = productService.retreiveAllProduct();
        assertNotNull(products);
    }

    @Test
    public void retrieveProductTest(){
        Product savedProduct = productService.retrieveProduct(17L);
        assertNotNull(savedProduct);
        assertEquals(17L,17L);
    }

    @Test
    public void retrieveProductByCategoryTest() {
        ProductCategory category = ProductCategory.ELECTRONICS;

        List<Product> products = productService.retrieveProductByCategory(category);

        assertNotNull(products);
    }




}