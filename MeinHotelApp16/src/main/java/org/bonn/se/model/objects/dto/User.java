package org.bonn.se.model.objects.dto;

import java.io.Serializable;
import java.util.List;
//import org.bonn.se.model.dao.RoleDAO;
import org.bonn.se.process.control.exceptions.DatabaseException;
//import org.lodshop.se.model.dao.RoleDAO;

/**
 *  TODO Klären was das Serializable soll. Im Tutorial bis Kapitel 12 wird es noch nicht erklärt (Frank)
 */
public class User implements Serializable {
    
    private String vorname = null;
    private String login = null;
//    private List<Role> roles = null;

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

/*    public boolean hasRole(String role) throws DatabaseException {
        
        //Lazy Load
        
        if(this.roles == null) {
            getRoles();
        }
        
        for (Role r : roles) {
//            System.out.println(r.getBezeichnung());
            if(r.getBezeichnung().equals(role)) return true;
        }
        return false;
    }

    private void getRoles() throws DatabaseException {
       
        this.roles =  RoleDAO.getInstance().getRolesForUser(this);
        
    }
*/    
    
}
