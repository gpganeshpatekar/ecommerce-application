package com.ecom.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderid;
	
	@OneToOne
	private Product product;
	
	private Integer productQuantity;
	
	private double totalProductPrice;
	
	@ManyToOne
	private Order order;
	
	@ManyToOne
	private Cart cart;

}
