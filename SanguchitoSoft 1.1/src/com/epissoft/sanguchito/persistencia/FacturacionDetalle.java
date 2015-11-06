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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "facturacion_detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FacturacionDetalle.findAll", query = "SELECT f FROM FacturacionDetalle f"),
    @NamedQuery(name = "FacturacionDetalle.findByFacDetSec", query = "SELECT f FROM FacturacionDetalle f WHERE f.facDetSec = :facDetSec"),
    @NamedQuery(name = "FacturacionDetalle.findByFacDetDesctProm", query = "SELECT f FROM FacturacionDetalle f WHERE f.facDetDesctProm = :facDetDesctProm"),
    @NamedQuery(name = "FacturacionDetalle.findByFacDetPrec", query = "SELECT f FROM FacturacionDetalle f WHERE f.facDetPrec = :facDetPrec")})
public class FacturacionDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FacDetSec")
    private Integer facDetSec;
    @Basic(optional = false)
    @Column(name = "FacDetDesctProm")
    private int facDetDesctProm;
    @Basic(optional = false)
    @Column(name = "FacDetPrec")
    private float facDetPrec;
    @JoinColumn(name = "FacCod", referencedColumnName = "FacCod")
    @ManyToOne
    private Facturacion facCod;
    @JoinColumn(name = "ProCod", referencedColumnName = "ProCod")
    @ManyToOne
    private Producto proCod;
    @OneToMany(mappedBy = "facDetSec")
    private Collection<FacturaciondExtra> facturaciondExtraCollection;

    public FacturacionDetalle() {
    }

    public FacturacionDetalle(Integer facDetSec) {
        this.facDetSec = facDetSec;
    }

    public FacturacionDetalle(Integer facDetSec, int facDetDesctProm, float facDetPrec) {
        this.facDetSec = facDetSec;
        this.facDetDesctProm = facDetDesctProm;
        this.facDetPrec = facDetPrec;
    }

    public Integer getFacDetSec() {
        return facDetSec;
    }

    public void setFacDetSec(Integer facDetSec) {
        this.facDetSec = facDetSec;
    }

    public int getFacDetDesctProm() {
        return facDetDesctProm;
    }

    public void setFacDetDesctProm(int facDetDesctProm) {
        this.facDetDesctProm = facDetDesctProm;
    }

    public float getFacDetPrec() {
        return facDetPrec;
    }

    public void setFacDetPrec(float facDetPrec) {
        this.facDetPrec = facDetPrec;
    }

    public Facturacion getFacCod() {
        return facCod;
    }

    public void setFacCod(Facturacion facCod) {
        this.facCod = facCod;
    }

    public Producto getProCod() {
        return proCod;
    }

    public void setProCod(Producto proCod) {
        this.proCod = proCod;
    }

    @XmlTransient
    public Collection<FacturaciondExtra> getFacturaciondExtraCollection() {
        return facturaciondExtraCollection;
    }

    public void setFacturaciondExtraCollection(Collection<FacturaciondExtra> facturaciondExtraCollection) {
        this.facturaciondExtraCollection = facturaciondExtraCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facDetSec != null ? facDetSec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturacionDetalle)) {
            return false;
        }
        FacturacionDetalle other = (FacturacionDetalle) object;
        if ((this.facDetSec == null && other.facDetSec != null) || (this.facDetSec != null && !this.facDetSec.equals(other.facDetSec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.FacturacionDetalle[ facDetSec=" + facDetSec + " ]";
    }
    
}
