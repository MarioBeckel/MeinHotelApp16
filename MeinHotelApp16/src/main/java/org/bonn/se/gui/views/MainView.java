/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.se.gui.views;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import org.bonn.se.gui.ui.MyUI;
import org.bonn.se.model.objects.dto.Hotel;
import org.bonn.se.model.objects.dto.User;
import org.bonn.se.process.control.HotelSearch;
import org.bonn.se.services.util.Roles;

/**
 *
 * @author Mario<Mario@Mario-PC>
 */
public class MainView extends VerticalLayout implements View{
    private Hotel hotelSelektiert = null;
    private int anzahlSuche = 0;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setUp();
    }

    public void setUp() {

        setMargin(true);

        final TextField textField = new TextField();
        Button button = new Button("Suche ", FontAwesome.SEARCH);
        setSpacing(true);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        
        User user = (User) UI.getCurrent().getSession().getAttribute( Roles.CURRENT_USER );
        
        Label labelText = new Label( user.getVorname() + ", gebe den Ort ein: ");
        //Label labelText = new Label( user.getVorname() + ", gebe den Ort ein: ");
        horizontalLayout.addComponent(labelText);
        horizontalLayout.setComponentAlignment(labelText, Alignment.MIDDLE_CENTER);
        horizontalLayout.addComponent(textField);
        horizontalLayout.addComponent(new Label("&nbsp", ContentMode.HTML));
        horizontalLayout.addComponent(button);
        addComponent(horizontalLayout);
        setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);
        final BeanContainer<Integer, Hotel> data = new BeanContainer<Integer, Hotel>(Hotel.class);
        data.setBeanIdProperty("id");
        
        final Table table = new Table("Treffer", data);
        //caption with HTML elements!!!
        table.setCaptionAsHtml(true);
        //change column header
        table.setColumnHeader("id", "ID");
        table.setColumnHeader("beschreibung", "Beschreibung");
        table.setColumnHeader("name", "Name");
        table.setColumnHeader("ort", "Ort");
        // Ausrichtung der Tabellenueberschrift
        table.setColumnAlignments(Table.Align.CENTER, Table.Align.CENTER, Table.Align.CENTER, Table.Align.CENTER  );
        
        
        
        table.setSizeFull();
        table.setSelectable(true);
        final Button bucheButton = new Button("Buche");
        bucheButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent envent) {
                if (MainView.this.hotelSelektiert == null) {
                    return;
                } else {
                    System.out.println("Hotel selektiert " + MainView.this.hotelSelektiert.getName());
                }
            }
        });
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                System.out.println("Zeile selektiert: " + event.getItemId().toString());
                BeanItem<Hotel> hotelBean = data.getItem(event.getItemId());
                Hotel hotelSelektiert = hotelBean.getBean();
            }
        });
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                String ort = textField.getValue();
                if (ort.equals("")) {
                    Notification.show(null, "Bitte Ort eingeben!", Notification.Type.WARNING_MESSAGE);
                } else {
                    addComponent(table);
                    MainView.this.anzahlSuche++;
                    table.setCaption("Treffer f\u00fcr " + ort + " (Anzahl der Suche: " + MainView .this.anzahlSuche + ")");
                    List<Hotel> liste = HotelSearch.getInstance().getHotelByOrt(ort);
                    data.removeAllItems();
                    data.addAll(liste);
                    table.setPageLength(table.size());
                    addComponent(bucheButton);
                    setComponentAlignment(bucheButton, Alignment.MIDDLE_CENTER);
                }
            }
        });
    }
    
}
