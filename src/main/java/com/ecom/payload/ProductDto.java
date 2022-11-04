package com.ecom.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
	
	private Integer productId;
	private String productName;
	private String productDesc;
	private double productPrice;
	private Integer productQty;
	private boolean liveStatus;
	private boolean stockStatus = true;
	private String productImage;
	private CategoryDto category;
	

}
