package BussinessLayer;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.Observable;

public class Order extends Observable implements Serializable {
    private int id;
    private String cUsername;
    private LocalDate localDate;
    private LocalTime localTime;

    public Order(int id, String cUsername, LocalDate localDate,LocalTime localTime) {
        this.id = id;
        this.cUsername = cUsername;
        this.localDate=localDate;
        this.localTime=localTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getcUsername() {
        return cUsername;
    }

    public void setcUsername(String cUsername) {
        this.cUsername = cUsername;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public int getHour(){return localTime.getHour();}
    public int getDay(){return localDate.getDayOfMonth();}

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && cUsername.equals(order.cUsername) && localDate.equals(order.localDate) && localTime.equals(order.localTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cUsername, localDate,localTime);
    }
}
