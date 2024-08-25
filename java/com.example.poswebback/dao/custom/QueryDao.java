package com.example.poswebback.dao.custom;

import java.sql.Connection;
import java.sql.SQLException;

public interface QueryDao {
    int getSumOrders(Connection connection) throws SQLException, ClassNotFoundException;

    int getItem(Connection connection) throws SQLException, ClassNotFoundException;

    int getCustomer(Connection connection) throws SQLException, ClassNotFoundException;
}
