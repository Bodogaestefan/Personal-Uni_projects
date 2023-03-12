package PresentationLayer;

import BussinessLayer.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class Administrator extends JFrame {

    private JTable table;
    private JButton btnAddProduct;
    private JButton btnDelete;
    private JButton btnEdit;
    private JButton btnNewProduct;
    private JButton btnBack;
    private JTextField textNume;
    private JTextField textNumeA;
    private JTextField textSodium;
    private JTextField textFats;
    private JTextField textProteins;
    private JTextField textCalories;
    private JTextField textRating;
    private JTextField textPrice;
    private JScrollPane scrollPane;

    JComboBox comboBox1;
    JComboBox comboBox2;
    JComboBox comboBox3;
    JComboBox comboBox4;

    public Administrator(){
        this.setTitle("Administrator");
        this.getContentPane().setBackground(Color.WHITE);
        this.setBackground(Color.WHITE);
        this.setBounds(100, 100, 760, 490);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        btnAddProduct = new JButton("Add");
        btnAddProduct.setBounds(32, 417, 89, 23);
        this.getContentPane().add(btnAddProduct);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(131, 417, 89, 23);
        this.getContentPane().add(btnDelete);

        btnEdit = new JButton("Modify");
        btnEdit.setBounds(230, 417, 89, 23);
        this.getContentPane().add(btnEdit);

        btnNewProduct = new JButton("Create New Product");
        btnNewProduct.setBounds(329, 417, 137, 23);
        this.getContentPane().add(btnNewProduct);

        btnBack = new JButton("Back");
        btnBack.setBounds(650, 417, 89, 23);
        this.getContentPane().add(btnBack);

        comboBox1 = new JComboBox();
        comboBox1.setBounds(30, 246, 142, 22);
        this.getContentPane().add(comboBox1);

        comboBox2 = new JComboBox();
        comboBox2.setBounds(182, 246, 157, 22);
        this.getContentPane().add(comboBox2);

        comboBox3 = new JComboBox();
        comboBox3.setBounds(349, 246, 158, 22);
        this.getContentPane().add(comboBox3);

        comboBox4 = new JComboBox();
        comboBox4.setBounds(517, 246, 143, 22);
        this.getContentPane().add(comboBox4);

        textNume = new JTextField();
        textNume.setBounds(486, 418, 143, 20);
        this.getContentPane().add(textNume);
        textNume.setColumns(10);

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

    public void addProductListener(ActionListener a) {
        btnAddProduct.addActionListener(a);
    }

    public void deleteProductListener(ActionListener a) {
        btnDelete.addActionListener(a);
    }

    public void editProductListener(ActionListener a) {
        btnEdit.addActionListener(a);
    }

    public void newProductListener(ActionListener a) {
        btnNewProduct.addActionListener(a);
    }

    public void backListener(ActionListener a){btnBack.addActionListener(a);}

    public String getName() {
        return textNume.getText();
    }

    public String getNameA() {
        return textNumeA.getText();
    }

    public int getSodium() {
        return Integer.parseInt(textSodium.getText());
    }

    public int getFats() {
        return Integer.parseInt(textFats.getText());
    }

    public int getProteins() {
        return Integer.parseInt(textProteins.getText());
    }

    public int getCalories() {
        return Integer.parseInt(textCalories.getText());
    }

    public double getRating() {
        return Double.parseDouble(textRating.getText());
    }

    public double getPrice() {
        return Double.parseDouble(textPrice.getText());
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void printTable(List<MenuItem> menuItemList){

        scrollPane = new JScrollPane();
        scrollPane.setBounds(182, 11, 478, 224);
        this.getContentPane().add(scrollPane);

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
        scrollPane.setViewportView(table);
    }

    public String[] menuItemName(List<MenuItem> menuItemList){
        int i=0;
        String[] menuName= new String[menuItemList.size()];
        for(MenuItem menuItem:menuItemList){
            menuName[i]=menuItem.getTitle();
            i++;
        }
        return menuName;

    }

    public int getComboBox1Index(){
        return comboBox1.getSelectedIndex();
    }
    public int getComboBox2Index(){
        return comboBox2.getSelectedIndex();
    }
    public int getComboBox3Index(){
        return comboBox3.getSelectedIndex();
    }
    public int getComboBox4Index(){
        return comboBox4.getSelectedIndex();
    }

    public void createComboBox(List<MenuItem> menuItemList)
    {
        String[] menuName = menuItemName(menuItemList);
        this.getContentPane().remove(comboBox1);
        this.getContentPane().remove(comboBox2);
        this.getContentPane().remove(comboBox3);
        this.getContentPane().remove(comboBox4);

        comboBox1 = new JComboBox(menuName);
        comboBox1.setBounds(30, 246, 142, 22);
        this.getContentPane().add(comboBox1);

        comboBox2 = new JComboBox(menuName);
        comboBox2.setBounds(182, 246, 157, 22);
        this.getContentPane().add(comboBox2);

        comboBox3 = new JComboBox(menuName);
        comboBox3.setBounds(349, 246, 158, 22);
        this.getContentPane().add(comboBox3);

        comboBox4 = new JComboBox(menuName);
        comboBox4.setBounds(517, 246, 143, 22);
        this.getContentPane().add(comboBox4);

        comboBox1.setVisible(true);
        comboBox2.setVisible(true);
        comboBox3.setVisible(true);
        comboBox4.setVisible(true);
    }

    public JTable getTable() {
        return table;
    }

    public int getMenuIndex(){
        return table.getSelectedRow();
    }


}