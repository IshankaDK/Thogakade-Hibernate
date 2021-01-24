package lk.ijse.hibernate.business.custom;

import lk.ijse.hibernate.business.SuperBO;
import lk.ijse.hibernate.dto.ItemDTO;
import lk.ijse.hibernate.dto.OrderDTO;

import java.util.List;

public interface OrderBO extends SuperBO {
    public boolean addOrder(OrderDTO order)throws Exception;

    public String newOrderID()throws Exception;
}
