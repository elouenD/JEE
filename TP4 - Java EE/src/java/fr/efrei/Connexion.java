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
     
     public String getDbLogin(){
         return this.dbLogin;
     }
     
     public String getDbMdp(){
         return this.dbMdp;
     }
     
     public void setDbLogin(String login){
         this.dbLogin = login;
     }
     
     public void setDbMdp(String mdp){
         this.dbMdp = mdp;
     }
}
