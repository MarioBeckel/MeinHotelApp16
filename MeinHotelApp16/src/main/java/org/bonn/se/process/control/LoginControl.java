/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.se.process.control;

import com.vaadin.ui.UI;
import org.bonn.se.process.control.exceptions.NoSuchUserOrPassword;
import org.bonn.se.services.util.Views;

/**
 *
 * @author Mario<Mario@Mario-PC>
 */
public class LoginControl {
    
    public static void checkAuthentification( String login, String password ) throws NoSuchUserOrPassword {
        
        // DB-Zugriff
        
        // Der Benutzer ist vorhanden
        // UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
         
         // Fehlerfall
         throw new NoSuchUserOrPassword();
    } 
    
}
