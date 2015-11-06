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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "tipo_documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT t FROM TipoDocumento t"),
    @NamedQuery(name = "TipoDocumento.findByTipDocCod", query = "SELECT t FROM TipoDocumento t WHERE t.tipDocCod = :tipDocCod"),
    @NamedQuery(name = "TipoDocumento.findByTipDocDes", query = "SELECT t FROM TipoDocumento t WHERE t.tipDocDes = :tipDocDes")})
public class TipoDocumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TipDocCod")
    private Integer tipDocCod;
    @Basic(optional = false)
    @Column(name = "TipDocDes")
    private String tipDocDes;
    @OneToMany(mappedBy = "tipDocCod")
    private Collection<Gastos> gastosCollection;

    public TipoDocumento() {
    }

    public TipoDocumento(Integer tipDocCod) {
        this.tipDocCod = tipDocCod;
    }

    public TipoDocumento(Integer tipDocCod, String tipDocDes) {
        this.tipDocCod = tipDocCod;
        this.tipDocDes = tipDocDes;
    }

    public Integer getTipDocCod() {
        return tipDocCod;
    }

    public void setTipDocCod(Integer tipDocCod) {
        this.tipDocCod = tipDocCod;
    }

    public String getTipDocDes() {
        return tipDocDes;
    }

    public void setTipDocDes(String tipDocDes) {
        this.tipDocDes = tipDocDes;
    }

    @XmlTransient
    public Collection<Gastos> getGastosCollection() {
        return gastosCollection;
    }

    public void setGastosCollection(Collection<Gastos> gastosCollection) {
        this.gastosCollection = gastosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipDocCod != null ? tipDocCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        if ((this.tipDocCod == null && other.tipDocCod != null) || (this.tipDocCod != null && !this.tipDocCod.equals(other.tipDocCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.TipoDocumento[ tipDocCod=" + tipDocCod + " ]";
    }
    
}
