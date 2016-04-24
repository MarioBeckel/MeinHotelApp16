/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.se.services.db;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import org.bonn.se.process.control.exceptions.DatabaseException;

/**
 * 
 * @author sascha
 */
public class JDBCConnection {
    
//   public static void main( String[] args ) {
//        JDBCConnection connection = new JDBCConnection();
//        try {
//            connection.test();
//        } catch ( SQLException ex ) {
//            Logger.getLogger( JDBCConnection.class.getName()).log(Level.SEVERE, null, ex );
//        }
//    }
    
   private static JDBCConnection connection = null;
    
    private String url = "jdbc:postgresql://dumbo.inf.fh-bonn-rhein-sieg.de/mmoers2s";
   // private String url = "jdbc:postgresql://localhost//phppgadmin/Mario";
    
    
    private Connection conn;
    
    private String login = "mmoers2s";
    
    private String password = "mmoers2s";
    
    public static JDBCConnection getInstance() throws DatabaseException {
        
        if ( connection == null ) {
            connection = new JDBCConnection();
        }
        return connection;
    }
    
    private JDBCConnection() throws DatabaseException {
        this.initConnection();
         
    }
    
    public void initConnection() throws DatabaseException  {
        try { 
            DriverManager.registerDriver( new org.postgresql.Driver() );
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.openConnection();
    }
    
    public void openConnection() throws DatabaseException{
       
        try {
    
            Properties props = new Properties();
            props.setProperty("user", "mmoers2s");
            props.setProperty("password", "mmoers2s");
            
            this.conn = DriverManager.getConnection(this.url, props);
         } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException(( "Fehler bei Zugriff auf die DB! Sichere Verbindung vorhanden!?"));
        }
    }   
    
    public Statement getStatement() throws DatabaseException {

        try {
            if ( this.conn.isClosed() ) {
                this.openConnection();
            }
            
            return this.conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
        public PreparedStatement getPreparedStatement(String sql) throws DatabaseException{
        try {
            if(this.conn.isClosed()) {
                this.openConnection();
            }
            return this.conn.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void closeConnection() {
        
        try {
            this.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
