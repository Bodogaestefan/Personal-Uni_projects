package BussinessLayer;

import java.util.List;

public class CompositeProduct extends MenuItem{
   private List<MenuItem> productList;

    public CompositeProduct(String title, List<MenuItem> productList) {
        super(title);
        this.productList = productList;
    }

    @Override
    public double calculateRating() {
        double avgRating=0;
        for(MenuItem menuItem : productList){
            avgRating += menuItem.calculateRating();
        }
        avgRating/=productList.size();
        return avgRating;
    }

    @Override
    public int calculateCalories() {
        int totalCalories=0;
        for(MenuItem menuItem : productList){
            totalCalories += menuItem.calculateCalories();
        }
        return totalCalories;
    }

    @Override
    public int calculateProtein() {
        int totalProtein=0;
        for(MenuItem menuItem : productList){
            totalProtein += menuItem.calculateProtein();
        }
        return totalProtein;
    }

    @Override
    public int calculateFat() {
        int totalFat=0;
        for(MenuItem menuItem : productList){
            totalFat += menuItem.calculateFat();
        }
        return totalFat;
    }

    @Override
    public int calculateSodium() {
        int totalSodium=0;
        for(MenuItem menuItem : productList){
            totalSodium += menuItem.calculateSodium();
        }
        return totalSodium;
    }

    @Override
    public double calculatePrice() {
        double totalPrice=0;
        for(MenuItem menuItem : productList){
            totalPrice += menuItem.calculatePrice();
        }
        return totalPrice;
    }

    @Override
    public void setRating() {

    }

    @Override
    public void setCalories() {

    }

    @Override
    public void setProtein() {

    }

    @Override
    public void setRating(double rating) {

    }

    @Override
    public void setCalories(int calories) {

    }

    @Override
    public void setProtein(int protein) {

    }

    @Override
    public void setFat(int fat) {

    }

    @Override
    public void setSodium(int sodium) {

    }

    @Override
    public void setPrice(double price) {

    }

    public void setProductList(List<MenuItem> productList) {
        this.productList = productList;
    }
}
