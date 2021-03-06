package com.nikola.chk.message_service.hibernate_logic;

import com.nikola.chk.message_service.error_messages.ErroreObject;
import com.nikola.chk.message_service.persistance.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Nikola on 9/8/2015.
 */
public class HibernateEntityLogic {
    public static Object saveObject(Object object) {
        ErroreObject erroreObject;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        //erroreObject = new ErroreObject(ErrorMessage.hibernateSaveObjectOk, ErroreCode.save_success);
        session.close();
        return object;
    }
    public  static List<Object> getAllEntites(Class classobject)
    {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Object> objectArrayList = session.createCriteria(classobject).list();
        session.getTransaction().commit();
        session.close();
        return objectArrayList;
    }
    public  static Object getEntite(Class classobject, int id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Object object = session.get(classobject, id);
        session.getTransaction().commit();
        session.close();
        return object;
    }
    public  static void updateObject(Object classobject)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(classobject);
        session.getTransaction().commit();
        session.close();
    }
    public  static List<Object> getEntiteCriteriaEquelsList(Class classobject, String row, Object objectForCriteria)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Object> listObjectsForLoad = session.createCriteria(classobject).add(Restrictions.eq(row, objectForCriteria)).list();
        session.getTransaction().commit();
        session.close();
        return listObjectsForLoad;
    }

    public  static List<Object> getEntiteCriteriaEquelsList(Class classobject, String row,String row2, Object objectForCriteria, Object objectForCriteria2)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Object> objectsForLoadList = session.createCriteria(classobject).add(Restrictions.eq(row, objectForCriteria)).add(Restrictions.eq(row2, objectForCriteria2)).list();
        session.getTransaction().commit();
        session.close();
        return objectsForLoadList;
    }
}
