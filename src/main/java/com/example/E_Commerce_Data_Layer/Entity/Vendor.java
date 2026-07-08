package com.example.E_Commerce_Data_Layer.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vendors")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "contact_email", nullable = false)
    private String contactEmail;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public Vendor() {}

    public Vendor(String name, String contactEmail) {
        this.name = name;
        this.contactEmail = contactEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
        product.setVendor(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setVendor(null);
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                '}';
    }
}

