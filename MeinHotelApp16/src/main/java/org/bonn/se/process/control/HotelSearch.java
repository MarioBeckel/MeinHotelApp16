/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.se.process.control;

import java.util.ArrayList;
import java.util.List;
import org.bonn.se.model.objects.dto.Hotel;

/**
 *
 * @author Ketchupp
 */
public class HotelSearch {
    Hotel hotel1 = new Hotel(1, "Hotel Maier", "Köln", "Ein schönes Hotel");
    Hotel hotel2 = new Hotel(2, "Hotel Maritim", "Bonn", "Ein wunderbares Hotel");
    Hotel hotel3 = new Hotel(3, "Hotel Königshof", "Bonn", "Zentrales Hotel");
    
    private HotelSearch() {
        //
    }
    
    public static HotelSearch search = null;
    
    public static HotelSearch getInstance() {
        if(search == null) {
            search = new HotelSearch();
        }    
        return search;
    }
    
    public List<Hotel> getHotelByOrt(String ort) {
        List<Hotel> searchResult = new ArrayList<>();
        if(ort.equals("Köln")) {
            searchResult.add(hotel1);
        }
        if(ort.equals("Bonn")) {
            searchResult.add(hotel2);
            searchResult.add(hotel3);
        }
        return searchResult;
    }
}
