          /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.se.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bonn.se.gui.ui.MyUI;
import org.bonn.se.model.objects.dto.User;
import org.bonn.se.process.control.LoginControl;
import org.bonn.se.process.control.exceptions.DatabaseException;
import org.bonn.se.process.control.exceptions.NoSuchUserOrPassword;
import org.bonn.se.services.util.Roles;
import org.bonn.se.services.util.Views;

/**
 *
 * @author Mario<Mario@Mario-PC>
 */
public class LoginView extends VerticalLayout implements View {

    public void setUp() {
        // Label label = new Label("Hello Login");
        // this.addComponent(label);

        this.setSizeFull();
        
        final TextField userLogin = new TextField();
        userLogin.setCaption("UserID: ");
        
        final PasswordField passwordField = new PasswordField();
        passwordField.setCaption("Passwort: ");
        
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(userLogin);
        layout.addComponent(passwordField);
        //Leerzeile definieren
        Label label = new Label( "&nbsp;" , ContentMode.HTML);
        layout.addComponent(label);
        
        Panel panel = new Panel( "Bitte Login-Daten angeben: ");
        panel.addStyleName("login");
        
        this.addComponent(panel);
        this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
        
        panel.setContent( layout );
        
        Button buttonLogin = new Button("Login", FontAwesome.SEARCH);
        layout.addComponent(buttonLogin);
        layout.setComponentAlignment(buttonLogin, Alignment.MIDDLE_CENTER);
        
        panel.setSizeUndefined();
        
        buttonLogin.addClickListener( new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                String login = userLogin.getValue();
                String password = passwordField.getValue();
                
                try {
                    LoginControl.checkAuthentification(login, password);
                } catch (NoSuchUserOrPassword ex) {
                   
                    Notification.show("Fehler", "Login oder Password falsch", Notification.Type.ERROR_MESSAGE);
                    userLogin.setValue("");
                    passwordField.setValue("");
                } catch (DatabaseException ex) {
                    Notification.show("DB-Fehler", ex.getReason(), Notification.Type.ERROR_MESSAGE);
                    userLogin.setValue("");
                    passwordField.setValue("");

                    //Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    };
     
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        
//          User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
        User user = ((MyUI) UI.getCurrent()).getUser();
        
        if ( user != null ){
            UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
        } else {
          this.setUp();  
        }
     }
    
}
