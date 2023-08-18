package com.lcwd.electronics.store.repositories;

import com.lcwd.electronics.store.entities.Order;
import com.lcwd.electronics.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByUser(User user);
}
