/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.persistencia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hisae Shizumaru
 */
@Entity
@Table(name = "tipousuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipousuario.findAll", query = "SELECT t FROM Tipousuario t"),
    @NamedQuery(name = "Tipousuario.findByTipUsuCod", query = "SELECT t FROM Tipousuario t WHERE t.tipUsuCod = :tipUsuCod"),
    @NamedQuery(name = "Tipousuario.findByTipUsuNom", query = "SELECT t FROM Tipousuario t WHERE t.tipUsuNom = :tipUsuNom"),
    @NamedQuery(name = "Tipousuario.findByTipUsuDes", query = "SELECT t FROM Tipousuario t WHERE t.tipUsuDes = :tipUsuDes")})
public class Tipousuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TipUsuCod")
    private Integer tipUsuCod;
    @Basic(optional = false)
    @Column(name = "TipUsuNom")
    private String tipUsuNom;
    @Column(name = "TipUsuDes")
    private String tipUsuDes;
    @OneToMany(mappedBy = "tipUsuCod")
    private Collection<Usuario> usuarioCollection;

    public Tipousuario() {
    }

    public Tipousuario(Integer tipUsuCod) {
        this.tipUsuCod = tipUsuCod;
    }

    public Tipousuario(Integer tipUsuCod, String tipUsuNom) {
        this.tipUsuCod = tipUsuCod;
        this.tipUsuNom = tipUsuNom;
    }

    public Integer getTipUsuCod() {
        return tipUsuCod;
    }

    public void setTipUsuCod(Integer tipUsuCod) {
        this.tipUsuCod = tipUsuCod;
    }

    public String getTipUsuNom() {
        return tipUsuNom;
    }

    public void setTipUsuNom(String tipUsuNom) {
        this.tipUsuNom = tipUsuNom;
    }

    public String getTipUsuDes() {
        return tipUsuDes;
    }

    public void setTipUsuDes(String tipUsuDes) {
        this.tipUsuDes = tipUsuDes;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipUsuCod != null ? tipUsuCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipousuario)) {
            return false;
        }
        Tipousuario other = (Tipousuario) object;
        if ((this.tipUsuCod == null && other.tipUsuCod != null) || (this.tipUsuCod != null && !this.tipUsuCod.equals(other.tipUsuCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.Tipousuario[ tipUsuCod=" + tipUsuCod + " ]";
    }
    
}
