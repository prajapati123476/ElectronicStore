package com.lcwd.electronics.store.repositories;

import com.lcwd.electronics.store.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
