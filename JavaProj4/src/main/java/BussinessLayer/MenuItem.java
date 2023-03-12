package BussinessLayer;

import DataLayer.Serializator;

import java.io.Serializable;
import java.util.Objects;

public abstract class MenuItem implements Serializable {
    private String title;
    private int count;

    public MenuItem(String title) {
        this.title = title;
        this.count=0;
    }

    public abstract double calculateRating();
    public abstract int calculateCalories();
    public abstract int calculateProtein();
    public abstract int calculateFat();
    public abstract int calculateSodium();
    public abstract double calculatePrice();
    public abstract void setRating();
    public abstract void setCalories();
    public abstract void setProtein();


    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return title.equals(menuItem.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
    public int getCount()
    {
        return this.count;
    }

    public void incrementCount(){
        this.count++;
    }

    public abstract void setRating(double rating);

    public abstract void setCalories(int calories);

    public abstract void setProtein(int protein);

    public abstract void setFat(int fat);

    public abstract void setSodium(int sodium);

    public abstract void setPrice(double price);

    public void setTitle(String title) {
        this.title = title;
    }
}
