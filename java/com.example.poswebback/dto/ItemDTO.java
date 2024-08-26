package com.example.poswebback.dto;

import com.example.poswebback.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO extends Item implements Serializable {
    private String code;
    private String description;
    private int qty;
    private double unitPrice;
}
