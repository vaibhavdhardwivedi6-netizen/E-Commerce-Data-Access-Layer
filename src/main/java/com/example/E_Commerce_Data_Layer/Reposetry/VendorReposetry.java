package com.example.E_Commerce_Data_Layer.Reposetry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.E_Commerce_Data_Layer.Entity.Vendor;

@Repository
public interface VendorReposetry extends JpaRepository<Vendor, Long> {

}
