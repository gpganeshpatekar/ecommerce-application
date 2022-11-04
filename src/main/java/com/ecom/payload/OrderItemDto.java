package com.ecom.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {

	private Integer orderid;

	private ProductDto product;

	private Integer productQuantity;

	private double totalProductPrice;

}
