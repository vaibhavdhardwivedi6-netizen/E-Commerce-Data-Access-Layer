package com.example.E_Commerce_Data_Layer.Entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", unique = true)
    private CustomerProfile customer;

    @ManyToMany(mappedBy = "carts", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    public Cart() {}

    public Cart(CustomerProfile customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerProfile getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerProfile customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
        product.getCarts().add(this);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        product.getCarts().remove(this);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                '}';
    }
}

