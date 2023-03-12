package DataAccess;

import Model.Client;
import Model.Order;
import Model.Product;
import Connection.ConnectionFactory;
import Presentation.TableView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *  Extends the AbstractDAO class and overides its methods to make them usable specific for the Order type objects
 */

public class OrderDAO extends AbstractDAO<Order> {
    public OrderDAO() {
        super();
    }

    @Override
    public List<Order> findAll() {
        return super.findAll();
    }

    @Override
    public Order findById(int id) {
        return super.findById(id);
    }

    @Override
    public Order insert(Order order) {
        return super.insert(order);
    }

    @Override
    public Order update(Order order) {
        return super.update(order);
    }

    @Override
    public void delete(Order order) {
        super.delete(order);
    }

    /**
     * Creates a JTable filled with a list of all the Orders in the DB
     * @param tableView
     * @throws SQLException
     */

    public void viewAllOrder(TableView tableView) throws SQLException {


        List<Order> orderList = findAll();
        System.out.println(orderList);
        var myScrollPane = new JScrollPane();
        myScrollPane.setBounds(20, 80, 600, 400);
        JTable orderTable;
        orderTable=createTable(orderList);
        orderTable.setEnabled(true);
        orderTable.setVisible(true);

        myScrollPane.setViewportView(orderTable);
        try{
        tableView.add(myScrollPane);
    }catch(Exception e){

        }

    }
}
