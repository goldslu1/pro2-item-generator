package com.ItemGenerator.ItemGenerator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Armor extends Item{

    private int physicalResistance;
    private int magicalResistance;

}
