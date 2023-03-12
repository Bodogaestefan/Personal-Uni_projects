package PresentationLayer;

import BussinessLayer.*;
import BussinessLayer.MenuItem;
import DataLayer.Serializator;
import org.apache.commons.lang3.ObjectUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Controller {
    private DeliveryService deliveryService;
    private Administrator administrator;
    private Employee employee;
    private Client client;
    private Login login;
    private ReportView reportView;
    private Serializator serializator;
    private GenReports genReports;


    public Controller(DeliveryService deliveryService,Administrator administrator,Login login,Serializator serializator,Employee employee,Client client) {
        this.deliveryService = deliveryService;
        this.administrator = administrator;
        this.employee = employee;
        this.client = client;
        this.login = login;
        this.serializator = serializator;
        this.reportView = new ReportView();
        this.genReports = new GenReports();

        administrator.setVisible(false);
        employee.setVisible(false);
        client.setVisible(false);
        reportView.setVisible(false);
        login.setVisible(true);

        administrator.deleteProductListener(new DeleteProduct());
        administrator.addProductListener(new AddProduct());
        administrator.newProductListener(new AddCompositeProduct());
        administrator.editProductListener(new ModifyProduct());
        administrator.backListener(new BackListener());

        reportView.newGenerateListener(new Generate());

        client.addOrderListener(new AddOrderListener());
        client.orderListener(new OrderListener());
        client.backListener(new BackListenerClient());
        client.filterListener(new Filter());

        login.newLoginListener(new LoginListener());
        login.newRegListener(new RegisterListener());

        administrator.printTable(deliveryService.getMenuItemList());
        administrator.createComboBox(deliveryService.getMenuItemList());
        client.printTable(deliveryService.getMenuItemList());
        client.printOrders(deliveryService.getShoppingCart());
    }

    private class DeleteProduct implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            deliveryService.deleteMenuItem(deliveryService.getMenuItemList(),administrator.getMenuIndex());
            administrator.getContentPane().remove(administrator.getScrollPane());
            administrator.printTable(deliveryService.getMenuItemList());
            serializator.serialize(deliveryService);
            deliveryService=serializator.deserialize();
        }
    }

    private class AddProduct implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            deliveryService.addProduct(administrator);
            administrator.getContentPane().remove(administrator.getScrollPane());
            administrator.printTable(deliveryService.getMenuItemList());
            serializator.serialize(deliveryService);
            deliveryService=serializator.deserialize();
        }
    }

    private class AddCompositeProduct implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            deliveryService.addCompositeProduct(administrator.getComboBox1Index(),administrator.getComboBox2Index(),administrator.getComboBox3Index(),administrator.getComboBox4Index(),administrator.getName());
            administrator.getContentPane().remove(administrator.getScrollPane());
            administrator.printTable(deliveryService.getMenuItemList());
            serializator.serialize(deliveryService);
            deliveryService=serializator.deserialize();
        }
    }

    private class ModifyProduct implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            deliveryService.modifyProduct(administrator.getNameA(),administrator.getRating(),administrator.getCalories(),administrator.getProteins(),administrator.getFats(),administrator.getSodium(),administrator.getPrice(),administrator.getMenuIndex());
            administrator.getContentPane().remove(administrator.getScrollPane());
            administrator.printTable(deliveryService.getMenuItemList());
            serializator.serialize(deliveryService);
            deliveryService=serializator.deserialize();
        }
    }


    private class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            String password = login.getpPassword();
            String username = login.getfUsername().getText();
            int sem=0;

            List<Person> personList = deliveryService.getPersonList();

            for(Person person:personList){
                if(person.getUsername().equals(username)){
                    if(person.getPassword().equals(password)){
                        if(person.getRole()==1){
                            administrator.setVisible(true);
                            reportView.setVisible(true);
                            login.setVisible(false);
                        }
                        else if(person.getRole()==2){
                            employee.setVisible(true);
                            login.setVisible(false);
                        }
                        else{
                            client.getContentPane().remove(client.getScrollPane2());
                            client.setVisible(true);
                            employee.setVisible(true);
                            login.setVisible(false);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Wrong password, try again!");
                    }
                }
            }
        }
    }

    private class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            String password = login.getpPassword();
            String username = login.getfUsername().getText();
            int role = login.getRole();

            Person person = new Person(username,password,role);
            deliveryService.getPersonList().add(person);
            serializator.serialize(deliveryService);
            deliveryService=serializator.deserialize();
        }
    }

    private class BackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            administrator.setVisible(false);
            reportView.setVisible(false);
            login.setVisible(true);
        }
    }

    private class AddOrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            int index = client.getMenuIndex();
            MenuItem menuItem = deliveryService.getMenuItemList().get(index);
            deliveryService.addOrder(menuItem);
            menuItem.incrementCount();
            List<MenuItem> menuItemList = new ArrayList<>();
            for(MenuItem menuItem1:deliveryService.getShoppingCart()){
                menuItemList.add(menuItem1);

            }
            client.getContentPane().remove(client.getScrollPane2());
            client.printOrders(menuItemList);
        }
    }

    private class BackListenerClient implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            client.setVisible(false);
            employee.setVisible(false);
            login.setVisible(true);
        }
    }

    private class OrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
           String rep =  deliveryService.createOrder(login.getfUsername().getText());
           Person person = deliveryService.getPersonByUsername(login.getfUsername().getText());
           person.incrementCount();
           deliveryService.getShoppingCart().clear();
            client.getContentPane().remove(client.getScrollPane2());
            client.printOrders(deliveryService.getShoppingCart());
            employee.updateE(rep);
        }
    }

    private class Filter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            List<MenuItem> menuItemList =  new ArrayList<>();
            List<MenuItem> aux = new ArrayList<>();
            menuItemList = deliveryService.getMenuItemList();
            if(client.getTextNumeA().equals("")){
                aux=menuItemList;
            }
            else{
                for(MenuItem m: menuItemList)
                {
                    if(m.getTitle().contains(client.getTextNumeA()))
                        aux.add(m);
                }
                menuItemList=aux;
            }


            if(!(client.getTextPrice().equals("")))
            {
                aux = menuItemList.stream().filter(h1 -> {
                    return h1.calculatePrice()== Double.parseDouble(client.getTextPrice());
                }).collect(toList());
                menuItemList=aux;
            }

            if(!(client.getTextRating().equals("")))
            {
                aux = menuItemList.stream().filter(h1 -> {
                    return h1.calculateRating()== Double.parseDouble(client.getTextRating());
                }).collect(toList());
                menuItemList=aux;
            }

            if(!(client.getTextProteins().equals("")))
            {
                aux = menuItemList.stream().filter(h1 -> {
                    return h1.calculateProtein()== Integer.parseInt(client.getTextProteins());
                }).collect(toList());
                menuItemList=aux;
            }

            if(!(client.getTextCalories().equals("")))
            {
                aux =  menuItemList.stream().filter(h1 -> {
                    return h1.calculateCalories()== Integer.parseInt(client.getTextCalories());
                }).collect(toList());
                menuItemList=aux;
            }

            if(!(client.getTextFats().equals("")))
            {
                aux = menuItemList.stream().filter(h1 -> {
                    return h1.calculateFat()== Integer.parseInt(client.getTextFats());
                }).collect(toList());
                menuItemList=aux;
            }

            if(!(client.getTextSodium().equals("")))
            {
                aux = menuItemList.stream().filter(h1 -> {
                    return h1.calculateSodium()== Integer.parseInt(client.getTextSodium());
                }).collect(toList());
                menuItemList=aux;
            }

            client.getContentPane().remove(client.getScrollPane1());
            client.printTable(menuItemList);
        }


    }

    private class Generate implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int hourS,hourE,day,noReport,minProducts,minOrder;
            hourS=Integer.parseInt(reportView.gettStartHour());
            hourE=Integer.parseInt(reportView.gettEndHour());
            day=Integer.parseInt(reportView.getTday());
            noReport=Integer.parseInt(reportView.gettNo());
            minOrder = Integer.parseInt(reportView.gettOrders());
            minProducts=Integer.parseInt(reportView.gettProducts());
            genReports.report1(hourS,hourE,deliveryService);
            genReports.report2(minProducts,deliveryService);
            genReports.report3(minOrder,deliveryService);
            genReports.report4(minOrder,day,deliveryService);
        }
    }
}
