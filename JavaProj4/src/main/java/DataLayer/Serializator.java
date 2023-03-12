package DataLayer;

import BussinessLayer.DeliveryService;

import java.io.*;

public class Serializator {

    public Serializator() {
    }

    public void serialize(DeliveryService deliveryService){
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("src/main/resources/file.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.reset();
            out.writeObject(deliveryService);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in src/main/resources/file.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public DeliveryService deserialize(){
        try {
            DeliveryService deliveryService;
            FileInputStream fileIn = new FileInputStream("src/main/resources/file.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            deliveryService = (DeliveryService) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Deserialized");
            return deliveryService;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return null;
        }
    }
}
