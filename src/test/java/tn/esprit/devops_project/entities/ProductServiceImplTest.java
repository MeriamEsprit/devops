package tn.esprit.devops_project.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
class ProductServiceImplTest {


    @Autowired
    private IProductService iProductService;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testAddProduct() {

        Stock stock = new Stock(1L,"BERSHKA",null);
        stockRepository.save(stock);
        Product product = new Product(6L,"T-shirt",320,50,ProductCategory.CLOTHING,null);
        Product savedProduct = iProductService.addProduct(product, 1L);
        Stock savedStock = stockRepository.findById(savedProduct.getStock().getIdStock()).orElse(null);
        assertNotNull(savedStock);
        assertNotNull(savedProduct);
        Product retrievedProduct = productRepository.findById(savedProduct.getIdProduct()).orElse(null);
        assertNotNull(retrievedProduct);

    }
    @Test
    public void retrieveAllProducts(){
        List<Product> products = iProductService.retreiveAllProduct();
        assertNotNull(products);
    }

    @Test
    public void retrieveProduct(){
        Product savedProduct = iProductService.retrieveProduct(1L);
        assertNotNull(savedProduct);
        assertEquals(1L,1L);
    }


}