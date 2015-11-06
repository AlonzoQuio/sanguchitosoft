/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "apertura_caja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AperturaCaja.findAll", query = "SELECT a FROM AperturaCaja a"),
    @NamedQuery(name = "AperturaCaja.findByAperCajCod", query = "SELECT a FROM AperturaCaja a WHERE a.aperCajCod = :aperCajCod"),
    @NamedQuery(name = "AperturaCaja.findByAperCajFecDia", query = "SELECT a FROM AperturaCaja a WHERE a.aperCajFecDia = :aperCajFecDia"),
    @NamedQuery(name = "AperturaCaja.findByAperCajFecMes", query = "SELECT a FROM AperturaCaja a WHERE a.aperCajFecMes = :aperCajFecMes"),
    @NamedQuery(name = "AperturaCaja.findByAperCajFecAni", query = "SELECT a FROM AperturaCaja a WHERE a.aperCajFecAni = :aperCajFecAni"),
    @NamedQuery(name = "AperturaCaja.findByAperCajHor", query = "SELECT a FROM AperturaCaja a WHERE a.aperCajHor = :aperCajHor"),
    @NamedQuery(name = "AperturaCaja.findByAperFacIni", query = "SELECT a FROM AperturaCaja a WHERE a.aperFacIni = :aperFacIni"),
    @NamedQuery(name = "AperturaCaja.findByAperMontIni", query = "SELECT a FROM AperturaCaja a WHERE a.aperMontIni = :aperMontIni"),
    @NamedQuery(name = "AperturaCaja.findByAperCajObs", query = "SELECT a FROM AperturaCaja a WHERE a.aperCajObs = :aperCajObs")})
public class AperturaCaja implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "AperCajCod")
    private Integer aperCajCod;
    @Basic(optional = false)
    @Column(name = "AperCajFecDia")
    private int aperCajFecDia;
    @Basic(optional = false)
    @Column(name = "AperCajFecMes")
    private int aperCajFecMes;
    @Basic(optional = false)
    @Column(name = "AperCajFecAni")
    private int aperCajFecAni;
    @Basic(optional = false)
    @Column(name = "AperCajHor")
    @Temporal(TemporalType.TIME)
    private Date aperCajHor;
    @Basic(optional = false)
    @Column(name = "AperFacIni")
    private int aperFacIni;
    @Basic(optional = false)
    @Column(name = "AperMontIni")
    private float aperMontIni;
    @Column(name = "AperCajObs")
    private String aperCajObs;
    @JoinColumn(name = "UsuCod", referencedColumnName = "UsuCod")
    @ManyToOne
    private Usuario usuCod;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "aperturaCaja")
    private CierreCaja cierreCaja;

    public AperturaCaja() {
    }

    public AperturaCaja(Integer aperCajCod) {
        this.aperCajCod = aperCajCod;
    }

    public AperturaCaja(Integer aperCajCod, int aperCajFecDia, int aperCajFecMes, int aperCajFecAni, Date aperCajHor, int aperFacIni, float aperMontIni) {
        this.aperCajCod = aperCajCod;
        this.aperCajFecDia = aperCajFecDia;
        this.aperCajFecMes = aperCajFecMes;
        this.aperCajFecAni = aperCajFecAni;
        this.aperCajHor = aperCajHor;
        this.aperFacIni = aperFacIni;
        this.aperMontIni = aperMontIni;
    }

    public Integer getAperCajCod() {
        return aperCajCod;
    }

    public void setAperCajCod(Integer aperCajCod) {
        this.aperCajCod = aperCajCod;
    }

    public int getAperCajFecDia() {
        return aperCajFecDia;
    }

    public void setAperCajFecDia(int aperCajFecDia) {
        this.aperCajFecDia = aperCajFecDia;
    }

    public int getAperCajFecMes() {
        return aperCajFecMes;
    }

    public void setAperCajFecMes(int aperCajFecMes) {
        this.aperCajFecMes = aperCajFecMes;
    }

    public int getAperCajFecAni() {
        return aperCajFecAni;
    }

    public void setAperCajFecAni(int aperCajFecAni) {
        this.aperCajFecAni = aperCajFecAni;
    }

    public Date getAperCajHor() {
        return aperCajHor;
    }

    public void setAperCajHor(Date aperCajHor) {
        this.aperCajHor = aperCajHor;
    }

    public int getAperFacIni() {
        return aperFacIni;
    }

    public void setAperFacIni(int aperFacIni) {
        this.aperFacIni = aperFacIni;
    }

    public float getAperMontIni() {
        return aperMontIni;
    }

    public void setAperMontIni(float aperMontIni) {
        this.aperMontIni = aperMontIni;
    }

    public String getAperCajObs() {
        return aperCajObs;
    }

    public void setAperCajObs(String aperCajObs) {
        this.aperCajObs = aperCajObs;
    }

    public Usuario getUsuCod() {
        return usuCod;
    }

    public void setUsuCod(Usuario usuCod) {
        this.usuCod = usuCod;
    }

    public CierreCaja getCierreCaja() {
        return cierreCaja;
    }

    public void setCierreCaja(CierreCaja cierreCaja) {
        this.cierreCaja = cierreCaja;
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
        if (!(object instanceof AperturaCaja)) {
            return false;
        }
        AperturaCaja other = (AperturaCaja) object;
        if ((this.aperCajCod == null && other.aperCajCod != null) || (this.aperCajCod != null && !this.aperCajCod.equals(other.aperCajCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.AperturaCaja[ aperCajCod=" + aperCajCod + " ]";
    }
    
}
