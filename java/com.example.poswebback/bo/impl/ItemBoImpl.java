package com.example.poswebback.bo.impl;

import com.example.poswebback.bo.custom.ItemBo;
import com.example.poswebback.dao.DaoFactory;
import com.example.poswebback.dao.custom.ItemDao;
import com.example.poswebback.dto.ItemDTO;
import com.example.poswebback.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBo {
    private final ItemDao itemDAO = (ItemDao) DaoFactory.getDaoFactory().getDAO(DaoFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<Item> allI = itemDAO.getAll(connection);
        ArrayList<ItemDTO> allItem = new ArrayList<>();

        for (Item item : allI) {
            allItem.add(new ItemDTO(item.getCode(), item.getDescription(), item.getQty(), item.getUnitPrice()));
        }
        return allItem;
    }

    @Override
    public boolean deleteItem(String code, Connection connection) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code, connection);
    }

    @Override
    public boolean saveItem(ItemDTO dto, Connection connection) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getCode(), dto.getDescription(), dto.getQty(), dto.getUnitPrice()), connection);
    }

    @Override
    public boolean updateItem(ItemDTO dto, Connection connection) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getCode(), dto.getDescription(), dto.getQty(), dto.getUnitPrice()), connection);
    }

    @Override
    public String generateNewItemCode(Connection connection) throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewID(connection);
    }

    @Override
    public ArrayList<ItemDTO> itemSearchId(String id, Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.searchId(id, connection);
        ArrayList<ItemDTO> allItem = new ArrayList<>();
        for (Item item : all) {
            allItem.add(new ItemDTO(item.getCode(), item.getDescription(), item.getQty(), item.getUnitPrice()));
        }
        return allItem;
    }
}
