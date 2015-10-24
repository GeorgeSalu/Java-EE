/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.service.repository;

import br.com.devmedia.consultorioee.entities.Users;
import java.security.MessageDigest;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author George
 */
public class UserRepository extends BasicRepository{

    private static final long serialVersionUID = 1L;

    public UserRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Users getUser(int id) {
        return getEntity(Users.class,id);
    }
    
    public Users setUser(Users usr) {
        return setEntity(Users.class, usr);
    }
    
    public void removeUser(Users usr) {
        removeEntity(usr);
    }
    
    public Users addUser(Users usr) {
        usr.setUsuPassword(getMd5(usr.getUsuPassword()));
        addEntity(Users.class, usr);
        return usr;
    }

    public Users getUserByLoginPassword(String login, String password) {
        return getPurePojo(Users.class, "select usr from Users usr where usr.usuLogin = ?1 and usr.usuPassword = ?2", login, getMd5(password));
    }

    public List<Users> getUsers() {
        return getPureList(Users.class, "select usr from Users usr");
    }
    
    public List<Users> getUsersByName(String name) {
        return getPureList(Users.class,"select usr from Users usr where usr.usuName like ?1",name+"%");
    }
    
    public void setPassword(String newPassword,int idOfUser) {
        String np = getMd5(newPassword);
        Users usr = getUser(idOfUser);
        usr.setUsuPassword(np);
        setUser(usr);
    }
    
    private String getMd5(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return digest;
    }
    
}
