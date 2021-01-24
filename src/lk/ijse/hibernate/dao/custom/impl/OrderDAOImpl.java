package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.OrderDAO;
import lk.ijse.hibernate.entity.Orders;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public String getLastOrderID() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery sqlQuery = session.createSQLQuery("select orderId from Orders order by orderId desc limit 1");
        String id = (String) sqlQuery.uniqueResult();
        transaction.commit();
        session.close();
        return id;
    }

    @Override
    public boolean add(Orders entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public boolean update(Orders entity) throws Exception {
        return false;
    }

    @Override
    public Orders find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Orders> findAll() throws Exception {
        return null;
    }
}
