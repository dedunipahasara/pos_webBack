package com.example.poswebback.dao.custom;

import com.example.poswebback.dao.CrudDao;
import com.example.poswebback.entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrderDAO extends CrudDao<Orders, String> {
    boolean mangeItems(int qty, String code, Connection connection) throws SQLException, ClassNotFoundException;

}