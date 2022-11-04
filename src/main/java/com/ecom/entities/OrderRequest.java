package com.ecom.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
	
	private String address;
	private Integer cartId;

}
