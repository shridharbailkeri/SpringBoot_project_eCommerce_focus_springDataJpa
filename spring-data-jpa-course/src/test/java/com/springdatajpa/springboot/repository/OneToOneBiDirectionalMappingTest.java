package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Address;
import com.springdatajpa.springboot.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToOneBiDirectionalMappingTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void saveAddressMethod() {
        Order order = new Order();
        order.setOrderTrackingNumber("1000ABC");
        order.setTotalQuantity(5);
        order.setStatus("IN PROGRESS");
        order.setTotalPrice(new BigDecimal(100));

        Address address = new Address();
        address.setCity("Bangalore");
        address.setStreet("Richmond circle");
        address.setState("Karnataka");
        address.setCountry("India");
        address.setZipCode("51663");

        order.setBillingAddress(address);
        address.setOrder(order);

        addressRepository.save(address);
    }

    @Test
    void updateAddressMethod() {
       Address address = addressRepository.findById(1L).get();
       address.setZipCode("411048");

       address.getOrder().setStatus("DELIVERED");
       addressRepository.save(address);
    }

    @Test
    void fetchAddressMethod() {
        Address address = addressRepository.findById(1L).get();

    }

    @Test
    void deleteAddressMethod() {
        addressRepository.deleteById(1L);
    }
}
