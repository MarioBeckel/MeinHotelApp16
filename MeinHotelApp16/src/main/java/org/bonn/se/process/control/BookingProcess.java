/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.se.process.control;

import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import java.util.List;
import org.bonn.se.gui.ui.MyUI;
import org.bonn.se.gui.windows.ConfirmationWindow;
import org.bonn.se.model.factories.BookingFactory;
import org.bonn.se.model.objects.dto.BookingDetail;
import org.bonn.se.model.objects.dto.User;
import org.bonn.se.model.objects.entities.Booking;
import org.bonn.se.modell.dao.BookingRequest;
import org.bonn.se.modell.dao.BuchungsDAO;
import org.bonn.se.services.util.Roles;

/**
 *
 * @author Mario<Mario@Mario-PC>
 */
public class BookingProcess {
    
    public static BookingProcess process = null;
    
    private BookingProcess() {
        
    }
    
    public static BookingProcess getInstance() {
        if( process == null ){
            process = new BookingProcess();
        }
        return process;
    }
    
    public void createBooking( BookingRequest request, Window window ) {
        
        //User user = (User) UI.getCurrent().getSession().getAttribute( Roles.CURRENT_USER);
        User user = ( (MyUI) UI.getCurrent()).getUser();
        
        Booking booking = BookingFactory.createBooking(request, user);
        
        boolean result = BuchungsDAO.getInstance().addBooking(booking);
        
        // Navigation auf Basis der (un-)erfolgreichen Buchung
        if( result == true ) {
            window.close();
            UI.getCurrent().addWindow( new ConfirmationWindow( "Buchung erfolgreich! ID: " + booking.getId()) );
        } else {
            // Fehlerhandling 
        } 
    }
    
    public void deleteBookingByID( int id ) {
        BuchungsDAO.getInstance().deleteBookingBy( id );
        UI.getCurrent().addWindow( new ConfirmationWindow( "Die Reise wurde storniert!") );
    }
    
    public List<BookingDetail> getAllBookingForUser() {
        final User user = ((MyUI) UI.getCurrent()).getUser();
        return BuchungsDAO.getInstance().getAllBookingsForUser(user);
    }
}
