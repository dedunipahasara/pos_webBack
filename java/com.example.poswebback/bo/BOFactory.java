package com.example.poswebback.bo;


import com.example.poswebback.bo.impl.CustomerBoImpl;
import com.example.poswebback.bo.impl.ItemBoImpl;
import com.example.poswebback.bo.impl.OrderBOImpl;
import com.example.poswebback.bo.impl.OrderDetailBOImpl;

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
            case ITEM:
                return new ItemBoImpl();
            case ORDERS:
                return new OrderBOImpl();
            case ORDERDETAILS:
                return new OrderDetailBOImpl();

            default:
                return null;
        }
    }

    public enum BOTypes {
        CUSTOMER, CUSTOM, ITEM, ORDERS, ORDERDETAILS
    }

}
