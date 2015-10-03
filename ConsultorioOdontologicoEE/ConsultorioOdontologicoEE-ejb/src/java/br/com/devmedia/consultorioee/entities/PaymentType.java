/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.devmedia.consultorioee.entities;

/**
 *
 * @author George
 */
public enum PaymentType {
    
    DINHEIRO("Dinheiro"),CREDITO("Crédito"),DEBITO("Débito");
    
    private final String description;
    
    PaymentType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
}
