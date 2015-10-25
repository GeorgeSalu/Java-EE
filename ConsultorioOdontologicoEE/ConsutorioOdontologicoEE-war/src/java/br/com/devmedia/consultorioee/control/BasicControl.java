/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.devmedia.consultorioee.control;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public abstract class BasicControl implements java.io.Serializable {

      protected void createFacesErrorMessage(String msg) {
        FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
}
