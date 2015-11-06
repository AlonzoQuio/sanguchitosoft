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
@Table(name = "tipo_facturacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoFacturacion.findAll", query = "SELECT t FROM TipoFacturacion t"),
    @NamedQuery(name = "TipoFacturacion.findByTipFacCod", query = "SELECT t FROM TipoFacturacion t WHERE t.tipFacCod = :tipFacCod"),
    @NamedQuery(name = "TipoFacturacion.findByTipFacDes", query = "SELECT t FROM TipoFacturacion t WHERE t.tipFacDes = :tipFacDes"),
    @NamedQuery(name = "TipoFacturacion.findByTipFacEst", query = "SELECT t FROM TipoFacturacion t WHERE t.tipFacEst = :tipFacEst")})
public class TipoFacturacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TipFacCod")
    private Integer tipFacCod;
    @Basic(optional = false)
    @Column(name = "TipFacDes")
    private String tipFacDes;
    @Basic(optional = false)
    @Column(name = "TipFacEst")
    private boolean tipFacEst;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipFacCod")
    private Collection<Facturacion> facturacionCollection;

    public TipoFacturacion() {
    }

    public TipoFacturacion(Integer tipFacCod) {
        this.tipFacCod = tipFacCod;
    }

    public TipoFacturacion(Integer tipFacCod, String tipFacDes, boolean tipFacEst) {
        this.tipFacCod = tipFacCod;
        this.tipFacDes = tipFacDes;
        this.tipFacEst = tipFacEst;
    }

    public Integer getTipFacCod() {
        return tipFacCod;
    }

    public void setTipFacCod(Integer tipFacCod) {
        this.tipFacCod = tipFacCod;
    }

    public String getTipFacDes() {
        return tipFacDes;
    }

    public void setTipFacDes(String tipFacDes) {
        this.tipFacDes = tipFacDes;
    }

    public boolean getTipFacEst() {
        return tipFacEst;
    }

    public void setTipFacEst(boolean tipFacEst) {
        this.tipFacEst = tipFacEst;
    }

    @XmlTransient
    public Collection<Facturacion> getFacturacionCollection() {
        return facturacionCollection;
    }

    public void setFacturacionCollection(Collection<Facturacion> facturacionCollection) {
        this.facturacionCollection = facturacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipFacCod != null ? tipFacCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoFacturacion)) {
            return false;
        }
        TipoFacturacion other = (TipoFacturacion) object;
        if ((this.tipFacCod == null && other.tipFacCod != null) || (this.tipFacCod != null && !this.tipFacCod.equals(other.tipFacCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.TipoFacturacion[ tipFacCod=" + tipFacCod + " ]";
    }
    
}
