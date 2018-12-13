/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Namko
 */
class DataAccess {
    
        /**
         * connection to the DB
         * @return
         * @throws SQLException 
         */
        public static Connection DBConnect() throws SQLException, ClassNotFoundException{
            
            String dbUrl = "";
            String dbUser = "";
            String dbMdp = "";
            Class.forName("com.mysql.jdbc.Driver");
            Properties prop = new Properties();
            InputStream input = null;
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            input = classLoader.getResourceAsStream("util/dbProperties.properties");
            
            try {
                prop.load(input);
            } catch (IOException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            dbUrl = prop.getProperty("url");
            dbUser = prop.getProperty("user");
            dbMdp = prop.getProperty("pwd");
            
            Connection dbConn = DriverManager.getConnection(dbUrl, dbUser, dbMdp);
            return dbConn;
    }
        
}
