/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.efrei;

/**
 *
 * @author Namko
 */
public class Connexion {
     private String dbLogin;
     private String dbMdp;
     
     /**
      * 
      * @return dbLogin
      */
     public String getDbLogin(){
         return this.dbLogin;
     }
     
     /**
      * 
      * @return dbMdp
      */
     public String getDbMdp(){
         return this.dbMdp;
     }
     
     /**
      * set la valeur de dbLogin
      * @param dbLogin 
      */
     public void setDbLogin(String dbLogin){
         this.dbLogin = dbLogin;
     }
     
     /**
      * set la valeur de dbMdp
      * @param dbMdp 
      */
     public void setDbMdp(String dbMdp){
         this.dbMdp = dbMdp;
     }
}
