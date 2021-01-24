package lk.ijse.hibernate.business.custom;

import lk.ijse.hibernate.business.SuperBO;
import lk.ijse.hibernate.dto.CustomerDTO;

import java.util.List;


public interface CustomerBO extends SuperBO {
    public boolean addCustomer(CustomerDTO customer)throws Exception;
    public boolean deleteCustomer(String id)throws Exception;
    public boolean updateCustomer(CustomerDTO customer)throws Exception;
    public CustomerDTO getCustomer(String id)throws Exception;
    public List<CustomerDTO> getAllCustomer()throws Exception;


    public String newCustomerID()throws Exception;
}
