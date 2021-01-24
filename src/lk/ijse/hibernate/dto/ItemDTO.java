package lk.ijse.hibernate.dto;

import java.util.List;

public class ItemDTO  {
    private String code;
    private String description;
    private double qty;
    private double price;
    private List<OrderDTO> orders;

    public ItemDTO() {
    }

    public ItemDTO(String code, String description, double qty, double price, List<OrderDTO> orders) {
        this.setCode(code);
        this.setDescription(description);
        this.setQty(qty);
        this.setPrice(price);
        this.setOrders(orders);
    }

    public ItemDTO(String code, String desc, double qty, double price) {
        this.setCode(code);
        this.setDescription(desc);
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

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
}

