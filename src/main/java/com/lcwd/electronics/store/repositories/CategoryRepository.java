package com.lcwd.electronics.store.repositories;

import com.lcwd.electronics.store.entities.Category;
import com.lcwd.electronics.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String > {
    List<Category> findByTitleContaining(String keyword);
}
