/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.se.model.objects.dto;

/**
 *
 * @author Mario<Mario@Mario-PC>
 */
public class Role {
    
    private String bezeichnung = null;
    
    public String getBezeichnung(){
        return bezeichnung;
    }
    
    public void setBezeichnung(String bezeichnung){
        this.bezeichnung = bezeichnung;
    }
}
