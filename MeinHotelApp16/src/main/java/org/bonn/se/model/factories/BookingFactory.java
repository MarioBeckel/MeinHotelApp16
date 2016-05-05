/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.se.model.factories;

import java.util.Date;
import org.bonn.se.model.objects.dto.User;
import org.bonn.se.model.objects.entities.Booking;
import org.bonn.se.modell.dao.BookingRequest;

/**
 *
 * @author Mario<Mario@Mario-PC>
 */
public class BookingFactory {
    
    public static Booking createBooking( BookingRequest request, User user ) {
        Booking book = new Booking();
        
        book.setAbreise( request.getAbreise() );
        book.setAnreise( request.getAnreise() );
        book.setHotel( request.getHotel() );
        book.setIban( request.getIban() );
        book.setNumber( request.getNumber() );
        
        // User gehoert zu einer Buchung (siehe ER-Modell
         book.setUser(user);
        
        // zusaetzliches Attribut
        book.setDatumBuchung( new Date() );
        
        // book.setID ... wird spaeter bei der Ablage in die Datenbank hinzugefuegt
        
        return book;
    }
}
