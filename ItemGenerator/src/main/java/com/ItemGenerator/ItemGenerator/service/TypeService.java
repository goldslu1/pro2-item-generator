package com.ItemGenerator.ItemGenerator.service;

import com.ItemGenerator.ItemGenerator.model.Type;
import com.ItemGenerator.ItemGenerator.repository.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    private TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public Type getRandomType() {
        List<Type> types = typeRepository.findAll();
        int index = (int) (Math.random() * types.size());
        return types.get(index);
    }

    public boolean ifExists(String newType) {
        boolean ifExists = false;
        List<Type> types = typeRepository.findAll();
        for (Type type : types) {
            if (type.getName().equals(newType)) {
                ifExists = true;
            }
        }
        return ifExists;
    }
}
