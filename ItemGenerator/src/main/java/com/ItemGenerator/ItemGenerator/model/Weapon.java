package com.ItemGenerator.ItemGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weapon extends Item{

    private int damage;
    private int range;
    private int attackSpeed;

}
