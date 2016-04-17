/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.se.process.control;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bonn.se.model.objects.dto.User;
import org.bonn.se.process.control.exceptions.DatabaseException;
import org.bonn.se.process.control.exceptions.NoSuchUserOrPassword;
import org.bonn.se.services.db.JDBCConnection;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

/**
 *
 * @author Mario<Mario@Mario-PC>
 */
public class LoginControl {
    
    public static void checkAuthentification( String login, String password ) throws NoSuchUserOrPassword, DatabaseException {
        
        ResultSet set = null;
        
        try {
            // DB-Zugriff (TODO)
            Statement statement = JDBCConnection.getInstance().getStatement();
            
            set = statement.executeQuery( "SELECT *"
                    + "FROM realm.user"
                    + "WHERE user.login = \'" + login + "\'"
                    + "AND realm.user.password = \'" + password + "\'");
            
            // UI.getCurrent().getNavigator().navigateTo(Views.MAIN);

        } catch (SQLException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        User user = null;
        
        try {
            if ( set.next() ) {
                user = new User();
                user.setLogin( set.getString(1) );   // 1 = 1.Spalte in Datennank
                user.setVorname( set.getString(3) ); // 3 = 3.Spalte in Datenbank
                
            } else {
                throw new NoSuchUserOrPassword();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            JDBCConnection.getInstance().closeConnection();
        }
        VaadinSession session = UI.getCurrent().getSession();
        session.setAttribute( Roles.CURRENT_USER, user );
        
        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
        
    } 
    
}