/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author benjamin
 */
@Entity
@Table(name = "IDENTIFIANTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Identifiants.findAll", query = "SELECT i FROM Identifiants i")
    , @NamedQuery(name = "Identifiants.findByLogin", query = "SELECT i FROM Identifiants i WHERE i.login = :login")
    , @NamedQuery(name = "Identifiants.findByMdp", query = "SELECT i FROM Identifiants i WHERE i.mdp = :mdp")})
public class Identifiants implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "LOGIN")
    private String login;
    @Size(max = 232)
    @Column(name = "MDP")
    private String mdp;

    public Identifiants() {
    }

    public Identifiants(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Identifiants)) {
            return false;
        }
        Identifiants other = (Identifiants) object;
        if ((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Identifiants[ login=" + login + " ]";
    }
    
}
