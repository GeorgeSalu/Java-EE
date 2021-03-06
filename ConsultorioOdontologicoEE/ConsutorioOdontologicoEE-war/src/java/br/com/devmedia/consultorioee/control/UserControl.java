/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmedia.consultorioee.control;

import br.com.devmedia.consultorioee.entities.Users;
import br.com.devmedia.consultorioee.entities.validator.LoginPadrao;
import br.com.devmedia.consultorioee.service.AcessoInvalidoException;
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
    @LoginPadrao(message = "Este login n�o est� dentro dos padr�es estabelecidos")
    private String userName;
    @NotNull
    @NotEmpty(message = "Voc� precisa especificar uma senha")
    @Length(min = 3, message = "Sua senha deve conter no minimo 3 caracteres.")
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
        System.out.println("[DevMedia] UserControl Started ! " + hashCode());
    }

    public List<Users> getUsers() {
        return userService.getUsers();
    }

    public String doLogin() {
        loggedUser = null;
        loggedUser = userService.getUserByLoginPassword(userName, password);
        if (loggedUser == null) {
            createFacesErrorMessage("Usuario / Senha Inv�lidos");
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
        try {
            userService.addUser(usuarioSelected);
        } catch (AcessoInvalidoException ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "O acesso deste usu�rio � invalido, favor selecionar o acesso de administrador e/ou dentista.", "O acesso deste usu�rio � invalido, favor selecionar o acesso de administrador e/ou dentista.");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "/restrito/addUser.faces";
        }
        return "/restrito/users.faces";
    }

    public String doFinishExcluir() {
        setUsrFiltrado(null);
        if (usuarioSelected.equals(loggedUser)) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Voc� n�o pode apagar a si mesmo.", "Voc� n�o pode apagar a si mesmo.");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "/restrito/users.faces";
        }
        userService.removeUser(usuarioSelected);
        return "/restrito/users.faces";
    }

    public String doStartAlterar() {
        return "/restrito/editUser.faces";
    }

    public String doFinishAlterar() {
        setUsrFiltrado(null);
        try {
            userService.setUser(usuarioSelected);
        } catch (AcessoInvalidoException ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "O acesso deste usu�rio � invalido, favor selecionar o acesso de administrador e/ou dentista.", "O acesso deste usu�rio � invalido, favor selecionar o acesso de administrador e/ou dentista.");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            return "/restrito/editUser.faces";
        }
        return "/restrito/users.faces";
    }

    public String doStartAlterarSenha() {
        getUsuarioSelected().setUsuPassword("");
        return "/restrito/editUserPassword.faces";
    }

    public String doFinishAlterarSenha() {
        setUsrFiltrado(null);
        userService.setPassword(getUsuarioSelected().getUsuId(), getUsuarioSelected().getUsuPassword());
        return "/restrito/users.faces";
    }

        
}
