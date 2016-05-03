/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.se.modell.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bonn.se.process.control.exceptions.DatabaseException;
import org.bonn.se.services.db.JDBCConnection;

/**
 *
 * @author Mario<Mario@Mario-PC>
 */
public class AbstractDAO {
   
    protected Statement getStatement(){
            Statement statement = null;
        try {
            statement = JDBCConnection.getInstance().getStatement();
        } catch (DatabaseException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return statement;  
    }
    
    protected PreparedStatement getPreparedStatement( String sql) {
        Statement statement = null;
        try {
            statement = JDBCConnection.getInstance().getPreparedStatement(sql); 
        } catch (DatabaseException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (PreparedStatement) statement;     
    }
}