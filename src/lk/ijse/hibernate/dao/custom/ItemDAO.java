package lk.ijse.hibernate.dao.custom;

import lk.ijse.hibernate.dao.SuperDAO;
import lk.ijse.hibernate.entity.Customer;
import lk.ijse.hibernate.entity.Item;

public interface ItemDAO extends SuperDAO<Item, String> {
    public String getLastItemID()throws Exception;
}
