package com.ecom.payload;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto {
	
	private Integer cartId;
	private UserDto user;
	private Set<CartItemDto> items = new HashSet<>();

}
