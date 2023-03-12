package Presentation;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * This is the main window that the user sees when starting the application
 */



public class MainView extends JFrame{
    private JLabel lTitle;
    private JButton bClients;
    private JButton bProducts;
    private JButton bOrders;
    private JPanel mainPanel;

    public MainView(){
        setContentPane(mainPanel);
        setVisible(true);
        setSize(600,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


    }

    public void showProductsListner(ActionListener a)
    {
        bProducts.addActionListener(a);
    }
    public void showClientsListner(ActionListener a)
    {
        bClients.addActionListener(a);
    }
    public void showOrdersListner(ActionListener a)
    {
        bOrders.addActionListener(a);
    }
}
