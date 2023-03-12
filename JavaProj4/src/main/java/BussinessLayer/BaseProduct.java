package BussinessLayer;

public class BaseProduct extends MenuItem {
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private double price;

    public BaseProduct(String title, double rating, int calories, int protein, int fat, int sodium, double price) {
        super(title);
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    @Override
    public double calculateRating() {
        return this.rating;
    }

    @Override
    public int calculateCalories() {
        return this.calories;
    }

    @Override
    public int calculateProtein() {
        return this.protein;
    }

    @Override
    public int calculateFat() {
        return this.fat;
    }

    @Override
    public int calculateSodium() {
        return this.sodium;
    }

    @Override
    public double calculatePrice() {
        return this.price;
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
        this.rating = rating;
    }
    @Override
    public void setCalories(int calories) {
        this.calories = calories;
    }
    @Override
    public void setProtein(int protein) {
        this.protein = protein;
    }
    @Override
    public void setFat(int fat) {
        this.fat = fat;
    }
    @Override
    public void setSodium(int sodium) {
        this.sodium = sodium;
    }
    @Override
    public void setPrice(double price) {
        this.price = price;
    }
}