package com.example.E_Commerce_Data_Layer.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "customer_profiles")
public class CustomerProfile {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String address;
    private String phone;

    public CustomerProfile() {}

    public CustomerProfile(String address, String phone) {
        this.address = address;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "CustomerProfile{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
