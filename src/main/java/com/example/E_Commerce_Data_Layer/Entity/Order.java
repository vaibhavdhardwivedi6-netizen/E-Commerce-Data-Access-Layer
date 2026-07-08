package com.example.E_Commerce_Data_Layer.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders", indexes = { @Index(name = "idx_customer", columnList = "customer_id"),
		@Index(name = "idx_order_date", columnList = "order_date"),
		@Index(name = "idx_status", columnList = "status") })


public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private CustomerProfile customer;

	@Column(name = "order_date", nullable = false)
	private LocalDateTime orderDate;

	@Column(nullable = false)
	private String status;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> orderItems = new ArrayList<>();

	public Order() {
	}

	public Order(CustomerProfile customer, LocalDateTime orderDate, String status) {
		this.customer = customer;
		this.orderDate = orderDate;
		this.status = status;
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

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public void addOrderItem(OrderItem item) {
		orderItems.add(item);
		item.setOrder(this);
	}

	public void removeOrderItem(OrderItem item) {
		orderItems.remove(item);
		item.setOrder(null);
	}

	@Override
	public String toString() {
		return "Order{" + "id=" + id + ", orderDate=" + orderDate + ", status='" + status + '\'' + '}';
	}
}
