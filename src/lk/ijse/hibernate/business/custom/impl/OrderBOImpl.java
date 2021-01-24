package lk.ijse.hibernate.business.custom.impl;

import lk.ijse.hibernate.business.custom.OrderBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.DAOType;
import lk.ijse.hibernate.dao.custom.impl.OrderDAOImpl;
import lk.ijse.hibernate.dto.CustomerDTO;
import lk.ijse.hibernate.dto.ItemDTO;
import lk.ijse.hibernate.dto.OrderDTO;
import lk.ijse.hibernate.entity.Customer;
import lk.ijse.hibernate.entity.Item;
import lk.ijse.hibernate.entity.Orders;

import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    OrderDAOImpl orderDAO = DAOFactory.getInstance().getDAO(DAOType.ORDERS);

    @Override
    public boolean addOrder(OrderDTO order) throws Exception {
        CustomerDTO customerDTO = order.getCustomerDTO();
        Customer customer = new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress());
        List<ItemDTO> itemDTOS = order.getItemDTOS();
        ArrayList<Item> itemList = new ArrayList<>();
        for (ItemDTO itemDTO : itemDTOS) {
            itemList.add(new Item(itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getQty(),itemDTO.getPrice()));
        }
        return orderDAO.add(new Orders(order.getOrderID(),order.getDate(),customer,itemList));
    }

    @Override
    public String newOrderID() throws Exception {
        String lastOrderID = orderDAO.getLastOrderID();
        if(lastOrderID==null){
            return "OR001";
        }
        int newCode = Integer.parseInt(lastOrderID.substring(2, 5)) + 1;

        if (newCode < 10) {
            return "OR00" + newCode;
        } else if (newCode < 100) {
            return "OR0" + newCode;
        } else {
            return "OR" + newCode;
        }
    }
}
