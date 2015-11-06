/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hisae Shizumaru
 */
@Entity
@Table(name = "ingreso_kardex")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IngresoKardex.findAll", query = "SELECT i FROM IngresoKardex i"),
    @NamedQuery(name = "IngresoKardex.findByKarCod", query = "SELECT i FROM IngresoKardex i WHERE i.karCod = :karCod"),
    @NamedQuery(name = "IngresoKardex.findByPrecProdAlm", query = "SELECT i FROM IngresoKardex i WHERE i.precProdAlm = :precProdAlm"),
    @NamedQuery(name = "IngresoKardex.findByCantProdAlm", query = "SELECT i FROM IngresoKardex i WHERE i.cantProdAlm = :cantProdAlm")})
public class IngresoKardex implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "KarCod")
    private Integer karCod;
    @Basic(optional = false)
    @Column(name = "PrecProdAlm")
    private float precProdAlm;
    @Basic(optional = false)
    @Column(name = "CantProdAlm")
    private int cantProdAlm;
    @JoinColumn(name = "GasCod", referencedColumnName = "GasCod")
    @ManyToOne
    private Gastos gasCod;
    @JoinColumn(name = "KarCod", referencedColumnName = "KarCod", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Kardex kardex;

    public IngresoKardex() {
    }

    public IngresoKardex(Integer karCod) {
        this.karCod = karCod;
    }

    public IngresoKardex(Integer karCod, float precProdAlm, int cantProdAlm) {
        this.karCod = karCod;
        this.precProdAlm = precProdAlm;
        this.cantProdAlm = cantProdAlm;
    }

    public Integer getKarCod() {
        return karCod;
    }

    public void setKarCod(Integer karCod) {
        this.karCod = karCod;
    }

    public float getPrecProdAlm() {
        return precProdAlm;
    }

    public void setPrecProdAlm(float precProdAlm) {
        this.precProdAlm = precProdAlm;
    }

    public int getCantProdAlm() {
        return cantProdAlm;
    }

    public void setCantProdAlm(int cantProdAlm) {
        this.cantProdAlm = cantProdAlm;
    }

    public Gastos getGasCod() {
        return gasCod;
    }

    public void setGasCod(Gastos gasCod) {
        this.gasCod = gasCod;
    }

    public Kardex getKardex() {
        return kardex;
    }

    public void setKardex(Kardex kardex) {
        this.kardex = kardex;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (karCod != null ? karCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IngresoKardex)) {
            return false;
        }
        IngresoKardex other = (IngresoKardex) object;
        if ((this.karCod == null && other.karCod != null) || (this.karCod != null && !this.karCod.equals(other.karCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.IngresoKardex[ karCod=" + karCod + " ]";
    }
    
}
