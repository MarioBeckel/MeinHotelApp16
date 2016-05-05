/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.se.gui.windows;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 *
 * @author Mario<Mario@Mario-PC>
 */
public class ConfirmationWindow extends Window {
  
    public ConfirmationWindow( String text ) {
        super("Confirmation"); // set Window Caption
        center();
        
        // some basic content for the window
        VerticalLayout content = new VerticalLayout();
        content.addComponent( new Label( text ));
        content.setMargin(true);
        setContent(content);
        
        Button buchungsButton = new Button( "OK" );
        buchungsButton.addClickListener( new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                close();
            }
        });
        content.addComponent(buchungsButton);
        content.setComponentAlignment( buchungsButton, Alignment.MIDDLE_CENTER);
    }
}
