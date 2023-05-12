package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@DataJpaTest
// @DataJpaTest to test repository layer components
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    // intent is not to test but to execute piece of code
    @Test
    void saveMethod() {
        // create product
        Product product = new Product();
        product.setName("product 1");
        product.setDescription("product 1 description");
        product.setSku("100ABC");
        product.setPrice(new BigDecimal(100));
        product.setActive(true);
        product.setImageUrl("product.png");
        // save product
        Product savedObject = productRepository.save(product); // returns saved entity
        // primary key will be generated and assigned to that product

        // display product info
        System.out.println(savedObject.getId());
        System.out.println(savedObject.toString());

        // note : if u use @SpringBootTest and @DataJpaTest above and run this test it will fail

    }

    @Test
    void updateUsingSaveMethod() {

        // find or retrieve an entity by id
        Long id = 1L;
        Product product = productRepository.findById(id).get();
        // update entity information
        product.setName("updated product 1");
        product.setDescription("updated product 1 description");
        // save updated entity
        productRepository.save(product);

    }

    @Test
    void findByIdMethod() {
        Long id = 1L;
        Product product = productRepository.findById(id).get();
    }

    @Test
    void saveAllMethod() {
        // create product
        Product product = new Product();
        product.setName("product 1");
        product.setDescription("product 1 description");
        product.setSku("1400ABC");
        product.setPrice(new BigDecimal(200));
        product.setActive(true);
        product.setImageUrl("product1.png");

        // create product
        Product product3 = new Product();
        product3.setName("product 2");
        product3.setDescription("product 2 description");
        product3.setSku("1500ABC");
        product3.setPrice(new BigDecimal(300));
        product3.setActive(true);
        product3.setImageUrl("product2.png");


        productRepository.saveAll(List.of(product, product3));
    }

    @Test
    void findAllMethod() {
        List<Product> products = productRepository.findAll();

        products.forEach(product -> {
            System.out.println(product.getName());
        });
    }


    @Test
    void deleteById() {
        Long id = 1L;
        productRepository.deleteById(id);
    }

    @Test
    void deleteMethod() {
        // find an entity by id
        Long id = 4L;
        // .get(); means get product type default return is optional type
        Product product = productRepository.findById(id).get();
        // delete entity
        productRepository.delete(product);
    }

    @Test
    void deleteAllMethod() {
        //productRepository.deleteAll(); // delets all entries in db

        Product product = productRepository.findById(7L).get();

        Product product1 = productRepository.findById(8L).get();

        productRepository.deleteAll(List.of(product, product1));
    }

    @Test
    void countMethod() {
        long count = productRepository.count();
        System.out.println(count);
    }

    @Test
    void existByIdMethod() {
        Long id = 9L;

        boolean result = productRepository.existsById(id);
        System.out.println(result);
    }




}