package com.example.E_Commerce_Data_Layer.Reposetry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.E_Commerce_Data_Layer.Entity.Cart;
import com.example.E_Commerce_Data_Layer.Entity.CustomerProfile;

@Repository
public interface CartReposetry extends JpaRepository<Cart, Long> {

    Optional<Cart> findByCustomer(CustomerProfile customer);

}