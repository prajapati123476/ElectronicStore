package com.lcwd.electronics.store.repositories;

import com.lcwd.electronics.store.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String > {

}
