package tn.esprit.devops_project.entities;

import org.junit.jupiter.api.Assertions;
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
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IProductService productService;

    @Test
    public void testAddProduct() {
        Stock stock = stockRepository.findAll().get(0);
        Product newProduct = new Product();
        newProduct.setTitle("New Product");
        newProduct.setPrice(20.0f);
        newProduct.setQuantity(50);
        newProduct.setCategory(ProductCategory.CLOTHING);
        productService.addProduct(newProduct, stock.getIdStock());

        Product retrievedProduct = productRepository.findById(newProduct.getIdProduct()).orElse(null);
        assertNotNull(retrievedProduct);
        assertEquals("New Product", retrievedProduct.getTitle());
        assertEquals(20.0f, retrievedProduct.getPrice());
        assertEquals(50, retrievedProduct.getQuantity());
        assertEquals(ProductCategory.CLOTHING, retrievedProduct.getCategory());
    }

}