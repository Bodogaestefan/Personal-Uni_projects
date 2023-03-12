package Presentation;

import DataAccess.ClientDAO;
import DataAccess.ProductDAO;
import Model.Client;
import Model.Product;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This is the window for operating with orders(add,edit,remove,show all orders)
 */

public class OrderView extends JFrame{
    private JComboBox cProducts;
    private JComboBox cClient;
    private JFormattedTextField fOrder;
    private JButton bOrder;
    private JButton bBack;
    private JPanel OrderView;
    private JButton bTable;
    private JLabel lAmount;
    private JLabel lId;
    private JFormattedTextField fId;

    public OrderView(){
        setContentPane(OrderView);
        //setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,300);
        ProductDAO productDAO = new ProductDAO();
        ClientDAO clientDAO = new ClientDAO();
        List<Product> pList = productDAO.findAll();
        for(Product product: pList){
            cProducts.addItem(product.isId());
        }

        List<Client> cList = clientDAO.findAll();
        for(Client client : cList){
            cClient.addItem(client.getId());
        }

    }

    public void goBackMainO(ActionListener a)
    {
        bBack.addActionListener(a);
    }

    public void order(ActionListener a)
    {
        bOrder.addActionListener(a);
    }

    public String getOrder(){
        return fOrder.getText();
    }

    public int getClientId(){return Integer.parseInt(cClient.getSelectedItem().toString());}
    public int getProductId(){return Integer.parseInt(cProducts.getSelectedItem().toString());}
    public int getOrderId(){return Integer.parseInt(fId.getText());}
    public void showOrders(ActionListener a){bTable.addActionListener(a);}
}
