package BussinessLayer;

import PresentationLayer.Administrator;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.List;

public class DeliveryService extends Observable implements Serializable{
    private List<MenuItem> menuItemList;
    private List<Person> personList;
    private HashMap<Order, ArrayList<MenuItem>> orderHashMap;
    private ArrayList<Order> orders;
    private ArrayList<MenuItem> shoppingCart;
    private int lastOrderIndex;

    public DeliveryService(List<MenuItem> menuItemList, List<Person> personList, HashMap<Order, ArrayList<MenuItem>> orderHashMap,ArrayList<MenuItem> shoppingCart) {
        this.menuItemList = menuItemList;
        this.personList = personList;
        this.orderHashMap = orderHashMap;
        this.shoppingCart = shoppingCart;
        lastOrderIndex=0;
        orders = new ArrayList<>();

    }

    public ArrayList<Order> getOrders(){
        return orders;
    }

    public int getLastOrderIndex() {
        return lastOrderIndex;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public HashMap<Order, ArrayList<MenuItem>> getOrderHashMap() {
        return orderHashMap;
    }

    public void setOrderHashMap(HashMap<Order, ArrayList<MenuItem>> orderHashMap) {
        this.orderHashMap = orderHashMap;
    }

    public void addOrder(){
        setChanged();
        notifyObservers();
    }

    public Person getPersonByUsername(String username){
        for(Person person:personList){
            if(person.getUsername().equals(username)){
                return person;
            }
        }
        return null;
    }


    public void deleteMenuItem(List<MenuItem> menuItemList,int index){
        menuItemList.remove(index);
    }

    public void addProduct(Administrator administrator){
        List<MenuItem> menuItemList = getMenuItemList();
        BaseProduct baseProduct = new BaseProduct(administrator.getNameA(),administrator.getRating(),administrator.getCalories(),administrator.getProteins(),administrator.getFats(),administrator.getSodium(),administrator.getPrice());
        menuItemList.add(baseProduct);
        setMenuItemList(menuItemList);
        administrator.getContentPane().remove(administrator.getScrollPane());
        administrator.printTable(getMenuItemList());
    }

    public void addCompositeProduct(int i1,int i2, int i3, int i4,String title){
        MenuItem item1 = menuItemList.get(i1);
        MenuItem item2 = menuItemList.get(i2);
        MenuItem item3 = menuItemList.get(i3);
        MenuItem item4 = menuItemList.get(i4);
        List<MenuItem> compositePList = new ArrayList<>();
        compositePList.add(item1);
        compositePList.add(item2);
        compositePList.add(item3);
        compositePList.add(item4);
        CompositeProduct compositeProduct = new CompositeProduct(title,compositePList);
        menuItemList.add(compositeProduct);

    }

    public void modifyProduct(String name, double rating, int calories, int protein, int fats, int sodium,double price,int index){
        MenuItem menuItem = menuItemList.get(index);
        menuItem.setTitle(name);
        menuItem.setRating(rating);
        menuItem.setProtein(protein);
        menuItem.setFat(fats);
        menuItem.setCalories(calories);
        menuItem.setSodium(sodium);
        menuItem.setPrice(price);
        menuItemList.remove(index);
        menuItemList.add(menuItem);
    }

    public void addOrder(MenuItem menuItem){
        shoppingCart.add(menuItem);
    }

    public ArrayList<MenuItem> getShoppingCart() {
        return shoppingCart;
    }

    private void generateBill(String username, int totalPrice, LocalDate localDate, LocalTime localTime){
        try {
            File myObj = new File("Order"+getLastOrderIndex()+".txt");
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
            FileWriter myWriter = new FileWriter("Order"+getLastOrderIndex()+".txt");
            myWriter.write("ID: "+getLastOrderIndex()+"\nusername: "+username+"\ndate: "+localDate+ "\ntime: "+ localTime+"\nTotal price: "+totalPrice+"Ron");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public String createOrder(String username){

        String rep=new String("");
        LocalTime localTime=LocalTime.now();
        LocalDate localDate=LocalDate.now();
        int id = lastOrderIndex,totalPrice=0;

        Order order = new Order(id,username,localDate,localTime);
        orderHashMap.put(order,shoppingCart);
        orders.add(order);

        for(MenuItem menuItem:shoppingCart){
            totalPrice+=menuItem.calculatePrice();
        }
        generateBill(username,totalPrice,localDate,localTime);
        lastOrderIndex++;
        rep = rep + "ID: "+id+"\nusername: "+username+"\ndate: "+localDate+ "\ntime: "+ localTime+"\nTotal price: "+totalPrice+"Ron\n\n\n";
        return rep;
    }

}
