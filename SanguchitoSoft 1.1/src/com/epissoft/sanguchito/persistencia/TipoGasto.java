/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.persistencia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "tipo_gasto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoGasto.findAll", query = "SELECT t FROM TipoGasto t"),
    @NamedQuery(name = "TipoGasto.findByTipGasCod", query = "SELECT t FROM TipoGasto t WHERE t.tipGasCod = :tipGasCod"),
    @NamedQuery(name = "TipoGasto.findByTipGasDes", query = "SELECT t FROM TipoGasto t WHERE t.tipGasDes = :tipGasDes"),
    @NamedQuery(name = "TipoGasto.findByTipGasEst", query = "SELECT t FROM TipoGasto t WHERE t.tipGasEst = :tipGasEst")})
public class TipoGasto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TipGasCod")
    private Integer tipGasCod;
    @Basic(optional = false)
    @Column(name = "TipGasDes")
    private String tipGasDes;
    @Basic(optional = false)
    @Column(name = "TipGasEst")
    private String tipGasEst;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipGasCod")
    private Collection<Gastos> gastosCollection;

    public TipoGasto() {
    }

    public TipoGasto(Integer tipGasCod) {
        this.tipGasCod = tipGasCod;
    }

    public TipoGasto(Integer tipGasCod, String tipGasDes, String tipGasEst) {
        this.tipGasCod = tipGasCod;
        this.tipGasDes = tipGasDes;
        this.tipGasEst = tipGasEst;
    }

    public Integer getTipGasCod() {
        return tipGasCod;
    }

    public void setTipGasCod(Integer tipGasCod) {
        this.tipGasCod = tipGasCod;
    }

    public String getTipGasDes() {
        return tipGasDes;
    }

    public void setTipGasDes(String tipGasDes) {
        this.tipGasDes = tipGasDes;
    }

    public String getTipGasEst() {
        return tipGasEst;
    }

    public void setTipGasEst(String tipGasEst) {
        this.tipGasEst = tipGasEst;
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
        hash += (tipGasCod != null ? tipGasCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoGasto)) {
            return false;
        }
        TipoGasto other = (TipoGasto) object;
        if ((this.tipGasCod == null && other.tipGasCod != null) || (this.tipGasCod != null && !this.tipGasCod.equals(other.tipGasCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.TipoGasto[ tipGasCod=" + tipGasCod + " ]";
    }
    
}
