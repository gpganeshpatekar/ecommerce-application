package com.ecom.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id",unique = true)
	private Integer productId;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_desc")
	private String productDesc;
	
	@Column(name = "product_price")
	private double productPrice;
	
	@Column(name = "product_qty")
	private Integer productQty;
	
	@Column(name = "live_status")
	private boolean liveStatus;
	
	@Column(name = "stock_status")
	private boolean stockStatus = true;
	
	
	@Column(name = "product_image")
	private String productImage;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@CreationTimestamp
	@Column(name = "created_date", updatable = false)
	private LocalDate createdDate;
	
	@UpdateTimestamp
	@Column(name = "updated_date", insertable = false)
	private LocalDate updateDate;
	
	
	
	
	
	
}