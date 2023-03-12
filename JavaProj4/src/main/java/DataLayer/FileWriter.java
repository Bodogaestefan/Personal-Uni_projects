package DataLayer;

import BussinessLayer.MenuItem;
import BussinessLayer.Order;
import BussinessLayer.Person;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileWriter {



    public static void raport1(List<Order> orderList)
    {
        try(BufferedWriter scrieFisier = new BufferedWriter(new java.io.FileWriter("report1.txt"))){
            for(Order order:orderList)
            {
                scrieFisier.write("Order no: "+order.getId() + "\nClient: " + order.getcUsername() + "\nData: " + order.getLocalDate() +"\nTime: "+order.getLocalTime()+ "\n");
            }
            System.out.println("Report1 done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void raport2(ArrayList<MenuItem> menuItems) {
        try(BufferedWriter scrieFisier = new BufferedWriter(new java.io.FileWriter("report2.txt"))){
            for(MenuItem item:menuItems)
            {
                scrieFisier.write("The Menu item " + item.getTitle() + " has been ordered " + item.getCount() + " times" + "\n");
            }
            System.out.println("Report2 done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void report3(List<Person> persons) {
        try(BufferedWriter scrieFisier = new BufferedWriter(new java.io.FileWriter("report3.txt"))){
            for(Person client:persons)
            {
                scrieFisier.write(client.getUsername() + " has ordered " + client.getCount() + " times." + "\n");
            }
            System.out.println("Report3 done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void report4(List<Order> orders, int D) {
        try(BufferedWriter scrieFisier = new BufferedWriter(new java.io.FileWriter("report4.txt"))){
            for(Order order:orders)
            {
                scrieFisier.write("The order " + order.getId() + " was placed " + "on day " + D +"\n");
            }
            System.out.println("Report4 done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

