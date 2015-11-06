/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hisae Shizumaru
 */
@Entity
@Table(name = "facturaciond_extra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FacturaciondExtra.findAll", query = "SELECT f FROM FacturaciondExtra f"),
    @NamedQuery(name = "FacturaciondExtra.findByFacExtDetCod", query = "SELECT f FROM FacturaciondExtra f WHERE f.facExtDetCod = :facExtDetCod"),
    @NamedQuery(name = "FacturaciondExtra.findByFacExtCant", query = "SELECT f FROM FacturaciondExtra f WHERE f.facExtCant = :facExtCant"),
    @NamedQuery(name = "FacturaciondExtra.findByFacExtPrec", query = "SELECT f FROM FacturaciondExtra f WHERE f.facExtPrec = :facExtPrec")})
public class FacturaciondExtra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FacExtDetCod")
    private Integer facExtDetCod;
    @Basic(optional = false)
    @Column(name = "FacExtCant")
    private int facExtCant;
    @Basic(optional = false)
    @Column(name = "FacExtPrec")
    private float facExtPrec;
    @JoinColumn(name = "ExtCod", referencedColumnName = "ExtCod")
    @ManyToOne
    private Extras extCod;
    @JoinColumn(name = "FacDetSec", referencedColumnName = "FacDetSec")
    @ManyToOne
    private FacturacionDetalle facDetSec;

    public FacturaciondExtra() {
    }

    public FacturaciondExtra(Integer facExtDetCod) {
        this.facExtDetCod = facExtDetCod;
    }

    public FacturaciondExtra(Integer facExtDetCod, int facExtCant, float facExtPrec) {
        this.facExtDetCod = facExtDetCod;
        this.facExtCant = facExtCant;
        this.facExtPrec = facExtPrec;
    }

    public Integer getFacExtDetCod() {
        return facExtDetCod;
    }

    public void setFacExtDetCod(Integer facExtDetCod) {
        this.facExtDetCod = facExtDetCod;
    }

    public int getFacExtCant() {
        return facExtCant;
    }

    public void setFacExtCant(int facExtCant) {
        this.facExtCant = facExtCant;
    }

    public float getFacExtPrec() {
        return facExtPrec;
    }

    public void setFacExtPrec(float facExtPrec) {
        this.facExtPrec = facExtPrec;
    }

    public Extras getExtCod() {
        return extCod;
    }

    public void setExtCod(Extras extCod) {
        this.extCod = extCod;
    }

    public FacturacionDetalle getFacDetSec() {
        return facDetSec;
    }

    public void setFacDetSec(FacturacionDetalle facDetSec) {
        this.facDetSec = facDetSec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facExtDetCod != null ? facExtDetCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturaciondExtra)) {
            return false;
        }
        FacturaciondExtra other = (FacturaciondExtra) object;
        if ((this.facExtDetCod == null && other.facExtDetCod != null) || (this.facExtDetCod != null && !this.facExtDetCod.equals(other.facExtDetCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.FacturaciondExtra[ facExtDetCod=" + facExtDetCod + " ]";
    }
    
}
