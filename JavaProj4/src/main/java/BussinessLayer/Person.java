package BussinessLayer;

import java.io.Serializable;

public class Person implements Serializable {
    private String username;
    private String password;
    private int count;
    private int role; //1 for admin 2 for employee 3 for client

    public Person(String username, String password, int role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.count = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public int getCount(){
        return this.count;
    }
    public void incrementCount(){
        this.count++;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
