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
@Table(name = "tipo_falta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoFalta.findAll", query = "SELECT t FROM TipoFalta t"),
    @NamedQuery(name = "TipoFalta.findByTipFalCod", query = "SELECT t FROM TipoFalta t WHERE t.tipFalCod = :tipFalCod"),
    @NamedQuery(name = "TipoFalta.findByTipFalDes", query = "SELECT t FROM TipoFalta t WHERE t.tipFalDes = :tipFalDes"),
    @NamedQuery(name = "TipoFalta.findByTipFalEst", query = "SELECT t FROM TipoFalta t WHERE t.tipFalEst = :tipFalEst")})
public class TipoFalta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TipFalCod")
    private Integer tipFalCod;
    @Basic(optional = false)
    @Column(name = "TipFalDes")
    private String tipFalDes;
    @Basic(optional = false)
    @Column(name = "TipFalEst")
    private boolean tipFalEst;
    @OneToMany(mappedBy = "tipFalCod")
    private Collection<RegistroFalta> registroFaltaCollection;

    public TipoFalta() {
    }

    public TipoFalta(Integer tipFalCod) {
        this.tipFalCod = tipFalCod;
    }

    public TipoFalta(Integer tipFalCod, String tipFalDes, boolean tipFalEst) {
        this.tipFalCod = tipFalCod;
        this.tipFalDes = tipFalDes;
        this.tipFalEst = tipFalEst;
    }

    public Integer getTipFalCod() {
        return tipFalCod;
    }

    public void setTipFalCod(Integer tipFalCod) {
        this.tipFalCod = tipFalCod;
    }

    public String getTipFalDes() {
        return tipFalDes;
    }

    public void setTipFalDes(String tipFalDes) {
        this.tipFalDes = tipFalDes;
    }

    public boolean getTipFalEst() {
        return tipFalEst;
    }

    public void setTipFalEst(boolean tipFalEst) {
        this.tipFalEst = tipFalEst;
    }

    @XmlTransient
    public Collection<RegistroFalta> getRegistroFaltaCollection() {
        return registroFaltaCollection;
    }

    public void setRegistroFaltaCollection(Collection<RegistroFalta> registroFaltaCollection) {
        this.registroFaltaCollection = registroFaltaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipFalCod != null ? tipFalCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoFalta)) {
            return false;
        }
        TipoFalta other = (TipoFalta) object;
        if ((this.tipFalCod == null && other.tipFalCod != null) || (this.tipFalCod != null && !this.tipFalCod.equals(other.tipFalCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.TipoFalta[ tipFalCod=" + tipFalCod + " ]";
    }
    
}
