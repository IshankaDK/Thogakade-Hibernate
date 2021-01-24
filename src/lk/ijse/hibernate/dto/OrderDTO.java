package lk.ijse.hibernate.dto;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private String orderID;
    private Date date;
    private CustomerDTO customerDTO;
    private List<ItemDTO>itemDTOS;

    public OrderDTO() {
    }

    public OrderDTO(String orderID, Date date, CustomerDTO customerDTO, List<ItemDTO> itemDTOS) {
        this.setOrderID(orderID);
        this.setDate(date);
        this.setCustomerDTO(customerDTO);
        this.setItemDTOS(itemDTOS);
    }

    public OrderDTO(String oId, String oDate, String cId) {
        this.setOrderID(orderID);
        this.setDate(date);
        this.setCustomerDTO(customerDTO);
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public List<ItemDTO> getItemDTOS() {
        return itemDTOS;
    }

    public void setItemDTOS(List<ItemDTO> itemDTOS) {
        this.itemDTOS = itemDTOS;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderID='" + orderID + '\'' +
                ", date=" + date +
                ", customerDTO=" + customerDTO +
                ", itemDTOS=" + itemDTOS +
                '}';
    }
}
