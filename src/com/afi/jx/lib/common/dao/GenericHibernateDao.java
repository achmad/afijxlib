/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afi.jx.lib.common.dao;

import com.afi.jx.lib.common.model.BaseAuditVersioning;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author AFI
 */
public class GenericHibernateDao<T, K extends Serializable> implements GenericDao<T, K> {

    @Autowired()
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;
    private Class<T> persistentClass;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public GenericHibernateDao() {
        persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T load(K key) {
        return (T) sessionFactory.getCurrentSession().load(persistentClass, key);
    }

    @Override
    public T get(K key) {
        return (T) sessionFactory.getCurrentSession().get(persistentClass, key);
    }

    @Override
    public boolean delete(K key) {
        Object entity = getCurrentSession().get(persistentClass, key);
        getCurrentSession().delete(entity);
        return true;
    }

    @Override
    public boolean save(T newEntity) {
        getCurrentSession().save(newEntity);
        return true;
    }

    @Override
    public boolean evict(Object entity) {
        getCurrentSession().evict(entity);
        return true;
    }

    @Override
    public boolean update(T editedEntity) {
        getCurrentSession().update(editedEntity);
        return true;
    }

    @Override
    public void flush() {
        getCurrentSession().flush();
    }

    @Override
    public void clear() {
        getCurrentSession().clear();
    }

    public Criterion buildResctrictionsAndForAllProperty(String[] listProp, String[] listValueLike) {
        Criterion criterion = null;
        for (String valueLike : listValueLike) {
            if (criterion == null) {
                criterion = buildResctrictionsOrForAllProperty(listProp, valueLike);
            } else {
                criterion = Restrictions.and(criterion, buildResctrictionsOrForAllProperty(listProp, valueLike));
            }
        }
        return criterion;
    }

    public Criterion buildResctrictionsOrForAllProperty(String[] listProp, String valueLike) {
        Criterion criterion = null;
        for (String prop : listProp) {
            if (criterion == null) {
                criterion = Restrictions.ilike(prop, valueLike, MatchMode.ANYWHERE);
            } else {
                String valueLikeTemp = null;                
                valueLikeTemp = valueLike;
                criterion = Restrictions.or(criterion, Restrictions.ilike(prop, valueLikeTemp, MatchMode.ANYWHERE));
            }
        }
        return criterion;
    }

    public boolean save(BaseAuditVersioning newEntity, String user) {
        Date date = new Date();
        newEntity.setCreateBy(user);
    	newEntity.setCreateDate(date);
        newEntity.setLastModBy(user);
        newEntity.setLastModDate(date);
        getCurrentSession().save(newEntity);
        return true;        
    }

    public boolean update(BaseAuditVersioning editedEntity, String user) {   
        System.out.println("editedEntity.getIver() : "+editedEntity.getIver());
        editedEntity.setLastModBy(user);
        editedEntity.setLastModDate(new Date());
        getCurrentSession().update(editedEntity);
        return true;        
    }
}
