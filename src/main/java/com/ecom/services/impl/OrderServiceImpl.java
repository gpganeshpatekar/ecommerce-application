package com.ecom.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entities.Cart;
import com.ecom.entities.CartItem;
import com.ecom.entities.Order;
import com.ecom.entities.OrderItem;
import com.ecom.entities.OrderRequest;
import com.ecom.entities.User;
import com.ecom.exceptions.ResourceNotFoundException;
import com.ecom.payload.OrderDto;
import com.ecom.repositories.CartRepository;
import com.ecom.repositories.OrderRepository;
import com.ecom.repositories.UserRepository;
import com.ecom.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public OrderDto createOrder(OrderRequest orderRequest, String userName) {
		User user = this.userRepository.findByEmail(userName)
				.orElseThrow(() -> new ResourceNotFoundException("user not found with username : " + userName));
		Integer cartId = orderRequest.getCartId();
		String address = orderRequest.getAddress();
		Cart cart = this.cartRepository.findById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("cart not found with cart id : " + cartId));
		Order order = new Order();
		Set<CartItem> items = cart.getItems();
		AtomicReference<Double> totalOrderPrice = new AtomicReference<>(0.0);
		Set<OrderItem> orderItems = items.stream().map((cartItem)->{
			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setProductQuantity(cartItem.getProductQuantity());
			orderItem.setTotalProductPrice(cartItem.getTotalProductPrice());
			orderItem.setOrder(order);
			totalOrderPrice.set(totalOrderPrice.get()+orderItem.getTotalProductPrice());
			Integer productId = orderItem.getProduct().getProductId();
			// product
			// update the product quantity
			// saave the product
			return orderItem;
		}).collect(Collectors.toSet());
	
		order.setItems(orderItems);
		order.setBillingAddress(address);
		order.setPaymentStatus("Not Paid");
		order.setOrderAmount(totalOrderPrice.get());
		order.setOrderCreated(new Date());
		order.setOrderDelivered(null);
		order.setOrderStatus("CREATED");
		order.setUser(user);
		
		Order saveOrder = this.orderRepository.save(order);
		cart.getItems().clear();
		this.cartRepository.save(cart);
		return this.modelMapper.map(saveOrder, OrderDto.class);
	}

	@Override
	public List<OrderDto> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDto getOrder(Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDto updateOrder(OrderDto orderDto, Integer orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrder(Integer orderId) {
		// TODO Auto-generated method stub

	}

}
