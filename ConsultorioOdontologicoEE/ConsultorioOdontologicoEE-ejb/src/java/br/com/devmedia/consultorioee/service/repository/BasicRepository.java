/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.service.repository;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author George
 */
public abstract class BasicRepository {
    
    private static final long serialVersionUID = 1L;
    
    private final EntityManager entityManager;

    public BasicRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    protected EntityManager getEntityManager() {
        return entityManager;
    }
    
    public <T> T refreshEntity(Class<T> classToCast,Object entity) {
        entity = getEntityManager().merge(entity);
        getEntityManager().refresh(entity);
        return (T) entity;
    }
    
    protected <T> T addEntity(Class<T> classToCast,Object entity) {
        getEntityManager().persist(entity);
        return (T) entity; 
    }
    
    protected <T> T getEntity(Class<T> classToCast,Serializable pk) {
        return getEntityManager().find(classToCast, pk);
    }
    
    protected <T> T setEntity(Class<T> classToCast,Object entity) {
        Object updatedObj = getEntityManager().merge(entity);
        return (T) updatedObj;
    }
    
    protected void removeEntity(Object entity) {
        Object updateObj = getEntityManager().merge(entity);
        getEntityManager().remove(updateObj);
    }
    
    protected <T> List<T> getPureList(Class<T> classToCast,String query,Object... values) {
        Query qr = createQuery(query, values);
        return qr.getResultList();
    }

    protected <T> List<T> getPureList(Class<T> classToCast,int limit,String query,Object... values) {
        Query qr = createQuery(query, values);
        qr.setMaxResults(1);
        return qr.getResultList();
    }
    
    protected <T> T getPurePojo(Class<T> classToCast,String query,Object... values) {
        Query qr = createQuery(query, values);
        return (T) qr.getSingleResult();
    }
    
    protected int executeCommand(String query,Object... values) {
        Query qr = createQuery(query, values);
        return qr.executeUpdate();
    }
    
    private Query createQuery(String query,Object... values) {
        Query qr = getEntityManager().createQuery(query);
        for (int i = 0; i < values.length; i++) {
            qr.setParameter((i+1), values[i]);
        }
        return qr;
    }
    
}
