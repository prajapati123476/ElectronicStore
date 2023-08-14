package com.lcwd.electronics.store.repositories;

import com.lcwd.electronics.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
