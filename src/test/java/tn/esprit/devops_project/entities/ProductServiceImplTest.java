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
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductServiceImplTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductServiceImpl productService;
    @Test
    public void testAddProduct() {
        // Create a test stock
        Stock stock = new Stock();
        stock.setTitle("Test Stock");
        stockRepository.save(stock);

        // Create a test product
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
    public void testRetreiveAllProduct() {
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

    @Test
    public void testRetrieveProductByCategory() {
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
        product2.setCategory(ProductCategory.ELECTRONICS);
        productRepository.save(product2);

        // Retrieve products by category using the service method
        List<Product> electronicsProducts = productService.retrieveProductByCategory(ProductCategory.ELECTRONICS);

        // Verify that the correct products are retrieved for the specified category
        assertEquals(2, electronicsProducts.size());
    }

    @Test
    public void testDeleteProduct() {
        // Create a test product and save it directly using the repository
        Product productToDelete = new Product();
        productToDelete.setTitle("Product to Delete");
        productToDelete.setPrice(100.0f);
        productToDelete.setQuantity(20);
        productToDelete.setCategory(ProductCategory.CLOTHING);
        Product savedProduct = productRepository.save(productToDelete);
        Long productIdToDelete = savedProduct.getIdProduct();

        // Delete the product using the service method
        productService.deleteProduct(productIdToDelete);

        // Verify that the product is deleted by attempting to retrieve it again
        assertFalse(productRepository.existsById(productIdToDelete));
    }

    @Test
    public void testRetreiveProductStock() {
        // Save some test products directly using the repository with associated stock
        Stock stock1 = new Stock();
        stock1.setTitle("Stock 1");
        stockRepository.save(stock1);

        Stock stock2 = new Stock();
        stock2.setTitle("Stock 2");
        stockRepository.save(stock2);

        Product product1 = new Product();
        product1.setTitle("Product 1");
        product1.setPrice(50.0f);
        product1.setQuantity(10);
        product1.setCategory(ProductCategory.ELECTRONICS);
        product1.setStock(stock1);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setTitle("Product 2");
        product2.setPrice(75.0f);
        product2.setQuantity(5);
        product2.setCategory(ProductCategory.CLOTHING);
        product2.setStock(stock2);
        productRepository.save(product2);

        // Retrieve products by stock using the service method
        List<Product> productsInStock1 = productService.retreiveProductStock(stock1.getIdStock());
        List<Product> productsInStock2 = productService.retreiveProductStock(stock2.getIdStock());

        // Verify that the correct products are retrieved for each stock
        assertEquals(1, productsInStock1.size());
        assertEquals(1, productsInStock2.size());
    }




}