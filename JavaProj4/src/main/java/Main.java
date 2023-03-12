import BussinessLayer.*;
import DataLayer.ReadCSV;
import DataLayer.Serializator;
import PresentationLayer.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Serializator serializator = new Serializator();
        HashMap<Order,ArrayList<MenuItem>> arrayListHashMap=new HashMap<>();
        ReadCSV readCSV = new ReadCSV();
        List<MenuItem> menuItemList = readCSV.read();
        List<Person> personList = new ArrayList<>();
        ArrayList<MenuItem> shoppingCart = new ArrayList<>();
        DeliveryService deliveryService = new DeliveryService(menuItemList,personList,arrayListHashMap,shoppingCart);
        Administrator administrator = new Administrator();
        Employee employee = new Employee();
        Client client = new Client();
        Login login = new Login();
try{
    deliveryService=serializator.deserialize();
}
   catch(Exception e){
   }
        Controller controller = new Controller(deliveryService,administrator,login,serializator,employee,client);
    }
}
