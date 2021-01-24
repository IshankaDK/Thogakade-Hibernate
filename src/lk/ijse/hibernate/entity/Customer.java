package lk.ijse.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
public class Customer implements SuperEntity{
    @Id
    private String id;
    private String name;
    private String address;
    @OneToMany(mappedBy = "customer")
    private List<Orders> orders;

    public Customer(String id, String name, String address, List<Orders> orders) {
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
        this.setOrders(orders);
    }

    public Customer() {
    }

    public Customer(String id, String name, String address) {
        this.setId(id);
        this.setName(name);
        this.setAddress(address);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", orders=" + orders +
                '}';
    }
}
