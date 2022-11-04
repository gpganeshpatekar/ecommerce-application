package com.ecom.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
	
	
	private Integer cartItemId;

	private ProductDto product;
	
	private Integer productQuantity;
	
	private double totalProductPrice;
	

}
