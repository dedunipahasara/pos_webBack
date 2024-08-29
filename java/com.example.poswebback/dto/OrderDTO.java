package com.example.poswebback.dto;

import com.example.poswebback.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO  extends Orders implements Serializable
{
    private String orderId;
    private String orderDate;
    private String cusId;
}
