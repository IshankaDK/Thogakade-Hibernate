package lk.ijse.hibernate.business.custom.impl;

import lk.ijse.hibernate.business.custom.ItemBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.DAOType;
import lk.ijse.hibernate.dao.custom.impl.ItemDAOImpl;
import lk.ijse.hibernate.dto.ItemDTO;
import lk.ijse.hibernate.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAOImpl itemDAO = DAOFactory.getInstance().getDAO(DAOType.ITEM);

    @Override
    public boolean addItem(ItemDTO item) throws Exception {
       return itemDAO.add(new Item(item.getCode(), item.getDescription(), item.getQty(), item.getPrice()));
    }

    @Override
    public boolean deleteItem(String id) throws Exception {
        return itemDAO.delete(id);
    }

    @Override
    public boolean updateItem(ItemDTO item) throws Exception {
        return itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getQty(), item.getPrice()));
    }

    @Override
    public ItemDTO getItem(String id) throws Exception {
        Item item = itemDAO.find(id);
        return new ItemDTO(item.getCode(),item.getDescription(),item.getQty(),item.getPrice());
    }

    @Override
    public List<ItemDTO> getAllItem() throws Exception {
        List<Item> all = itemDAO.findAll();
        List<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item : all) {
            itemDTOS.add(new ItemDTO(item.getCode(),item.getDescription(),item.getQty(),item.getPrice()));
        }
        return itemDTOS;
    }

    @Override
    public String newItemCode() throws Exception {
        String lastCode = itemDAO.getLastItemID();
        if (lastCode == null) {
            return "I001";
        }
        int newCode = Integer.parseInt(lastCode.substring(1, 4)) + 1;

        if (newCode < 10) {
            return "I00" + newCode;
        } else if (newCode < 100) {
            return "I0" + newCode;
        } else {
            return "I" + newCode;
        }
    }
}
