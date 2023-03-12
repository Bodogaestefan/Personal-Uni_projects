package BussinessLayer;

import DataLayer.FileWriter;
import PresentationLayer.Client;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class GenReports {

    public void report1(int H1, int H2,DeliveryService deliveryService)
    {
        ArrayList<Order> orders;
        orders= (ArrayList<Order>) deliveryService.getOrders().stream().filter(h1 -> {return h1.getHour()>= H1;}).filter(h2 -> {return h2.getHour() <= H2;}).collect(toList());
        FileWriter.raport1(orders);
    }

    public void report2(int H1,DeliveryService deliveryService)
    {
        ArrayList<MenuItem> items;
        items = (ArrayList<MenuItem>) deliveryService.getMenuItemList().stream().filter(h1 -> {return h1.getCount()>= H1;}).collect(toList());
        FileWriter.raport2(items);
    }

    public void report3(int orderCount,DeliveryService deliveryService)
    {
        List<Person> persons;
        persons = (List<Person>) deliveryService.getPersonList().stream().filter(h1 -> {return h1.getCount() > orderCount;}).collect(toList());
        FileWriter.report3(persons);
    }

    public void report4(int H1,int H2,DeliveryService deliveryService)
    {
        List<Order> orders;
        orders = (List<Order>) deliveryService.getOrders().stream().filter(h1 -> {return h1.getDay()>= H2;}).collect(toList());
        FileWriter.report4(orders, H1);
    }

}
