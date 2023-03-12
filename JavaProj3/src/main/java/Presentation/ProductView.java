package Presentation;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * This is the window for operating with products(add,edit,remove,show all products)
 */

public class ProductView extends JFrame{
    private JFormattedTextField fId;
    private JFormattedTextField fName;
    private JFormattedTextField fPrice;
    private JButton bAdd;
    private JButton bEdit;
    private JButton bRemove;
    private JButton bBack;
    private JPanel productView;
    private JFormattedTextField fStock;
    private JButton bTable;

    public ProductView(){
        setContentPane(productView);
        //setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,300);
    }
    public void goBackMainP(ActionListener a)
    {
        bBack.addActionListener(a);
    }

    public void addProduct(ActionListener a)
    {
        bAdd.addActionListener(a);
    }

    public void editProduct(ActionListener a)
    {
        bEdit.addActionListener(a);
    }

    public void removeProduct(ActionListener a)
    {
        bRemove.addActionListener(a);
    }
    public void showProducts(ActionListener a){bTable.addActionListener(a);}

    public int getId(){
        return Integer.parseInt(fId.getText());
    }

    public int getPrice(){
        return Integer.parseInt(fPrice.getText());
    }
    public int getStock(){
        return Integer.parseInt(fStock.getText());
    }

    public String getName(){
        return fName.getText();
    }

    public JPanel getProductView() {
        return productView;
    }
}
