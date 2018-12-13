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
    
    /**
     * 
     * @return login
     */
    public String getLogin(){
        return this.login;
    }
    
    /**
     * 
     * @return mdp
     */
    public String getMdp(){
        return this.mdp;
    }
    
    /**
     * set login
     * @param login 
     */
    public void setLogin(String login){
        this.login = login;
    }
    
    /**
     * set mdp
     * @param mdp 
     */
    public void setMdp(String mdp){
        this.mdp = mdp;
    }
}
