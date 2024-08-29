package com.example.poswebback.bo.impl;

import com.example.poswebback.bo.custom.OrderDetailsBO;
import com.example.poswebback.dao.DaoFactory;
import com.example.poswebback.dao.custom.OrderDetailDAO;
import com.example.poswebback.dto.OrderDetailDTO;
import com.example.poswebback.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailBOImpl implements OrderDetailsBO {
    private final OrderDetailDAO orderDetailsDAO = (OrderDetailDAO) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.ORDERDETAILS);

    @Override
    public ArrayList<OrderDetailDTO> getAllOrderDetails(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetail> all = orderDetailsDAO.getAll(connection);

        ArrayList<OrderDetailDTO> allOrderDetails = new ArrayList<>();
        for (OrderDetail orderDetail : all) {
            allOrderDetails.add(new OrderDetailDTO(orderDetail.getOrderId(), orderDetail.getItemCode(), orderDetail.getQty(), orderDetail.getTotal()));
        }
        return allOrderDetails;
    }

    @Override
    public boolean purchaseOrderDetails(OrderDetailDTO dto, Connection connection) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.save(new OrderDetail(dto.getOrderId(), dto.getItemCode(), dto.getQty(), dto.getTotal()), connection);
    }

}