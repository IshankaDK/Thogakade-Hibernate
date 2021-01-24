package lk.ijse.hibernate.business.custom.impl;

import lk.ijse.hibernate.business.custom.CustomerBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.DAOType;
import lk.ijse.hibernate.dao.SuperDAO;
import lk.ijse.hibernate.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.hibernate.dto.CustomerDTO;
import lk.ijse.hibernate.entity.Customer;

import java.util.ArrayList;
import java.util.List;



public class CustomerBOImpl implements CustomerBO {

    CustomerDAOImpl customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);

    @Override
    public boolean addCustomer(CustomerDTO customer) throws Exception {
        return customerDAO.add(new Customer(customer.getId(),
                customer.getName(),
                customer.getAddress()));
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return customerDAO.delete(id);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customer) throws Exception {
        return customerDAO.update(new Customer(customer.getId(),customer.getName(),customer.getAddress()));
    }

    @Override
    public CustomerDTO getCustomer(String id) throws Exception {
        Customer customer = customerDAO.find(id);
        return new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress());
    }

    @Override
    public List<CustomerDTO> getAllCustomer() throws Exception {
        List<Customer> customers = customerDAO.findAll();
        List<CustomerDTO> dtoList =new ArrayList<>();
        for (Customer customer : customers) {
            dtoList.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return dtoList;
    }

    @Override
    public String newCustomerID() throws Exception {

        String lastID = customerDAO.getLastCustomerID();
        if (lastID == null) {
            return "C001";
        }
        int newID = Integer.parseInt(lastID.substring(1, 4)) + 1;

        if (newID < 10) {
            return "C00" + newID;
        } else if (newID < 100) {
            return "C0" + newID;
        } else {
            return "C" + newID;
        }

    }
}
