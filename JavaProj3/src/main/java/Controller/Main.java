package Controller;

import Connection.ConnectionFactory;
import DataAccess.AbstractDAO;
import Model.Client;
import Presentation.*;
import DataAccess.ClientDAO;

import java.sql.Connection;
import java.util.List;

/**
 * The starting point of the application
 * <p>It contains the main method</p>
 */

public class Main {
    public static void main(String[] args) {
        MainView mainView = new MainView();
        ClientView clientView = new ClientView();
        OrderView orderView = new OrderView();
        ProductView productView = new ProductView();
        TableView tableView = new TableView();

        Controller controller = new Controller(mainView,orderView,clientView,productView,tableView);

    }
}
