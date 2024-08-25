package com.example.poswebback.dao;

import com.example.poswebback.dao.impl.CustomerDaoImpl;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory() {
    }

    public static DaoFactory getDaoFactory() {
        return daoFactory == null ? daoFactory = new DaoFactory() : daoFactory;
    }

    public SuperDao getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDaoImpl();

            default:
                return null;
        }
    }

    public enum DAOTypes {
        CUSTOMER, CUSTOM, ITEM, ORDERS, ORDERDETAILS
    }
}