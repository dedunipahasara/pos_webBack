package com.example.poswebback.bo.custom;

import com.example.poswebback.bo.SuperBo;
import com.example.poswebback.dto.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsBO
        extends SuperBo {
    ArrayList<OrderDetailDTO> getAllOrderDetails(Connection connection) throws SQLException, ClassNotFoundException;

    boolean purchaseOrderDetails(OrderDetailDTO dto, Connection connection) throws SQLException, ClassNotFoundException;
}
