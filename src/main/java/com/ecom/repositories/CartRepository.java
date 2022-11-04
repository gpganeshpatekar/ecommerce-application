package com.ecom.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entities.Cart;
import com.ecom.entities.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	Optional<Cart> findByUser(User user);

}
