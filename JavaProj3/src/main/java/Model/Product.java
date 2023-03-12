package Model;

/**
 * Definition of the Order class, used to instantiate Order type objects
 */

public class Product {
    int id;
    String pr_name;
    int stock;
    int price;

    public Product(){

    }

    public Product(int id, String pr_name, int stock, int price) {
        this.id = id;
        this.pr_name = pr_name;
        this.stock = stock;
        this.price = price;
    }

    public int isId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPr_name() {
        return pr_name;
    }

    public void setPr_name(String pr_name) {
        this.pr_name = pr_name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", pr_name='" + pr_name + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                '}';
    }
}
