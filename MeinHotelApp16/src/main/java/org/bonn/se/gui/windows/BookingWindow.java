/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.se.gui.windows;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.sql.Date;
import org.bonn.se.model.objects.dto.Hotel;
import org.bonn.se.modell.dao.BookingRequest;
import org.bonn.se.process.control.BookingProcess;

/**
 *
 * @author Mario<Mario@Mario-PC>
 */
public class BookingWindow extends Window {
    
    public BookingWindow( final Hotel hotel ) {
        super("Buchung"); // set window caption
        center();
   
        // some basic content for the window
        VerticalLayout content = new VerticalLayout();
        content.addComponent( new Label( "Buchung fuer Hotel: " + hotel.getName() ));
        content.setMargin(true);
        setContent(content);
        
        final DateField dateAnreise = new DateField();
        content.addComponent(dateAnreise);
        dateAnreise.setCaption("Anreise: ");
        dateAnreise.setDateFormat("yyyy-MM-dd");
        dateAnreise.setValue(new java.util.Date() ); 
    
        final DateField dateAbreise = new DateField();
        content.addComponent(dateAbreise);
        dateAbreise.setCaption("Abreise: ");
        dateAbreise.setDateFormat("yyyy-MM-dd");
        dateAbreise.setValue(new java.util.Date() ); 
        
        final ComboBox personNumber = new ComboBox();
        personNumber.setCaption("Anzahl Personen: ");
        content.addComponent(personNumber);
        personNumber.addItem( new Integer(1) );
        personNumber.addItem( new Integer(2) );
        personNumber.addItem( new Integer(3) );
        
        final TextField ibanFeld = new TextField();
        ibanFeld.setCaption("IBAN: ");
        content.addComponent(ibanFeld);
        
        Label emptyLabel = new Label("&nbsp;", ContentMode.HTML);
        content.addComponent( emptyLabel);
        
        // um das Fenster jederzeit schliessen zu koennen
        setClosable(true);
        
        // Implementierung Button
        Button  buchungsButton = new Button("Buche");
        buchungsButton.addClickListener( new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
               
                BookingRequest request = new BookingRequest();
                request.setAnreise( dateAnreise.getValue() );
                request.setAbreise( dateAbreise.getValue() );
                request.setIban( ibanFeld.getValue() );
                request.setNumber( (Integer) personNumber.getValue() );
                request.setHotel(hotel);
                
                // Video 13, Zeitpunkt: 13.47
                BookingProcess.getInstance().createBooking(request, BookingWindow.this );
            }
            
        });
        
        content.addComponent(buchungsButton);
        content.setComponentAlignment(buchungsButton, Alignment.MIDDLE_CENTER );
    }
}
