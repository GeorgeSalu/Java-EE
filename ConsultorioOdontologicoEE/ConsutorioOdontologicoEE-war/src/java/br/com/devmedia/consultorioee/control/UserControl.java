/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Users;
import br.com.devmedia.consultorioee.service.UserService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


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

    
/*    @NotEmpty(message = "Voc� precisa especificar um nome v�lido")
    @NotNull(message = "Voc� precisa especificar um nome v�lido")
    @Length(min=3,message = "Voc� deve especificar um nome com mais de 3 letras.")*/
    private String localizar;
    private List<Users> usrFiltrado;
    
    private Users usuarioSelected;

    public Users getUsuarioSelected() {
        return usuarioSelected;
    }

    public void setUsuarioSelected(Users usuarioSelected) {
        this.usuarioSelected = usuarioSelected;
    }
    
    public Users getLoggedUser() {
        return loggedUser;
    }

    public String doLocalizar() {
        usrFiltrado = userService.getUsersByName(getLocalizar());
        return "users.faces";
    }
    
    public void setLoggedUser(Users loggedUser) {
        this.loggedUser = loggedUser;
    }

    @PostConstruct
    public void postContrsuct() {
        System.out.println("[DevMedia] UserControl Started ! "+hashCode());
    }

    public List<Users> getUsers() {
        return userService.getUsers();
    }
    
    public String doLogin() {
        loggedUser = null;
        loggedUser = userService.getUserByLoginPassword(userName, password);
        if (loggedUser == null) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario / Senha Inv�lidos", "Usuario / Senha Inv�lidos");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "/login.faces";
        } else {
            return "/restrito/index.faces?faces-redirect=true";
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

    public String getLocalizar() {
        return localizar;
    }

    public void setLocalizar(String localizar) {
        this.localizar = localizar;
    }

    public List<Users> getUsrFiltrado() {
        if (usrFiltrado == null) {
            return getUsers();
        }
        return usrFiltrado;
    }

    public void setUsrFiltrado(List<Users> usrFiltrado) {
        this.usrFiltrado = usrFiltrado;
    }
    
    public String doStartAddUsuario() {
        setUsuarioSelected(new Users());
        return "/restrito/addUser.faces";
    }
    
    public String doFinishAddUsuario() {
        setUsrFiltrado(null);
        userService.addUser(usuarioSelected);
        return "/restrito/users.faces";
    }
    
    public String doFinishExcluir() {
        return "/restrito/users.faces";
    }

    public String doStartAlterar() {
        return "/restrito/editUser.faces";
    }
    public String doStartAlterarSenha() {
        return "/restrito/editUserPassword.faces";
    }        
}
