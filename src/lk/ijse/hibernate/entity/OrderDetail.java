package lk.ijse.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderDetail implements SuperEntity{
    @Id
    private String orderId;
    private String code;
    private int qty;
    private double price;

    public OrderDetail() {
    }

    public OrderDetail(String orderId, String code, int qty, double price) {
        this.orderId = orderId;
        this.code = code;
        this.qty = qty;
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderId='" + orderId + '\'' +
                ", code='" + code + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
