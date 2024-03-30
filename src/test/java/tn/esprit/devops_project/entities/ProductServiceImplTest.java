package tn.esprit.devops_project.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;
import tn.esprit.devops_project.services.Iservices.IProductService;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class ProductServiceImplTest {

    @Autowired
    private IProductService productService;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;

@BeforeEach
public void setUp() {
    // Clear the Product and Stock tables before each test
    productRepository.deleteAll();
    stockRepository.deleteAll();
    // Create and save a stock for testing
    Stock stock = new Stock();
    stock.setTitle("Test Stock");
    stockRepository.save(stock);

    // Create and save a product for testing
    Product product = new Product();
    product.setIdProduct(1L);
    product.setTitle("Test Product");
    product.setPrice(10.0f);
    product.setQuantity(100);
    product.setCategory(ProductCategory.CLOTHING);
    product.setStock(stock);
    productRepository.save(product);
}
    @Test
    public void testAddProduct() {
        Stock stock = stockRepository.findAll().get(0);
        Product newProduct = new Product();
        newProduct.setTitle("New Product");
        newProduct.setPrice(20.0f);
        newProduct.setQuantity(50);
        newProduct.setCategory(ProductCategory.BOOKS);
       productService.addProduct(newProduct, stock.getIdStock());

        Product retrievedProduct = productRepository.findById(newProduct.getIdProduct()).orElse(null);
        assertNotNull(retrievedProduct);
        assertEquals("New Product", retrievedProduct.getTitle());
        assertEquals(20.0f, retrievedProduct.getPrice());
        assertEquals(50, retrievedProduct.getQuantity());
        assertEquals(ProductCategory.BOOKS, retrievedProduct.getCategory());
    }
    @Test
    public void testRetrieveProduct() {
        Product product = productService.retrieveProduct(1L);
        assertNotNull(product);
        assertEquals("Test Product", product.getTitle());
        assertEquals(10.0f, product.getPrice());
        assertEquals(100, product.getQuantity());
        assertEquals(ProductCategory.CLOTHING, product.getCategory());
    }

    @Test
    public void testRetrieveAllProduct() {
        List<Product> productList = productService.retreiveAllProduct();
        assertNotNull(productList);
        assertTrue(productList.size() > 0);
    }

    @Test
    public void testRetrieveProductByCategory() {
        List<Product> productList = productService.retrieveProductByCategory(ProductCategory.CLOTHING);
        assertNotNull(productList);
        assertTrue(productList.size() > 0);
        for (Product product : productList) {
            assertEquals(ProductCategory.CLOTHING, product.getCategory());
        }
    }

    @Test
    public void testDeleteProduct() {
        productService.deleteProduct(1L);
        Product deletedProduct = productRepository.findById(1L).orElse(null);
        assertNull(deletedProduct);
    }

    @Test
    public void testRetrieveProductStock() {
        Stock stock = stockRepository.findAll().get(0);
        List<Product> productList = productService.retreiveProductStock(stock.getIdStock());
        assertNotNull(productList);
        assertTrue(productList.size() > 0);
        for (Product product : productList) {
            assertEquals(stock.getIdStock(), product.getStock().getIdStock());
        }
    }

}