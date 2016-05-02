/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.se.gui.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import org.bonn.se.gui.ui.MyUI;
import org.bonn.se.model.objects.dto.User;
import org.bonn.se.process.control.LoginControl;
import org.bonn.se.process.control.exceptions.DatabaseException;
import org.bonn.se.services.util.Roles;

/**
 *
 * @author Mario<Mario@Mario-PC>
 */
public class TopPanel extends HorizontalLayout {

    public TopPanel() throws DatabaseException {
        
        this.setSizeFull();
        
        Label headLabel = new Label("MeinHotel - <i>das Reservierungssystem</i>", ContentMode.HTML);
        headLabel.setSizeUndefined();
        headLabel.addStyleName("mytitle");
        
        this.addComponent(headLabel);
        this.setComponentAlignment(headLabel, Alignment.TOP_LEFT);
        
        HorizontalLayout horLayout = new HorizontalLayout();
        
//        User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
        
        User user = ((MyUI) UI.getCurrent()).getUser();
        
        String vorname = null;
        if( user != null ) {
            vorname = user.getVorname();
        }
        
        
        Label loggedLabel = new Label( "Welcome: " + vorname + "!" );
        loggedLabel.setSizeUndefined();
        loggedLabel.addStyleName("logdedLabel");
        
        horLayout.addComponent(loggedLabel);
        horLayout.setComponentAlignment(loggedLabel, Alignment.MIDDLE_CENTER);

        MenuBar bar = new MenuBar();
        MenuItem item1 = bar.addItem("Menu", null);
    
        // logOut des Users: TODO
        item1.addItem("Logout", FontAwesome.SIGN_OUT, new MenuBar.Command() {

            @Override
            public void menuSelected(MenuItem selectedItem) {
                LoginControl.logoutUser(); 
            }
        });
        
        // Stornierung von Reisen (PowerUser) - TODO
        
        if ( user.hasRole (Roles.POWER_USER) ) {
            
        item1.addItem("Cancel", FontAwesome.UNLINK, new MenuBar.Command() {

            @Override
            public void menuSelected(MenuItem selectedItem) {
                // TODO ein Window wird geoeffnet um Buchnungen anzuzeigen und ggf. zu stornieren
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                
            }
        } );
        
        }
        horLayout.addComponent(bar); 
        this.addComponent(horLayout);
        this.setComponentAlignment(horLayout, Alignment.TOP_RIGHT);

        
        
    }
    
}
