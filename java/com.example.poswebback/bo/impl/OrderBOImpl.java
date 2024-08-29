package com.example.poswebback.bo.impl;

import com.example.poswebback.bo.custom.OrderBO;
import com.example.poswebback.dao.DaoFactory;
import com.example.poswebback.dao.custom.OrderDAO;
import com.example.poswebback.dto.OrderDTO;
import com.example.poswebback.entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    private final OrderDAO orderDAO = (OrderDAO) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.ORDERS);

    @Override
    public boolean purchaseOrder(OrderDTO dto, Connection connection) throws SQLException, ClassNotFoundException {
        return orderDAO.save(new Orders(dto.getOrderId(), dto.getOrderDate(), dto.getCusId()), connection);
    }

    @Override
    public ArrayList<OrderDTO> getAllOrders(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<Orders> all = orderDAO.getAll(connection);

        ArrayList<OrderDTO> allOrders = new ArrayList<>();
        for (Orders orders : all) {
            allOrders.add(new OrderDTO(orders.getOrderId(), orders.getOrderDate(), orders.getCusId()));
        }
        return allOrders;
    }

    @Override
    public String generateNewOrder(Connection connection) throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewID(connection);
    }

    @Override
    public boolean mangeItems(int qty, String code, Connection connection) throws SQLException, ClassNotFoundException {
        return orderDAO.mangeItems(qty, code, connection);
    }
}
