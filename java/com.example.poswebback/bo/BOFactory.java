package com.example.poswebback.bo;


import com.example.poswebback.bo.impl.CustomerBoImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public SuperBo getBO(BOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerBoImpl();

            default:
                return null;
        }
    }

    public enum BOTypes {
        CUSTOMER, CUSTOM, ITEM, ORDERS, ORDERDETAILS
    }

}