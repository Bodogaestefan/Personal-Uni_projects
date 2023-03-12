package Model;

/**
 * Definition of the Client class, used to instantiate Client type objects
 */
public class Client {
    private int id;
    private String cl_name;
    private int age;
    private String address;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", cl_name='" + cl_name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    public Client(){

    }

    public Client(int id, String cl_name, int age, String address) {
        this.id = id;
        this.cl_name = cl_name;
        this.age = age;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCl_name() {
        return cl_name;
    }

    public void setCl_name(String cl_name) {
        this.cl_name = cl_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
