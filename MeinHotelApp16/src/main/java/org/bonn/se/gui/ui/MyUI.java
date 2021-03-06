package org.bonn.se.gui.ui;
//Komentar
import com.vaadin.annotations.PreserveOnRefresh;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import org.bonn.se.gui.views.LoginView;
import org.bonn.se.gui.views.MainView;
import org.bonn.se.model.objects.dto.Hotel;
import org.bonn.se.model.objects.dto.User;
import org.bonn.se.process.control.HotelSearch;
import org.bonn.se.services.util.Views;
/**
 *
 */
@Theme("mytheme")
@Widgetset("org.bonn.se.meinhotelapp16.MyAppWidgetset")
@Title("MeinHotel")
@PreserveOnRefresh 
public class MyUI extends UI {
    
    private User user = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
        System.out.println("LOG: neues UI-Objekt erzeugt. Session-ID: " + VaadinSession.getCurrent().toString() );
        
        Navigator navi = new Navigator( this , this );
        
        navi.addView(Views.MAIN, MainView.class);
        navi.addView(Views.LOGIN, LoginView.class);
        
        UI.getCurrent().getNavigator().navigateTo(Views.LOGIN)  ;
    }

    public MyUI getMyUI() {
        return (MyUI) UI.getCurrent();
    }
    
    
    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
 