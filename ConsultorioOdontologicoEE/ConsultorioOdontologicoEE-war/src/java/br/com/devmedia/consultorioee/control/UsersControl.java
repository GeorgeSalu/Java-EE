/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Users;
import br.com.devmedia.consultorioee.service.UserService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
/**
 *
 * @author George
 */
@Named
@SessionScoped
public class UsersControl extends BasicControl implements Serializable {

    @EJB
    private UserService userService;
    private Users loggedUser;
    
    private String userName;
    private String password;
    
}

