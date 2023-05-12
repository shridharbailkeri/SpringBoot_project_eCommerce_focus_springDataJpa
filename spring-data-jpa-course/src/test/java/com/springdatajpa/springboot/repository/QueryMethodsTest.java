package com.springdatajpa.springboot.repository;


import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class QueryMethodsTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameMethod() {
        Product product = productRepository.findByName("product 2");

        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());

    }

    @Test
    void findByIdMethod() {
        Product product = productRepository.findById(1L).get();
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findByNameOrDescription() {
        List<Product> products = productRepository.findByNameOrDescription("product 1", "product 1 description");

        products.forEach(product -> {
            System.out.println(product.getName());
            System.out.println(product.getId());
        });
    }

    @Test
    void findByNameAndDescription() {
        List<Product> products = productRepository.findByNameAndDescription("product 1", "product 1 description");

        products.forEach(product -> {
            System.out.println(product.getName());
            System.out.println(product.getId());
        });
    }

    @Test
    void findDistinctByNameMethod() {
        Product product = productRepository.findDistinctByName("product 1");
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findByPriceGreaterThanMethod() {
        List<Product> products = productRepository.findByPriceGreaterThan(new BigDecimal(100));
        products.forEach(product -> {
            System.out.println(product.getName());
            System.out.println(product.getId());
        });
    }

    @Test
    void findByPriceLessThanMethod() {
        List<Product> products = productRepository.findByPriceLessThan(new BigDecimal(200));
        products.forEach(product -> {
            System.out.println(product.getName());
            System.out.println(product.getId());
        });
    }

    @Test
    void findByNameContainingMethod() {
        List<Product> products = productRepository.findByNameContaining("product");
        products.forEach(product -> {
            System.out.println(product.getName());
            System.out.println(product.getId());
        });
    }

    @Test
    void findByNameLikeMethod() {
        List<Product> products = productRepository.findByNameLike("product 1");
        products.forEach(product -> {
            System.out.println(product.getName());
            System.out.println(product.getId());
        });
    }

    @Test
    void findByPriceBetweenMethod() {
        List<Product> products = productRepository.findByPriceBetween(
                new BigDecimal(100), new BigDecimal(300)
        );
        products.forEach(product -> {
            System.out.println(product.getName());
            System.out.println(product.getId());
        });
    }

    @Test
    void findByDateCreatedBetweenMethod() {
        // start date
        LocalDateTime startDate = LocalDateTime.of(2023, 05, 05, 13, 30, 10);
        // end data
        LocalDateTime endDate = LocalDateTime.of(2023, 05, 05, 13, 45, 21);

        List<Product> products = productRepository.findByDateCreatedBetween(startDate, endDate);

        products.forEach(product -> {
            System.out.println(product.getName());
            System.out.println(product.getId());
        });
    }

    @Test
    void findByNameInMethod() {
        List<Product> products = productRepository.findByNameIn(List.of("product 1", "product 2", "product 3"));
        products.forEach(product -> {
            System.out.println(product.getName());
            System.out.println(product.getId());
        });
    }

    @Test
    void findFirst2ByOrderByNameAscMethod() {
        List<Product> products = productRepository.findFirst2ByOrderByNameAsc();
        products.forEach(product -> {
            System.out.println(product.getName());
            System.out.println(product.getId());
        });
    }


    @Test
    void findTop2ByOrderByPriceDescMethod() {
        List<Product> products = productRepository.findTop2ByOrderByPriceDesc();
        products.forEach(product -> {
            System.out.println(product.getName());
            System.out.println(product.getId());
        });
    }



}
