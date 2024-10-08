package com.example.poswebback.bo.impl;

import com.example.poswebback.bo.custom.CustomerBo;
import com.example.poswebback.dao.DaoFactory;
import com.example.poswebback.dao.custom.CustomerDao;
import com.example.poswebback.dto.CustomerDTO;
import com.example.poswebback.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBoImpl implements CustomerBo {
    private final CustomerDao customerDAO = (CustomerDao) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.CUSTOMER);// hide the object creation logic through the factory

    @Override
    public ArrayList<CustomerDTO> getAllCustomers(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.getAll(connection);
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        for (Customer customer : all) {
            allCustomers.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary()));
        }
        return allCustomers;
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto, Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary()), connection);
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto, Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary()), connection);
    }

    @Override
    public boolean deleteCustomer(String id, Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id, connection);

    }

    @Override
    public String generateNewCustomerID(Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID(connection);
    }

    @Override
    public ArrayList<CustomerDTO> customerSearchId(String id, Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<Customer> all = customerDAO.searchId(id, connection);
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
        for (Customer customer : all) {
            allCustomers.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary()));
        }
        return allCustomers;
    }
}