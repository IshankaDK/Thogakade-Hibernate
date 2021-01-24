package lk.ijse.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Item implements SuperEntity{
    @Id
    private String code;
    private String description;
    private double qty;
    private double price;
    @ManyToMany(mappedBy = "items")
    private List<Orders>orders;

    public Item() {
    }

    public Item(String code, String description, double qty, double price, List<Orders> orders) {
        this.setCode(code);
        this.setDescription(description);
        this.setQty(qty);
        this.setPrice(price);
        this.setOrders(orders);
    }

    public Item(String code, String description, double qty, double price) {
        this.setCode(code);
        this.setDescription(description);
        this.setQty(qty);
        this.setPrice(price);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Item{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", orders=" + orders +
                '}';
    }
}

