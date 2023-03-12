package Controller;

import BusinessLogic.ClientBLL;
import BusinessLogic.ProductBLL;
import DataAccess.ClientDAO;
import DataAccess.OrderDAO;
import DataAccess.ProductDAO;
import Model.Client;
import Model.Product;
import Model.Order;
import Presentation.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the action listeners for the swing views and also a method for creating bills in .text files
 */

public class Controller {

    private MainView mainView;
    private OrderView orderView;
    private ClientView clientView;
    private ProductView productView;
    private TableView tableView;

    public Controller(MainView mainView, OrderView orderView, ClientView clientView, ProductView productView, TableView tableView) {
        this.mainView = mainView;
        this.orderView = orderView;
        this.clientView = clientView;
        this.productView = productView;
        this.tableView = tableView;


        mainView.showProductsListner(new ShowProductsView());
        mainView.showClientsListner(new ShowsClientsView());
        mainView.showOrdersListner(new ShowOrdersView());
        orderView.goBackMainO(new GoToMainP());
        productView.goBackMainP(new GoToMainP());
        clientView.goBackMainC(new GoToMainP());
        clientView.addClient(new InsertClient());
        clientView.editClient(new EditClient());
        clientView.removeClient(new RemoveClient());
        clientView.showClients(new ShowClients());
        productView.addProduct(new InsertProduct());
        productView.editProduct(new EditProduct());
        productView.removeProduct(new RemoveProduct());
        productView.showProducts(new ShowProducts());
        orderView.order(new Order());
        orderView.showOrders(new ShowOrders());





    }

    private class ShowProductsView implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent a){
            mainView.setVisible(false);
            productView.setVisible(true);
        }
    }
    private class ShowsClientsView implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent a){
            mainView.setVisible(false);
            clientView.setVisible(true);

        }
    }
    private class ShowOrdersView implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent a){
            mainView.setVisible(false);
            orderView.setVisible(true);
        }
    }
    private class GoToMainP implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent a){
            mainView.setVisible(true);
            orderView.setVisible(false);
            productView.setVisible(false);
            clientView.setVisible(false);
        }
    }

    private class InsertClient implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent a){
            try {
                ClientBLL clientBLL = new ClientBLL();
                ClientDAO clientDAO = new ClientDAO();
                int ID = clientView.getId();
                System.out.println(ID);
                Client client = new Client(ID, clientView.getName(), clientView.getAge(), clientView.getAddress());
                System.out.println(client.toString());
                clientBLL.insert(client);
                List<Client> clienti = clientDAO.findAll();

                for(Client c : clienti) {
                    System.out.println(c.toString());
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(mainView, "Invalid data");
            }

        }
    }

    private class InsertProduct implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent a){
            try {
                ProductBLL productBLL = new ProductBLL();
                ProductDAO productDAO = new ProductDAO();
                Product product = new Product(productView.getId(), productView.getName(), productView.getStock(), productView.getPrice());
                System.out.println(product.toString());
                productBLL.insert(product);
                List<Product>  products= productDAO.findAll();
                for(Product p : products) {
                    System.out.println(p.toString());
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(mainView, "Invalid data");
            }
        }
    }

    public void createBill(Client client, Product product, Model.Order order) {
        try {
            File myObj = new File("Order"+order.isId()+".txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("Order"+order.isId()+".txt");
            myWriter.write("Order no."+order.isId()+"\nProduct: "+product.getPr_name()+" amount: "+order.getAmount()+"\nClient no."+client.getId()+": "+client.getCl_name()+"\nTotal price: "+(order.getAmount())*(product.getPrice()));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }

    private class Order implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent a){
            int amount;
            ClientDAO clientDAO = new ClientDAO();
            ProductDAO productDAO = new ProductDAO();
            OrderDAO orderDAO = new OrderDAO();
            Client client;
            Product product;
            int oId,cId,pId;
            try {
                amount = Integer.parseInt(orderView.getOrder());
                oId=orderView.getOrderId();
                cId=orderView.getClientId();
                pId=orderView.getProductId();
                client = clientDAO.findById(cId);
                product = productDAO.findById(pId);

                if(amount>product.getStock()){
                    JOptionPane.showMessageDialog(null,"There is not enough stock");
                }
                else{
                    product.setStock(product.getStock()-amount);
                    productDAO.update(product);
                    Model.Order order = new Model.Order(oId,pId,cId,amount);
                    orderDAO.insert(order);
                    System.out.println(order.isId());
                    createBill(client,product,order);
                }

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(mainView, "Invalid data");
            }

        }
    }

    private class EditClient implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent a){
            try {
                ClientDAO clientDAO = new ClientDAO();
                int ID = clientView.getId();
                System.out.println(ID);
                Client client = new Client(ID, clientView.getName(), clientView.getAge(), clientView.getAddress());
                System.out.println(client.toString());
                clientDAO.update(client);
                List<Client> clienti = clientDAO.findAll();
                for(Client c : clienti) {
                    System.out.println(c.toString());
                }            } catch (Exception exception) {
                JOptionPane.showMessageDialog(mainView, "Invalid data");
            }

        }
    }

    private class RemoveClient implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent a){
            try {
                ClientDAO clientDAO = new ClientDAO();
                int id = clientView.getId();
                Client c = clientDAO.findById(id);
                clientDAO.delete(c);
                List<Client> clienti = clientDAO.findAll();
                for(Client client : clienti) {
                    System.out.println(client.toString());
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(mainView, "Invalid data");
            }

        }
    }

    private class ShowClients implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent a){
            tableView.setVisible(true);

            ClientDAO clientDAO = new ClientDAO();
            try {
                clientDAO.viewAllClients(tableView);
            } catch (SQLException e) {

            }
        }
    }

    private class EditProduct implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent a){
            try {
                ProductDAO productDAO = new ProductDAO();
                Product product = new Product(productView.getId(), productView.getName(), productView.getStock(), productView.getPrice());
                System.out.println(product.toString());
                productDAO.update(product);
                List<Product> products = productDAO.findAll();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(mainView, "Invalid data");
            }

        }
    }

    private class RemoveProduct implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent a){
            try {
                ProductDAO productDAO = new ProductDAO();
                Product product = new Product(productView.getId(), productView.getName(), productView.getStock(), productView.getPrice());
                System.out.println(product.toString());
                productDAO.delete(product);
                List<Product> products = productDAO.findAll();            } catch (Exception exception) {
                JOptionPane.showMessageDialog(mainView, "Invalid data");
            }

        }
    }
    private class ShowProducts implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent a){
            tableView.setVisible(true);

            ProductDAO productDAO = new ProductDAO();
            try {
                productDAO.viewAllProducts(tableView);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * This is the method for creating a JTable with all the orders filled in
     */
    private class ShowOrders implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent a){
            tableView.setVisible(true);

            OrderDAO orderDAO = new OrderDAO();
            try {
                orderDAO.viewAllOrder(tableView);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
