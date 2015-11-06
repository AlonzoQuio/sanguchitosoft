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
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByProvRuc", query = "SELECT p FROM Proveedor p WHERE p.provRuc = :provRuc"),
    @NamedQuery(name = "Proveedor.findByProvNom", query = "SELECT p FROM Proveedor p WHERE p.provNom = :provNom"),
    @NamedQuery(name = "Proveedor.findByProvApeMat", query = "SELECT p FROM Proveedor p WHERE p.provApeMat = :provApeMat"),
    @NamedQuery(name = "Proveedor.findByProvApePat", query = "SELECT p FROM Proveedor p WHERE p.provApePat = :provApePat"),
    @NamedQuery(name = "Proveedor.findByProvTel", query = "SELECT p FROM Proveedor p WHERE p.provTel = :provTel"),
    @NamedQuery(name = "Proveedor.findByProvMail", query = "SELECT p FROM Proveedor p WHERE p.provMail = :provMail"),
    @NamedQuery(name = "Proveedor.findByProvRazSoc", query = "SELECT p FROM Proveedor p WHERE p.provRazSoc = :provRazSoc"),
    @NamedQuery(name = "Proveedor.findByProvEst", query = "SELECT p FROM Proveedor p WHERE p.provEst = :provEst")})
public class Proveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ProvRuc")
    private String provRuc;
    @Basic(optional = false)
    @Column(name = "ProvNom")
    private String provNom;
    @Basic(optional = false)
    @Column(name = "ProvApeMat")
    private String provApeMat;
    @Basic(optional = false)
    @Column(name = "ProvApePat")
    private String provApePat;
    @Column(name = "ProvTel")
    private Integer provTel;
    @Column(name = "ProvMail")
    private String provMail;
    @Basic(optional = false)
    @Column(name = "ProvRazSoc")
    private String provRazSoc;
    @Basic(optional = false)
    @Column(name = "ProvEst")
    private boolean provEst;
    @OneToMany(mappedBy = "provRuc")
    private Collection<Gastos> gastosCollection;

    public Proveedor() {
    }

    public Proveedor(String provRuc) {
        this.provRuc = provRuc;
    }

    public Proveedor(String provRuc, String provNom, String provApeMat, String provApePat, String provRazSoc, boolean provEst) {
        this.provRuc = provRuc;
        this.provNom = provNom;
        this.provApeMat = provApeMat;
        this.provApePat = provApePat;
        this.provRazSoc = provRazSoc;
        this.provEst = provEst;
    }

    public String getProvRuc() {
        return provRuc;
    }

    public void setProvRuc(String provRuc) {
        this.provRuc = provRuc;
    }

    public String getProvNom() {
        return provNom;
    }

    public void setProvNom(String provNom) {
        this.provNom = provNom;
    }

    public String getProvApeMat() {
        return provApeMat;
    }

    public void setProvApeMat(String provApeMat) {
        this.provApeMat = provApeMat;
    }

    public String getProvApePat() {
        return provApePat;
    }

    public void setProvApePat(String provApePat) {
        this.provApePat = provApePat;
    }

    public Integer getProvTel() {
        return provTel;
    }

    public void setProvTel(Integer provTel) {
        this.provTel = provTel;
    }

    public String getProvMail() {
        return provMail;
    }

    public void setProvMail(String provMail) {
        this.provMail = provMail;
    }

    public String getProvRazSoc() {
        return provRazSoc;
    }

    public void setProvRazSoc(String provRazSoc) {
        this.provRazSoc = provRazSoc;
    }

    public boolean getProvEst() {
        return provEst;
    }

    public void setProvEst(boolean provEst) {
        this.provEst = provEst;
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
        hash += (provRuc != null ? provRuc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.provRuc == null && other.provRuc != null) || (this.provRuc != null && !this.provRuc.equals(other.provRuc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.Proveedor[ provRuc=" + provRuc + " ]";
    }
    
}
