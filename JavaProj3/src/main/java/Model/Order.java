package Model;

import DataAccess.OrderDAO;

/**
 * Definition of the Order class, used to instantiate Order type objects
 */

public class Order {
    int id;
    int product_id;
    int client_id;
    int amount;

    public Order(int id, int product_id, int client_id, int amount) {
        this.id = id;
        this.product_id = product_id;
        this.client_id = client_id;
        this.amount = amount;
    }

    public Order(){

    }

    public int isId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
