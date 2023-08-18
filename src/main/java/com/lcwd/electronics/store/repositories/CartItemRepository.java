package com.lcwd.electronics.store.repositories;

import com.lcwd.electronics.store.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}
