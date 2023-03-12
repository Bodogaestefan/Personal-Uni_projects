package DataAccess;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import Connection.ConnectionFactory;
import Model.Client;
import Presentation.ClientView;
import Presentation.TableView;

import javax.swing.*;

/**
 *  Extends the AbstractDAO class and overides its methods to make them usable specific for the Client type objects
 */

public class ClientDAO extends AbstractDAO<Client>{
    public ClientDAO() {
        super();
    }

    @Override
    public List<Client> findAll() {
        return super.findAll();
    }

    @Override
    public Client findById(int id) {
        return super.findById(id);
    }

    @Override
    public Client insert(Client client) {
        return super.insert(client);
    }
    @Override
    public Client update(Client client) {
        return super.update(client);
    }

    @Override
    public void delete(Client client) {
        super.delete(client);
    }

    /**
     * Creates a JTable filled with a list of all the Clients in the DB
     * @param tableView
     * @throws SQLException
     */

    public void viewAllClients(TableView tableView) throws SQLException {


        List<Client> clientsList = findAll();
        var myScrollPane = new JScrollPane();
        myScrollPane.setBounds(20, 80, 600, 400);
        JTable clientTable;
        clientTable=createTable(clientsList);
        clientTable.setEnabled(true);
        clientTable.setVisible(true);

        myScrollPane.setViewportView(clientTable);
        try {
            tableView.add(myScrollPane);
        }catch (Exception e){}
    }

}
