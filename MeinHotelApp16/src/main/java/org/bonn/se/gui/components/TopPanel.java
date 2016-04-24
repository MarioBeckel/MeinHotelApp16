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
import org.bonn.se.model.objects.dto.User;
import org.bonn.se.process.control.LoginControl;
import org.bonn.se.services.util.Roles;

/**
 *
 * @author Mario<Mario@Mario-PC>
 */
public class TopPanel extends HorizontalLayout {

    public TopPanel() {
        
        this.setSizeFull();
        
        Label headLabel = new Label("MeinHotel - <i>das Reservierungssystem</i>", ContentMode.HTML);
        headLabel.setSizeUndefined();
        headLabel.addStyleName("mytitle");
        
        this.addComponent(headLabel);
        this.setComponentAlignment(headLabel, Alignment.TOP_LEFT);
        
        HorizontalLayout horLayout = new HorizontalLayout();
        
        User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
        
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
        item1.addItem("Cancel", FontAwesome.UNLINK, null);
        
        horLayout.addComponent(bar);
        this.addComponent(horLayout);
        this.setComponentAlignment(horLayout, Alignment.TOP_RIGHT);

        
        
    }
    
}
