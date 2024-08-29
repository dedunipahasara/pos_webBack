package com.example.poswebback.dao;

import com.example.poswebback.dao.impl.CustomerDaoImpl;
import com.example.poswebback.dao.impl.ItemDaoImpl;
import com.example.poswebback.dao.impl.OrderDAOImpl;
import com.example.poswebback.dao.impl.OrderDetailDaoImpl;

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
            case ITEM:
                return new ItemDaoImpl();
            case ORDERS:
                return new OrderDAOImpl();
            case ORDERDETAILS:
                return new OrderDetailDaoImpl();

            default:
                return null;
        }
    }

    public enum DAOTypes {
        CUSTOMER, CUSTOM, ITEM, ORDERS, ORDERDETAILS
    }
}
