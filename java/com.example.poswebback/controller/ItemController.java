package com.example.poswebback.controller;

import com.example.poswebback.bo.BOFactory;
import com.example.poswebback.bo.custom.ItemBo;
import com.example.poswebback.bo.custom.QueryBo;
import com.example.poswebback.dto.CustomerDTO;
import com.example.poswebback.dto.ItemDTO;
import com.example.poswebback.util.UtilProcess;
import jakarta.annotation.Resource;
import jakarta.json.*;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

@WebServlet(urlPatterns = "/item")
public class ItemController  extends HttpServlet {

    private final QueryBo queryBO = (QueryBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOM);
    private final ItemBo itemBO = (ItemBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    @Resource(name = "java:comp/env/jdbc/posWebBackEnd")
    DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        JsonArrayBuilder allItems = Json.createArrayBuilder();

        String code = req.getParameter("code");
        String option = req.getParameter("option");

        PrintWriter writer = resp.getWriter();
        switch (option) {
            case "searchItemCode":
                try (Connection connection = dataSource.getConnection()) {
                    ArrayList<ItemDTO> arrayList = itemBO.itemSearchId(code, connection);
                    if (arrayList.isEmpty()) {
                        JsonObjectBuilder rjo = Json.createObjectBuilder();
                        rjo.add("state", "Error");
                        rjo.add("message", "Item");
                        rjo.add("data", "");
                        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        resp.getWriter().print(rjo.build());

                    } else {
                        for (ItemDTO itemDTO : arrayList) {
                            JsonObjectBuilder item = Json.createObjectBuilder();
                            item.add("code", itemDTO.getCode());
                            item.add("description", itemDTO.getDescription());
                            item.add("qty", itemDTO.getQty());
                            item.add("unitPrice", itemDTO.getUnitPrice());
                            writer.print(item.build());
                        }
                    }

                } catch (SQLException | ClassNotFoundException e) {

                    JsonObjectBuilder rjo = Json.createObjectBuilder();
                    rjo.add("state", "Error");
                    rjo.add("message", e.getLocalizedMessage());
                    rjo.add("data", "");
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    resp.getWriter().print(rjo.build());
                }

                break;
            case "loadAllItem":
                try (Connection connection = dataSource.getConnection()) {
                    ArrayList<ItemDTO> obList = itemBO.getAllItems(connection);

                    for (ItemDTO itemDTO : obList) {
                        JsonObjectBuilder item = Json.createObjectBuilder();
                        item.add("code", itemDTO.getCode());
                        item.add("description", itemDTO.getDescription());
                        item.add("qty", itemDTO.getQty());
                        item.add("unitPrice", itemDTO.getUnitPrice());
                        allItems.add(item.build());
                    }

                    JsonObjectBuilder job = Json.createObjectBuilder();
                    job.add("state", "Ok");
                    job.add("message", "Successfully Loaded..!");
                    job.add("data", allItems.build());
                    resp.getWriter().print(job.build());

                } catch (ClassNotFoundException | SQLException e) {
                    JsonObjectBuilder rjo = Json.createObjectBuilder();
                    rjo.add("state", "Error");
                    rjo.add("message", e.getLocalizedMessage());
                    rjo.add("data", "");
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    resp.getWriter().print(rjo.build());
                }
                break;
            case "ItemIdGenerate":
                try (Connection connection = dataSource.getConnection()) {
                    String iCode = itemBO.generateNewItemCode(connection);

                    JsonObjectBuilder ItemID = Json.createObjectBuilder();
                    ItemID.add("code", iCode);
                    writer.print(ItemID.build());

                } catch (SQLException | ClassNotFoundException e) {

                    JsonObjectBuilder rjo = Json.createObjectBuilder();
                    rjo.add("state", "Error");
                    rjo.add("message", e.getLocalizedMessage());
                    rjo.add("data", "");
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    resp.getWriter().print(rjo.build());
                }
                break;
            case "itemCount":
                try (Connection connection = dataSource.getConnection()) {
                    int countItems = queryBO.getItem(connection);

                    JsonObjectBuilder count = Json.createObjectBuilder();
                    count.add("count", countItems);
                    writer.print(count.build());


                } catch (SQLException | ClassNotFoundException e) {

                    JsonObjectBuilder rjo = Json.createObjectBuilder();
                    rjo.add("state", "Error");
                    rjo.add("message", e.getLocalizedMessage());
                    rjo.add("data", "");
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    resp.getWriter().print(rjo.build());
                }
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String code = req.getParameter("code");
        String description = req.getParameter("description");
        int qty = Integer.parseInt(req.getParameter("qty"));
        double unitPrice = Double.parseDouble(req.getParameter("unitPrice"));

        try (Connection connection = dataSource.getConnection()) {
            ItemDTO i = new ItemDTO(code, description, qty, unitPrice);
            boolean b = itemBO.saveItem(i, connection);

            if (b) {
                JsonObjectBuilder responseObject = Json.createObjectBuilder();
                responseObject.add("state", "Ok");
                responseObject.add("message", "Successfully added..!");
                responseObject.add("data", "");
                resp.getWriter().print(responseObject.build());

            }
        } catch (SQLException e) {

            JsonObjectBuilder error = Json.createObjectBuilder();
            error.add("state", "Error");
            error.add("message", e.getLocalizedMessage());
            error.add("data", "");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print(error.build());

        } catch (ClassNotFoundException e) {

            JsonObjectBuilder error = Json.createObjectBuilder();
            error.add("state", "Error");
            error.add("message", e.getLocalizedMessage());
            error.add("data", "");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print(error.build());

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject item = reader.readObject();

        String code = item.getString("code");
        String description = item.getString("description");
        int qty = Integer.parseInt(item.getString("qty"));
        double unitPrice = Double.parseDouble(item.getString("unitPrice"));

        //Update Item
        ItemDTO iU = new ItemDTO(code, description, qty, unitPrice);
        try (Connection connection = dataSource.getConnection()) {
            boolean b = itemBO.updateItem(iU, connection);

            if (b) {
                JsonObjectBuilder responseObject = Json.createObjectBuilder();
                responseObject.add("state", "Ok");
                responseObject.add("message", "Successfully Updated..!");
                responseObject.add("data", "");
                resp.getWriter().print(responseObject.build());

            } else {
                throw new RuntimeException("Wrong Code, Please Check The Code..!");
            }

        } catch (RuntimeException e) {

            JsonObjectBuilder rjo = Json.createObjectBuilder();
            rjo.add("state", "Error");
            rjo.add("message", e.getLocalizedMessage());
            rjo.add("data", "");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print(rjo.build());

        } catch (ClassNotFoundException | SQLException e) {

            JsonObjectBuilder rjo = Json.createObjectBuilder();
            rjo.add("state", "Error");
            rjo.add("message", e.getLocalizedMessage());
            rjo.add("data", "");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print(rjo.build());

        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject item = reader.readObject();

        String code = item.getString("code");

        //Delete Item
        try (Connection connection = dataSource.getConnection()) {
            boolean b = itemBO.deleteItem(code, connection);

            if (b) {
                JsonObjectBuilder rjo = Json.createObjectBuilder();
                rjo.add("state", "Ok");
                rjo.add("message", "Successfully Deleted..!");
                rjo.add("data", "");
                resp.getWriter().print(rjo.build());

            } else {
                throw new RuntimeException("There is no such Item for that ID..!");
            }
        } catch (RuntimeException e) {

            JsonObjectBuilder rjo = Json.createObjectBuilder();
            rjo.add("state", "Error");
            rjo.add("message", e.getLocalizedMessage());
            rjo.add("data", "");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print(rjo.build());

        } catch (ClassNotFoundException | SQLException e) {

            JsonObjectBuilder rjo = Json.createObjectBuilder();
            rjo.add("state", "Error");
            rjo.add("message", e.getLocalizedMessage());
            rjo.add("data", "");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().print(rjo.build());

        }
    }
}
