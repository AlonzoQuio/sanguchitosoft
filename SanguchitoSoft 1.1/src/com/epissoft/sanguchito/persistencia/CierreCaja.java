/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hisae Shizumaru
 */
@Entity
@Table(name = "cierre_caja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CierreCaja.findAll", query = "SELECT c FROM CierreCaja c"),
    @NamedQuery(name = "CierreCaja.findByAperCajCod", query = "SELECT c FROM CierreCaja c WHERE c.aperCajCod = :aperCajCod"),
    @NamedQuery(name = "CierreCaja.findByCierCajFecDia", query = "SELECT c FROM CierreCaja c WHERE c.cierCajFecDia = :cierCajFecDia"),
    @NamedQuery(name = "CierreCaja.findByCierCajFecMes", query = "SELECT c FROM CierreCaja c WHERE c.cierCajFecMes = :cierCajFecMes"),
    @NamedQuery(name = "CierreCaja.findByCierCajFecAni", query = "SELECT c FROM CierreCaja c WHERE c.cierCajFecAni = :cierCajFecAni"),
    @NamedQuery(name = "CierreCaja.findByCierCajHor", query = "SELECT c FROM CierreCaja c WHERE c.cierCajHor = :cierCajHor"),
    @NamedQuery(name = "CierreCaja.findByCierFacFin", query = "SELECT c FROM CierreCaja c WHERE c.cierFacFin = :cierFacFin"),
    @NamedQuery(name = "CierreCaja.findByCierMont", query = "SELECT c FROM CierreCaja c WHERE c.cierMont = :cierMont"),
    @NamedQuery(name = "CierreCaja.findByCierCajObs", query = "SELECT c FROM CierreCaja c WHERE c.cierCajObs = :cierCajObs")})
public class CierreCaja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AperCajCod")
    private Integer aperCajCod;
    @Basic(optional = false)
    @Column(name = "CierCajFecDia")
    private int cierCajFecDia;
    @Basic(optional = false)
    @Column(name = "CierCajFecMes")
    private int cierCajFecMes;
    @Basic(optional = false)
    @Column(name = "CierCajFecAni")
    private int cierCajFecAni;
    @Basic(optional = false)
    @Column(name = "CierCajHor")
    @Temporal(TemporalType.TIME)
    private Date cierCajHor;
    @Basic(optional = false)
    @Column(name = "CierFacFin")
    private int cierFacFin;
    @Basic(optional = false)
    @Column(name = "CierMont")
    private float cierMont;
    @Column(name = "CierCajObs")
    private String cierCajObs;
    @JoinColumn(name = "AperCajCod", referencedColumnName = "AperCajCod", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private AperturaCaja aperturaCaja;

    public CierreCaja() {
    }

    public CierreCaja(Integer aperCajCod) {
        this.aperCajCod = aperCajCod;
    }

    public CierreCaja(Integer aperCajCod, int cierCajFecDia, int cierCajFecMes, int cierCajFecAni, Date cierCajHor, int cierFacFin, float cierMont) {
        this.aperCajCod = aperCajCod;
        this.cierCajFecDia = cierCajFecDia;
        this.cierCajFecMes = cierCajFecMes;
        this.cierCajFecAni = cierCajFecAni;
        this.cierCajHor = cierCajHor;
        this.cierFacFin = cierFacFin;
        this.cierMont = cierMont;
    }

    public Integer getAperCajCod() {
        return aperCajCod;
    }

    public void setAperCajCod(Integer aperCajCod) {
        this.aperCajCod = aperCajCod;
    }

    public int getCierCajFecDia() {
        return cierCajFecDia;
    }

    public void setCierCajFecDia(int cierCajFecDia) {
        this.cierCajFecDia = cierCajFecDia;
    }

    public int getCierCajFecMes() {
        return cierCajFecMes;
    }

    public void setCierCajFecMes(int cierCajFecMes) {
        this.cierCajFecMes = cierCajFecMes;
    }

    public int getCierCajFecAni() {
        return cierCajFecAni;
    }

    public void setCierCajFecAni(int cierCajFecAni) {
        this.cierCajFecAni = cierCajFecAni;
    }

    public Date getCierCajHor() {
        return cierCajHor;
    }

    public void setCierCajHor(Date cierCajHor) {
        this.cierCajHor = cierCajHor;
    }

    public int getCierFacFin() {
        return cierFacFin;
    }

    public void setCierFacFin(int cierFacFin) {
        this.cierFacFin = cierFacFin;
    }

    public float getCierMont() {
        return cierMont;
    }

    public void setCierMont(float cierMont) {
        this.cierMont = cierMont;
    }

    public String getCierCajObs() {
        return cierCajObs;
    }

    public void setCierCajObs(String cierCajObs) {
        this.cierCajObs = cierCajObs;
    }

    public AperturaCaja getAperturaCaja() {
        return aperturaCaja;
    }

    public void setAperturaCaja(AperturaCaja aperturaCaja) {
        this.aperturaCaja = aperturaCaja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aperCajCod != null ? aperCajCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CierreCaja)) {
            return false;
        }
        CierreCaja other = (CierreCaja) object;
        if ((this.aperCajCod == null && other.aperCajCod != null) || (this.aperCajCod != null && !this.aperCajCod.equals(other.aperCajCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.CierreCaja[ aperCajCod=" + aperCajCod + " ]";
    }
    
}
