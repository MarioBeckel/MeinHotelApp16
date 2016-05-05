/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.se.gui.windows;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.List;
import org.bonn.se.model.objects.dto.BookingDetail;
import org.bonn.se.process.control.BookingProcess;


/**
 *
 * @author Mario<Mario@Mario-PC>
 */
public class ListBookingWindow extends Window {
    
    private int currentID;
    private List<BookingDetail> liste;
    
    public ListBookingWindow() {
        super( "Liste Alle Buchungen"); // set window caption
        center();
        
        VerticalLayout layout = new VerticalLayout();
        
        // erzeuge die Tabelle anhand des dto BookingDetail
        final BeanContainer<Integer, BookingDetail> data = new BeanContainer<Integer, BookingDetail>(BookingDetail.class);
        data.setBeanIdProperty("id");
        final Table table = new Table( "Liste ihrer Buchungen:", data);
        table.setSizeFull();
        table.setSelectable(true);
        
        // hole alle Buchungen fuer einen User ab
        liste = BookingProcess.getInstance().getAllBookingForUser();
        
        // befuelle die Daten in die Tabelle (einfaches Datenmodel)
        data.addAll(liste);
        table.setPageLength(table.size());
        this.setSizeFull();
        layout.addComponent(table);
        
        Button deleteButton = new Button( "Storniere Reise ");
        deleteButton.addClickListener( new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                // loesche die Buchung
                BookingProcess.getInstance().deleteBookingByID(currentID);
                
                // lokale Loeschung der Tabelle
                data.removeAllItems();
                
                // Tabelle neu laden und darstellen (sichere Variante)
                liste = BookingProcess.getInstance().getAllBookingForUser();
                data.addAll( liste );
                table.setPageLength( table.size() );
            }
        });
        Label emptyLabel = new Label( "&nbsp;", ContentMode.HTML);
        layout.addComponent( emptyLabel );
        layout.addComponent( deleteButton );
        layout.setComponentAlignment( deleteButton, Alignment.MIDDLE_CENTER);
        
        table.addItemClickListener( new ItemClickEvent.ItemClickListener() {
        @Override
        public void itemClick(ItemClickEvent itemClickEvent) {
            System.out.println( itemClickEvent.getItemId() );
            ListBookingWindow.this.currentID = (Integer) itemClickEvent.getItemId();
            }
        });
        this.setContent(layout);    
    }
}  
    

