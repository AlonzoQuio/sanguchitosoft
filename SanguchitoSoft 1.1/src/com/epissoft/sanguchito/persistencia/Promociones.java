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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hisae Shizumaru
 */
@Entity
@Table(name = "promociones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Promociones.findAll", query = "SELECT p FROM Promociones p"),
    @NamedQuery(name = "Promociones.findByPromCod", query = "SELECT p FROM Promociones p WHERE p.promCod = :promCod"),
    @NamedQuery(name = "Promociones.findByPromPorDesc", query = "SELECT p FROM Promociones p WHERE p.promPorDesc = :promPorDesc"),
    @NamedQuery(name = "Promociones.findByPromEst", query = "SELECT p FROM Promociones p WHERE p.promEst = :promEst")})
public class Promociones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PromCod")
    private Integer promCod;
    @Basic(optional = false)
    @Column(name = "PromPorDesc")
    private float promPorDesc;
    @Basic(optional = false)
    @Column(name = "PromEst")
    private boolean promEst;
    @JoinColumn(name = "ProCod", referencedColumnName = "ProCod")
    @ManyToOne
    private Producto proCod;

    public Promociones() {
    }

    public Promociones(Integer promCod) {
        this.promCod = promCod;
    }

    public Promociones(Integer promCod, float promPorDesc, boolean promEst) {
        this.promCod = promCod;
        this.promPorDesc = promPorDesc;
        this.promEst = promEst;
    }

    public Integer getPromCod() {
        return promCod;
    }

    public void setPromCod(Integer promCod) {
        this.promCod = promCod;
    }

    public float getPromPorDesc() {
        return promPorDesc;
    }

    public void setPromPorDesc(float promPorDesc) {
        this.promPorDesc = promPorDesc;
    }

    public boolean getPromEst() {
        return promEst;
    }

    public void setPromEst(boolean promEst) {
        this.promEst = promEst;
    }

    public Producto getProCod() {
        return proCod;
    }

    public void setProCod(Producto proCod) {
        this.proCod = proCod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (promCod != null ? promCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Promociones)) {
            return false;
        }
        Promociones other = (Promociones) object;
        if ((this.promCod == null && other.promCod != null) || (this.promCod != null && !this.promCod.equals(other.promCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.Promociones[ promCod=" + promCod + " ]";
    }
    
}
