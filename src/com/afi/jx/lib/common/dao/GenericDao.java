/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afi.jx.lib.common.dao;

import com.afi.jx.lib.common.model.BaseAuditVersioning;
import java.io.Serializable;

/**
 *
 * @author AFI
 */
public interface GenericDao <T, K extends Serializable>{
    
    /**
     * might not hit db, return proxy placeholder, throw ex if no rec found
     */
    T load(K key);
        
    /**
     * always hit db, might return null if no rec found
     */
    T get(K key);
    
    boolean delete(K key);
    
    boolean save(T newEntity);
    boolean save(BaseAuditVersioning newEntity,String user);   
    boolean evict(Object entity);    
    boolean update(T editedEntity);    
    boolean update(BaseAuditVersioning editedEntity,String user);    
    void flush();    
    void clear();
    
    
}
