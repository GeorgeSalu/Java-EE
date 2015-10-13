/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Users;
import br.com.devmedia.consultorioee.service.UserService;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Dyego Souza do Carmo
 * @version 1.0
 * @since 06/2014
 */

@Named
@SessionScoped
public class UserControl extends BasicControl implements java.io.Serializable {

    @EJB
    private UserService userService;
    private Users loggedUser;
    
    private String userName;
    private String password;
    
}
