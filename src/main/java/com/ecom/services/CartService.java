package com.ecom.services;

import com.ecom.payload.CartDto;
import com.ecom.payload.ItemRequest;

public interface CartService {
	
	// add item to cart
	// we will check the availability of the cart
	// if the cart is available then we will add items to the cart
	// if the cart is not available then create cart
	
	CartDto addItem(ItemRequest item, String userName);
	
	public CartDto getCart(String userName);
	
	// remove items from cart
	CartDto removeItem(String userName,Integer productId);
	

}
