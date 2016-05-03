/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.se.modell.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bonn.se.model.objects.dto.Role;
import org.bonn.se.model.objects.dto.User;
import org.bonn.se.process.control.LoginControl;
import org.bonn.se.process.control.exceptions.DatabaseException;
import org.bonn.se.services.db.JDBCConnection;

/**
 *
 * @author Mario<Mario@Mario-PC>
 */
public class RoleDAO extends AbstractDAO {
    
    public static RoleDAO dao = null;
    
    private RoleDAO(){
    }
    
    public static RoleDAO getInstance() {
        if( dao == null ) {
            dao = new RoleDAO();
        }
        return dao;
    }
    
    public List<Role> getRoleForUser (User user) {
        Statement statement = this.getStatement();
         
        ResultSet rs = null;
        
        try {
            rs = statement.executeQuery(
                    "SELECT * "
                + "FROM realm.user "
                + "WHERE realm.user .login = \'" + user.getLogin() + "\'");
        } catch (SQLException ex) {
            //Logger.getLogger(LoginControl.class.getName()),log(Level.SEVERE, null, ex); 
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if ( rs == null ) return null;
        
        List<Role> liste = new ArrayList<Role>();
        Role role = null;
        
        try {
            while (rs.next() ) {
                role = new Role();
                role.setBezeichnung(rs.getString(2) );
                liste.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return liste;
        
    }

    private void log(Level SEVERE, Object object, SQLException ex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
