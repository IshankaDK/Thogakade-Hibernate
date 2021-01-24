package lk.ijse.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Entity
public class Orders implements SuperEntity{
    @Id
    private String orderId;
    private Date date;
    @ManyToOne
    private Customer customer;
    @ManyToMany
    private List<Item> items;

    public Orders() {
    }

    public Orders(String orderId, Date date, Customer customer, List<Item> items) {
        this.setOrderId(orderId);
        this.setDate(date);
        this.setCustomer(customer);
        this.setItems(items);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", date=" + date +
                ", customer=" + customer +
                ", items=" + items +
                '}';
    }
}
