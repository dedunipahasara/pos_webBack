package com.example.poswebback.bo.custom;

import com.example.poswebback.bo.SuperBo;
import com.example.poswebback.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBo extends SuperBo {
    ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String code, Connection connection) throws SQLException, ClassNotFoundException;

    boolean saveItem(ItemDTO dto, Connection connection) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDTO dto, Connection connection) throws SQLException, ClassNotFoundException;

    String generateNewItemCode(Connection connection) throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> itemSearchId(String id, Connection connection) throws SQLException, ClassNotFoundException;


}
