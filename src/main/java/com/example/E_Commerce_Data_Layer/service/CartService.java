package com.example.E_Commerce_Data_Layer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.E_Commerce_Data_Layer.Entity.Cart;
import com.example.E_Commerce_Data_Layer.Entity.CustomerProfile;
import com.example.E_Commerce_Data_Layer.Entity.Product;
import com.example.E_Commerce_Data_Layer.Exception.NotFound;
import com.example.E_Commerce_Data_Layer.Reposetry.CartReposetry;
import com.example.E_Commerce_Data_Layer.Reposetry.CustomerProfileReposetry;
import com.example.E_Commerce_Data_Layer.Reposetry.ProductReposetry;

@Service
public class CartService {

	@Autowired
	private CartReposetry cartRepo;

	@Autowired
	private CustomerProfileReposetry customerRepo;

	@Autowired
	private ProductReposetry productRepo;

	// Add Product To Cart
	public Cart addProduct(Long customerId, Long productId) {

		CustomerProfile customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new NotFound("Customer Not Found With Id : " + customerId));

		Cart cart = cartRepo.findByCustomer(customer).orElseThrow(() -> new NotFound("Cart Not Found"));

		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new NotFound("Product Not Found With Id : " + productId));

		if (!cart.getProducts().contains(product)) {
			cart.addProduct(product);
		}

		return cartRepo.save(cart);
	}

	// Remove Product From Cart
	public Cart removeProduct(Long customerId, Long productId) {

		CustomerProfile customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new NotFound("Customer Not Found With Id : " + customerId));

		Cart cart = cartRepo.findByCustomer(customer).orElseThrow(() -> new NotFound("Cart Not Found"));

		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new NotFound("Product Not Found With Id : " + productId));

		cart.removeProduct(product);

		return cartRepo.save(cart);
	}

	// Get Customer Cart
	public Cart getCart(Long customerId) {

		CustomerProfile customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new NotFound("Customer Not Found With Id : " + customerId));

		return cartRepo.findByCustomer(customer).orElseThrow(() -> new NotFound("Cart Not Found"));
	}

	// Clear Cart
	public String clearCart(Long customerId) {

		CustomerProfile customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new NotFound("Customer Not Found With Id : " + customerId));

		Cart cart = cartRepo.findByCustomer(customer).orElseThrow(() -> new NotFound("Cart Not Found"));

		cart.getProducts().clear();
		cartRepo.save(cart);

		return "Cart Cleared Successfully";
	}
}