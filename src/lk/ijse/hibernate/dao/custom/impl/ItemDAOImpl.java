package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.ItemDAO;
import lk.ijse.hibernate.entity.Item;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public boolean add(Item entity) throws Exception {
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
        Item item = session.load(Item.class, s);
        session.delete(item);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Item entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Item find(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Item item = session.get(Item.class, s);
        transaction.commit();
        session.close();
        if (item!=null){
            return item;
        }
        return null;
    }

    @Override
    public List<Item> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        List<Item> list = session.createCriteria(Item.class).list();

        transaction.commit();

        session.close();
        return list;
    }

    @Override
    public String getLastItemID() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        NativeQuery sqlQuery = session.createSQLQuery("select code from Item order by code desc limit 1");
        String code = (String) sqlQuery.uniqueResult();
        transaction.commit();

        session.close();
        return code;
    }
}
