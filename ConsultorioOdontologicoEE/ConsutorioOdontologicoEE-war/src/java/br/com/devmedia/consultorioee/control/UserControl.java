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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

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

    @NotNull(message = "Voc� deve especificar o usu�rio")
    @NotEmpty(message = "Voc� deve especificar o usu�rio")
    private String userName;
    @NotNull
    @NotEmpty(message = "Voc� precisa especificar uma senha")
    @Length(min = 3,message = "Sua senha deve conter no minimo 3 caracteres.")
    private String password;

    public Users getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Users loggedUser) {
        this.loggedUser = loggedUser;
    }

    
    public String doLogin() {
        loggedUser = null;
        loggedUser = userService.getUserByLoginPassword(userName, password);
        if (loggedUser == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario / Senha Inv�lidos", "Usuario / Senha Inv�lidos");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "/login.faces";
        } else {
            return "/index.faces";
        }
        
    }
    
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}