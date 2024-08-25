package com.example.poswebback.bo.custom;

import com.example.poswebback.bo.SuperBo;

import java.sql.Connection;
import java.sql.SQLException;

public interface QueryBo extends SuperBo {
    int getSumOrders(Connection connection) throws SQLException, ClassNotFoundException;

    int getItem(Connection connection) throws SQLException, ClassNotFoundException;

    int getCustomer(Connection connection) throws SQLException, ClassNotFoundException;

}