package com.ecom.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payload.CartDto;
import com.ecom.payload.ItemRequest;
import com.ecom.services.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@PostMapping(value = "/",consumes = "application/json",produces = "application/json" )
	public ResponseEntity<CartDto> addItemToCart(@RequestBody ItemRequest request, Principal principal){
		CartDto addItem = this.cartService.addItem(request, principal.getName());
		return new ResponseEntity<CartDto>(addItem,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{username}",produces = "application/json")
	public ResponseEntity<CartDto> getCart(Principal principal){
		CartDto cartDto = this.cartService.getCart(principal.getName());
		return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
	}
	
	@PutMapping("/{productId}")
	public ResponseEntity<CartDto> removeProductFromCart(@PathVariable Integer productId,Principal principal){
		CartDto removeItem = this.cartService.removeItem(principal.getName(), productId);
		return new ResponseEntity<CartDto>(removeItem,HttpStatus.OK);
	}
}
