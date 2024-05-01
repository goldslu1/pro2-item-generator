package com.ItemGenerator.ItemGenerator.service;

import com.ItemGenerator.ItemGenerator.model.Item;
import com.ItemGenerator.ItemGenerator.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    public Item newItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Item getById(int id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Item updateItem(Item item) {
        Item oldItem = itemRepository.findById(item.getId().intValue()).orElse(null);

        if (oldItem != null) {
            oldItem.setName(item.getName());
            oldItem.setType(item.getType());
            oldItem.setMaterial(item.getMaterial());
            oldItem.setDurability(item.getDurability());

            itemRepository.save(oldItem);
            return oldItem;
        }

        return null;
    }

    public String deleteItem(int id) {
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

}
