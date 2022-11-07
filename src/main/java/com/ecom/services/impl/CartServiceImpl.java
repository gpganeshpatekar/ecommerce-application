package com.ecom.services.impl;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entities.Cart;
import com.ecom.entities.CartItem;
import com.ecom.entities.Product;
import com.ecom.entities.User;
import com.ecom.exceptions.ResourceNotFoundException;
import com.ecom.payload.CartDto;
import com.ecom.payload.ItemRequest;
import com.ecom.repositories.CartRepository;
import com.ecom.repositories.ProductRepository;
import com.ecom.repositories.UserRepository;
import com.ecom.services.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public CartDto addItem(ItemRequest item, String username) {
		// to get the user 
		User user = this.userRepository.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with username : " + username));
		//
		Integer productId = item.getProductId();
		Integer productQuantity = item.getProductQuantity();
		if(productQuantity<=0) {
			throw new ResourceNotFoundException("Invalid Product Quantity..");
		}
		// to get product form DB
		Product product = this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("product not found with product id : " + productId));
		if(!product.isStockStatus()) {
			throw new ResourceNotFoundException("product is out of stock");
		}else if(productQuantity>product.getProductQty()) {
			throw new ResourceNotFoundException("only "+product.getProductQty()+" products are available..");
		}
		System.out.println(product.getProductPrice());
		// create new cartItem with product and quantity
		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		cartItem.setProductQuantity(productQuantity);
		cartItem.setTotalProductPrice(product.getProductPrice());
		// getting cart from user
		Cart cart = user.getCart();
		// if cart is null that means user does not have any cart
		if(cart==null) {
			// we will create new cart
			cart = new Cart();
			cart.setUser(user);
		}
		Set<CartItem> items = cart.getItems();
		AtomicReference<Boolean> flag = new AtomicReference<>(false);
		// check
		Set<CartItem> newItems = items.stream().map((i)->{
			if(i.getProduct().getProductId()==product.getProductId()) {
				i.setProductQuantity(productQuantity);
				i.setTotalProductPrice();
				flag.set(true);
			}
			return i;
		}).collect(Collectors.toSet());
		// check
		if(flag.get()) {
			// newItems ko items ki jagah set karenge
			items.clear();
			items.addAll(newItems);
		}else {
			cartItem.setCart(cart);
			items.add(cartItem);
		}
			

		
		Cart updatedCart = this.cartRepository.save(cart);
		return this.modelMapper.map(updatedCart, CartDto.class);
		
	}

	@Override
	public CartDto getCart(String username) {
		User user = this.userRepository.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with username : " + username));
		Cart cart = this.cartRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("cart not found with username : " + username));
		return this.modelMapper.map(cart, CartDto.class);
	}

	@Override
	public CartDto removeItem(String username, Integer productId) {
		User user = this.userRepository.findByEmail(username)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with username : " + username));
		Cart cart = user.getCart();
		Set<CartItem> items = cart.getItems();
		boolean result = items.removeIf((item)->item.getProduct().getProductId()==productId);
		System.out.println(result);
		Cart updatedCart = this.cartRepository.save(cart);
		
		return this.modelMapper.map(updatedCart, CartDto.class);
	}

}
