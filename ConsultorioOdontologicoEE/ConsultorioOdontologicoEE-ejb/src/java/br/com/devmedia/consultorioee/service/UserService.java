/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.service;

import br.com.devmedia.consultorioee.entities.Users;
import br.com.devmedia.consultorioee.service.repository.UserRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author George
 */
@Stateless
@LocalBean
public class UserService extends BasicService{
    
    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;
    private UserRepository usrRepository;
    
    @PostConstruct
    @PostActivate
    private void postConstruct() {
        usrRepository = new UserRepository(em);
    }

    public UserService() {
    
    }
    
    public Users getUser(int id) {
        return usrRepository.getUser(id);
    }
    
    public Users setUser(Users user) {
        return usrRepository.setUser(user);
    }
    
    public void removeUser(Users user) {
        usrRepository.removeUser(user);
    }
    
    public void setPassword(int ifOfUser,String password) {
        usrRepository.setPassword(password, ifOfUser);
    }
    
    public Users addUser(Users user) {
        return usrRepository.addUser(user);
    }
    
    public Users getUserByLoginPassword(String login,String password) {
        return usrRepository.getUserByLoginPassword(login, password);
    }
    
    public List<Users> getUsers() {
        return usrRepository.getUsers();
    }
    
}