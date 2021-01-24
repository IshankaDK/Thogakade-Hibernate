package lk.ijse.hibernate.view.tm;

public class OrderTM {
    private String code;
    private String description;
    private double qty;
    private double price;
    private double total;

    public OrderTM() {
    }

    public OrderTM(String code, String description, double qty, double price, double total) {
        this.code = code;
        this.description = description;
        this.qty = qty;
        this.price = price;
        this.total = total;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderTM{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", total=" + total +
                '}';
    }
}
