package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.CustomerDAO;
import lk.ijse.hibernate.entity.Customer;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;


public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();

        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        Customer customer = session.load(Customer.class, s);
        session.delete(customer);

        transaction.commit();

        session.close();
        return true;
    }

    @Override
    public boolean update(Customer entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();

        session.close();
        return true;
    }

    @Override
    public Customer find(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, s);
//        session.get(customer);

        transaction.commit();

        session.close();
        if (customer!=null){
            return customer;
        }
        return null;
    }

    @Override
    public List<Customer> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        List<Customer> list = session.createCriteria(Customer.class).list();

        transaction.commit();

        session.close();
        return list;
    }

    @Override
    public String getLastCustomerID() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        NativeQuery sqlQuery = session.createSQLQuery("select id from Customer order by id desc limit 1");
        String id = (String) sqlQuery.uniqueResult();
        transaction.commit();

        session.close();
        return id;
    }
}
