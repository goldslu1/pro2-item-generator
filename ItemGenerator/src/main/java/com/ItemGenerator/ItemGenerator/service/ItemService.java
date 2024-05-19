package com.ItemGenerator.ItemGenerator.service;

import com.ItemGenerator.ItemGenerator.model.Item;
import com.ItemGenerator.ItemGenerator.model.Material;
import com.ItemGenerator.ItemGenerator.repository.ItemRepository;
import com.ItemGenerator.ItemGenerator.repository.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final MaterialRepository materialRepository;
    private ItemRepository itemRepository;

    public ItemService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public Item newItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Item getById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Item updateItem(Item item) {
        Item oldItem = itemRepository.findById(item.getId()).orElse(null);

        if (oldItem != null) {
            oldItem.setName(item.getName());
            oldItem.setType(item.getType());
            oldItem.setMaterial(item.getMaterial());

            itemRepository.save(oldItem);
            return oldItem;
        }

        return null;
    }

    public String deleteItem(Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return "Item deleted";
        } else {
            return "Item not found";
        }

    }

    public List<Item> saveListOfItems(List<Item> items) {
        return itemRepository.saveAll(items);
    }

    public Material getRandomMaterial() {
        List<Material> materials = materialRepository.findAll();
        int randomIndex = (int) (Math.random() * materials.size());
        return materials.get(randomIndex);
    }

    public int getRandomDurability() {
        return (int) (Math.random() * 2000) + 1000; //random number from 1000 to 3000
    }
}
