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
public class Utilisateur {
    private String login;
    private String mdp;
    
    public String getLogin(){
        return this.login;
    }
    
    public String getMdp(){
        return this.mdp;
    }
    
    public void setLogin(String login){
        this.login = login;
    }
    
    public void setMdp(String mdp){
        this.mdp = mdp;
    }
}
