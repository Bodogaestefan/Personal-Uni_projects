package Presentation;

import Model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This is the window for operating with clients(add,edit,remove,show all clients)
 */

public class ClientView extends JFrame {
    private JLabel lTitle;
    private JFormattedTextField fid;
    private JFormattedTextField fName;
    private JFormattedTextField fAge;
    private JFormattedTextField fAddress;
    private JButton bAdd;
    private JButton bEdit;
    private JButton bRemove;
    private JButton bBack;
    private JPanel ClientView;
    private JLabel lId;
    private JPanel salut;
    private JButton bTable;
    private JScrollPane scrollPane;

    public JPanel getClientView() {
        return ClientView;
    }

    public ClientView(){
        setContentPane(ClientView);
        //setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,300);
        salut.setEnabled(false);
    }


    public void goBackMainC(ActionListener a)
    {
        bBack.addActionListener(a);
    }

    public void editClient(ActionListener a)
    {
        bEdit.addActionListener(a);
    }

    public void removeClient(ActionListener a)
    {
        bRemove.addActionListener(a);
    }

    public void addClient(ActionListener a)
    {
        bAdd.addActionListener(a);
    }
    public int getId(){
        return Integer.parseInt(fid.getText());
    }

    public String getName(){
        return fName.getText();
    }
    public int getAge(){
        return Integer.parseInt(fAge.getText());
    }
    public String getAddress(){
        return fAddress.getText();
    }
    public void showClients(ActionListener a){bTable.addActionListener(a);}

}
