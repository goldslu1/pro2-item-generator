package com.ItemGenerator.ItemGenerator.repository;

import com.ItemGenerator.ItemGenerator.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByName(String name);
    List<Item> findByType(String type);
}
