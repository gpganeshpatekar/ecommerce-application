package com.ecom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entities.Order;
import com.ecom.entities.User;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	List<Order> findByUser(User user);

}
