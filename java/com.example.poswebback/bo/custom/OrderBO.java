package com.example.poswebback.bo.custom;

import com.example.poswebback.bo.SuperBo;
import com.example.poswebback.dto.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO
    extends SuperBo {
        boolean purchaseOrder(OrderDTO dto, Connection connection) throws SQLException, ClassNotFoundException;

        ArrayList<OrderDTO> getAllOrders(Connection connection) throws SQLException, ClassNotFoundException;

        String generateNewOrder(Connection connection) throws SQLException, ClassNotFoundException;

        boolean mangeItems(int qty, String code, Connection connection) throws SQLException, ClassNotFoundException;


}
