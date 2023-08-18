package com.lcwd.electronics.store.repositories;

import com.lcwd.electronics.store.entities.Cart;
import com.lcwd.electronics.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, String> {


    Optional<Cart> findByUser(User user);

}
