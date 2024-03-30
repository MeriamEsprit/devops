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
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductServiceImpl productService;
    @Test
    public void testAddProduct() {
        // Your testAddProduct method implementation
        // Create and save test data
        Stock stock = new Stock();
        stock.setTitle("Test Stock");
        stockRepository.save(stock);

        Product product = new Product();
        product.setTitle("Test Product");
        product.setPrice(50.0f);
        product.setQuantity(10);
        product.setCategory(ProductCategory.ELECTRONICS);

        // Add the product using the service method
        Product savedProduct = productService.addProduct(product, stock.getIdStock());

        // Verify that the product is saved and has correct values
        assertNotNull(savedProduct.getIdProduct());
        assertEquals("Test Product", savedProduct.getTitle());
        assertEquals(50.0f, savedProduct.getPrice());
        assertEquals(10, savedProduct.getQuantity());
        assertEquals(ProductCategory.ELECTRONICS, savedProduct.getCategory());
        assertEquals(stock, savedProduct.getStock());
    }

    @Test
    public void testRetrieveAllProduct() {
        // Your testRetrieveAllProduct method implementation
        // Save some test products directly using the repository
        Product product1 = new Product();
        product1.setTitle("Product 1");
        product1.setPrice(50.0f);
        product1.setQuantity(10);
        product1.setCategory(ProductCategory.ELECTRONICS);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setTitle("Product 2");
        product2.setPrice(75.0f);
        product2.setQuantity(5);
        product2.setCategory(ProductCategory.CLOTHING);
        productRepository.save(product2);

        // Retrieve all products using the service method
        List<Product> products = productService.retreiveAllProduct();

        // Verify that the correct number of products are retrieved
        assertEquals(2, products.size());
    }




}