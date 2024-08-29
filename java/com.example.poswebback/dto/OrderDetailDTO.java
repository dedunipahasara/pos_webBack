package com.example.poswebback.dto;

import com.example.poswebback.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO extends OrderDetail implements Serializable {
    private String orderId;
    private String itemCode;
    private int qty;
    private double total;

}
