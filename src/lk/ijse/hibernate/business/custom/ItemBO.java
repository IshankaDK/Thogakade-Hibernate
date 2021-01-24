package lk.ijse.hibernate.business.custom;

import lk.ijse.hibernate.business.SuperBO;
import lk.ijse.hibernate.dto.CustomerDTO;
import lk.ijse.hibernate.dto.ItemDTO;

import java.util.List;

public interface ItemBO extends SuperBO {
    public boolean addItem(ItemDTO item)throws Exception;
    public boolean deleteItem(String id)throws Exception;
    public boolean updateItem(ItemDTO item)throws Exception;
    public ItemDTO getItem(String id)throws Exception;
    public List<ItemDTO> getAllItem()throws Exception;


    public String newItemCode()throws Exception;
}
