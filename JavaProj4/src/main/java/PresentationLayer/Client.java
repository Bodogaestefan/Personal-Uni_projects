package PresentationLayer;

import BussinessLayer.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class Client extends JFrame{
    private JTable table;
    private JTable table1;
    private JButton btnAdd;
    private JButton btnOrder;
    private JButton btnFilter;
    private JButton btnBack;
    private JTextField textNumeA;
    private JTextField textSodium;
    private JTextField textFats;
    private JTextField textProteins;
    private JTextField textCalories;
    private JTextField textRating;
    private JTextField textPrice;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;

    public Client(){
        this.setTitle("Client");
        this.setBounds(100, 100, 760, 760);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);


        btnAdd = new JButton("Add");
        btnAdd.setBounds(32, 447, 89, 23);
        this.getContentPane().add(btnAdd);

        btnOrder = new JButton("Order");
        btnOrder.setBounds(32, 417, 89, 23);
        this.getContentPane().add(btnOrder);

        btnFilter = new JButton("Filter");
        btnFilter.setBounds(32, 387, 89, 23);
        this.getContentPane().add(btnFilter);

        btnBack = new JButton("Back");
        btnBack.setBounds(32, 357, 89, 23);
        this.getContentPane().add(btnBack);

        textNumeA = new JTextField();
        textNumeA.setBounds(86, 15, 86, 20);
        this.getContentPane().add(textNumeA);
        textNumeA.setColumns(10);

        textSodium = new JTextField();
        textSodium.setBounds(86, 46, 86, 20);
        this.getContentPane().add(textSodium);
        textSodium.setColumns(10);

        textFats = new JTextField();
        textFats.setBounds(86, 76, 86, 20);
        this.getContentPane().add(textFats);
        textFats.setColumns(10);

        textProteins = new JTextField();
        textProteins.setBounds(86, 107, 86, 20);
        this.getContentPane().add(textProteins);
        textProteins.setColumns(10);

        textCalories = new JTextField();
        textCalories.setBounds(86, 134, 86, 20);
        this.getContentPane().add(textCalories);
        textCalories.setColumns(10);

        textRating = new JTextField();
        textRating.setBounds(86, 165, 86, 20);
        this.getContentPane().add(textRating);
        textRating.setColumns(10);

        textPrice = new JTextField();
        textPrice.setBounds(86, 196, 86, 20);
        this.getContentPane().add(textPrice);
        textPrice.setColumns(10);
        JLabel lblNewLabel = new JLabel("Nume");
        lblNewLabel.setBounds(30, 18, 46, 14);
        this.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Sodium");
        lblNewLabel_1.setBounds(30, 49, 46, 14);
        this.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Fats");
        lblNewLabel_2.setBounds(32, 79, 46, 14);
        this.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Proteins");
        lblNewLabel_3.setBounds(30, 110, 46, 14);
        this.getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Calories");
        lblNewLabel_4.setBounds(30, 137, 46, 14);
        this.getContentPane().add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Rating");
        lblNewLabel_5.setBounds(30, 168, 46, 14);
        this.getContentPane().add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("Price");
        lblNewLabel_6.setBounds(30, 199, 46, 14);
        this.getContentPane().add(lblNewLabel_6);
    }

    public void addOrderListener(ActionListener a) {
        btnAdd.addActionListener(a);
    }
    public void orderListener(ActionListener a){
        btnOrder.addActionListener(a);
    }
    public void backListener(ActionListener a) {
        btnBack.addActionListener(a);
    }
    public void filterListener(ActionListener a) {
        btnFilter.addActionListener(a);
    }

    public JTable getTable() {
        return table;
    }

    public String getTextNumeA() {
        return textNumeA.getText();
    }

    public String getTextSodium() {
        return textSodium.getText();
    }

    public String getTextFats() {
        return textFats.getText();
    }

    public String getTextProteins() {
        return textProteins.getText();
    }

    public String getTextCalories() {
        return textCalories.getText();
    }

    public String getTextRating() {
        return textRating.getText();
    }

    public String getTextPrice() {
        return textPrice.getText();
    }

    public JScrollPane getScrollPane1() {
        return scrollPane1;
    }

    public JScrollPane getScrollPane2() {
        return scrollPane2;
    }

    public void printTable(List<BussinessLayer.MenuItem> menuItemList){

        scrollPane1 = new JScrollPane();
        scrollPane1.setBounds(182, 11, 478, 224);
        this.getContentPane().add(scrollPane1);

        String[] cols = new String[7];
        cols[0] = "Name";
        cols[1] = "Sodium";
        cols[2] = "Fats";
        cols[3] = "Proteins";
        cols[4] = "Calories";
        cols[5] = "Rating";
        cols[6] = "Price";

        Object[][] rows = new Object[menuItemList.size()][7];
        int i = 0;

        for(MenuItem menuItem: menuItemList){
            rows[i][0] = menuItem.getTitle();
            rows[i][1] = menuItem.calculateSodium();
            rows[i][2] = menuItem.calculateFat();
            rows[i][3] = menuItem.calculateProtein();
            rows[i][4] = menuItem.calculateCalories();
            rows[i][5] = menuItem.calculateRating();
            rows[i][6] = menuItem.calculatePrice();
            i++;
        }
        table = new JTable(rows,cols);
        scrollPane1.setViewportView(table);
    }
    public void printOrders(List<BussinessLayer.MenuItem> menuItemList){

        scrollPane2 = new JScrollPane();
        scrollPane2.setBounds(182, 250, 478, 224);
        this.getContentPane().add(scrollPane2);

        String[] cols = new String[7];
        cols[0] = "Name";
        cols[1] = "Sodium";
        cols[2] = "Fats";
        cols[3] = "Proteins";
        cols[4] = "Calories";
        cols[5] = "Rating";
        cols[6] = "Price";

        Object[][] rows = new Object[menuItemList.size()][7];
        int i = 0;

        for(MenuItem menuItem: menuItemList){
            rows[i][0] = menuItem.getTitle();
            rows[i][1] = menuItem.calculateSodium();
            rows[i][2] = menuItem.calculateFat();
            rows[i][3] = menuItem.calculateProtein();
            rows[i][4] = menuItem.calculateCalories();
            rows[i][5] = menuItem.calculateRating();
            rows[i][6] = menuItem.calculatePrice();
            i++;
        }
        table1 = new JTable(rows,cols);
        scrollPane2.setViewportView(table1);
    }
    public int getMenuIndex(){
        return table.getSelectedRow();
    }

}
