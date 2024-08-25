package com.example.poswebback.bo.impl;

import com.example.poswebback.bo.custom.QueryBo;
import com.example.poswebback.dao.DaoFactory;
import com.example.poswebback.dao.custom.QueryDao;

import java.sql.Connection;
import java.sql.SQLException;

public class QueryBoImpl implements QueryBo {
    private final QueryDao queryDAO = (QueryDao) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.CUSTOM);

    @Override
    public int getSumOrders(Connection connection) throws SQLException, ClassNotFoundException {
        return queryDAO.getSumOrders(connection);
    }

    @Override
    public int getItem(Connection connection) throws SQLException, ClassNotFoundException {
        return queryDAO.getItem(connection);
    }

    @Override
    public int getCustomer(Connection connection) throws SQLException, ClassNotFoundException {
        return queryDAO.getCustomer(connection);
    }
}
