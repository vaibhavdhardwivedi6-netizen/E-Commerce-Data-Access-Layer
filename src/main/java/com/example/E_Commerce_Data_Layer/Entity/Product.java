package com.example.E_Commerce_Data_Layer.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
	    name = "products",
	    indexes = {
	        @Index(name = "idx_product_name", columnList = "name"),
	        @Index(name = "idx_category", columnList = "category_id"),
	        @Index(name = "idx_vendor", columnList = "vendor_id")
	    })
@SQLDelete(sql = "UPDATE products SET active = false WHERE id = ?")
@SQLRestriction("active = true")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "cart_product",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "cart_id")
    )
    private List<Cart> carts = new ArrayList<>();

    public Product() {}

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
        this.active = true;
    }

    public Product(String name, Double price, Category category, Vendor vendor) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.vendor = vendor;
        this.active = true;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", active=" + active +
                '}';
    }
}

