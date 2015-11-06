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
@Table(name = "egreso_kardex")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EgresoKardex.findAll", query = "SELECT e FROM EgresoKardex e"),
    @NamedQuery(name = "EgresoKardex.findByKarCod", query = "SELECT e FROM EgresoKardex e WHERE e.karCod = :karCod"),
    @NamedQuery(name = "EgresoKardex.findByCantProdAlm", query = "SELECT e FROM EgresoKardex e WHERE e.cantProdAlm = :cantProdAlm")})
public class EgresoKardex implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "KarCod")
    private Integer karCod;
    @Basic(optional = false)
    @Column(name = "CantProdAlm")
    private int cantProdAlm;
    @JoinColumn(name = "KarCod", referencedColumnName = "KarCod", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Kardex kardex;

    public EgresoKardex() {
    }

    public EgresoKardex(Integer karCod) {
        this.karCod = karCod;
    }

    public EgresoKardex(Integer karCod, int cantProdAlm) {
        this.karCod = karCod;
        this.cantProdAlm = cantProdAlm;
    }

    public Integer getKarCod() {
        return karCod;
    }

    public void setKarCod(Integer karCod) {
        this.karCod = karCod;
    }

    public int getCantProdAlm() {
        return cantProdAlm;
    }

    public void setCantProdAlm(int cantProdAlm) {
        this.cantProdAlm = cantProdAlm;
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
        if (!(object instanceof EgresoKardex)) {
            return false;
        }
        EgresoKardex other = (EgresoKardex) object;
        if ((this.karCod == null && other.karCod != null) || (this.karCod != null && !this.karCod.equals(other.karCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.EgresoKardex[ karCod=" + karCod + " ]";
    }
    
}
