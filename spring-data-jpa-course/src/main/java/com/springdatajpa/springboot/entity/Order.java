package com.springdatajpa.springboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
// instead of using all the abobe use at data annotation, hashcode equals included
@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String orderTrackingNumber;

    private int totalQuantity;

    private BigDecimal totalPrice;

    private String status;

    @CreationTimestamp
    private LocalDateTime dateCreated;
    @CreationTimestamp
    private LocalDateTime lastUpdated;

    // apply the action on parent entity on corresponding child or associated entity CascadeType.ALL
    //detach, merge persist remove etc all included in CascadeType.ALL option
    // for example when we save/update/delete order entity then its associated address entity will also get saved/updated/deleted
    // for one to one unidirtectional the primary key of addresses table (target) becomes foreign key orders table (source)
    //  for one to one bidirectional mapping its reverse, the primary key of orders table (source) becomes foreign key in addresses table (target)
    // for bidirectional u have to use one to one on both sides , additionally give mappedBy attribute in source which refers to field in target
    //@OneToOne(cascade = CascadeType.ALL) for unidirectional
    //@JoinColumn(name = "billing_address_id", referencedColumnName = "id") unidirectional
    // to give the foreign key a custom name billing_address_id, we use @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    // here the foreign key billing_address_id references to primary key id of Address class or target entity,
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order") // "order" should be same as order field name in addressses class
    private Address billingAddress;
    // eager means it will retrieve everything
    // lazy means it will retrieve on request or on demand
    // default fetch type for one to many is lazy
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    // performance wise one to many bidirectional works better than unidirectional one to many
    // id from order jpa entity this primary key in order becomes foreign key in child table
    // in plane english it creates "order_id" foreign key in OrderItems table which references back to "id" field in Orders table
    private Set<OrderItem> orderItems = new HashSet<>(); // assuming each order as unique so set

    public BigDecimal getTotalAmount() {
        BigDecimal amount = new BigDecimal(0);

        for(OrderItem item: this.orderItems) {
            amount = amount.add(item.getPrice());
        }
        return amount;
    }
}
