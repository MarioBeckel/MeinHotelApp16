/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.se.model.objects.dto;

/**
 *
 * @author Ketchupp
 */
public class Hotel implements java.io.Serializable{
    
    private Integer id;
    private String name;
    private String ort;
    private String beschreibung;
    
    public Hotel(Integer id, String name, String ort, String beschreibung){
        this.id = id;
        this.name = name;
        this.ort = ort;
        this.beschreibung = beschreibung;
    }

    public Hotel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
    
}
