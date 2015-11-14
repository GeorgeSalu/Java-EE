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
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author George
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
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
    
    public Users setUser(Users user) throws AcessoInvalidoException {
        if (!user.getUsuAdministrator() && !user.getUsuDentist()) {
            throw new AcessoInvalidoException("O usuário atual não possui um acesso válido.");
        }
        return usrRepository.setUser(user);
    }
    
    public void removeUser(Users user) {
        usrRepository.removeUser(user);
    }
    
    public void setPassword(int ifOfUser,String password) {
        usrRepository.setPassword(password, ifOfUser);
    }
    
    public Users addUser(Users user) throws AcessoInvalidoException {
        if (!user.getUsuAdministrator() && !user.getUsuDentist()) {
            throw new AcessoInvalidoException("O usuário atual não possui um acesso válido.");
        }
        return usrRepository.addUser(user);
    }
    
    
    public List<Users> getUsersByName(String name) {
        return usrRepository.getUsersByName(name);
    }
    
    public Users getUserByLoginPassword(String login,String password) {
        try {
            return usrRepository.getUserByLoginPassword(login, password);
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Users> getUsers() {
        return usrRepository.getUsers();
    }
    public List<Users> getDentistUsers() {
        return usrRepository.getDentistUsers();
    }

    public Users getUsersByExactName(String value) {
        return usrRepository.getUsersByExactName(value);
    }
    
}
