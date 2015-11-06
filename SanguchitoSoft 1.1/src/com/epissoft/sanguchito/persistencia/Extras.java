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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "extras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Extras.findByCatExtCod", query = "SELECT e FROM Extras e WHERE e.catExtCod = :catExtCod AND e.extEst = :extEst"),
    @NamedQuery(name = "Extras.findAll", query = "SELECT e FROM Extras e"),
    @NamedQuery(name = "Extras.findByExtCod", query = "SELECT e FROM Extras e WHERE e.extCod = :extCod"),
    @NamedQuery(name = "Extras.findByExtDes", query = "SELECT e FROM Extras e WHERE e.extDes = :extDes"),
    @NamedQuery(name = "Extras.findByExtPrecSol", query = "SELECT e FROM Extras e WHERE e.extPrecSol = :extPrecSol"),
    @NamedQuery(name = "Extras.findByExtPrecSolCar", query = "SELECT e FROM Extras e WHERE e.extPrecSolCar = :extPrecSolCar"),
    @NamedQuery(name = "Extras.findByExtEst", query = "SELECT e FROM Extras e WHERE e.extEst = :extEst"),
    @NamedQuery(name = "Extras.findByProAlmCod", query = "SELECT e FROM Extras e WHERE e.proAlmCod = :proAlmCod")})
public class Extras implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ExtCod")
    private Integer extCod;
    @Basic(optional = false)
    @Column(name = "ExtDes")
    private String extDes;
    @Basic(optional = false)
    @Column(name = "ExtPrecSol")
    private float extPrecSol;
    @Basic(optional = false)
    @Column(name = "ExtPrecSolCar")
    private float extPrecSolCar;
    @Basic(optional = false)
    @Column(name = "ExtEst")
    private boolean extEst;
    @Basic(optional = false)
    @Column(name = "ProAlmCod")
    private int proAlmCod;
    @JoinColumn(name = "CatExtCod", referencedColumnName = "CatExtCod")
    @ManyToOne(optional = false)
    private CategoriaExtra catExtCod;
    @OneToMany(mappedBy = "extCod")
    private Collection<FacturaciondExtra> facturaciondExtraCollection;

    public Extras() {
    }

    public Extras(Integer extCod) {
        this.extCod = extCod;
    }

    public Extras(Integer extCod, String extDes, float extPrecSol, float extPrecSolCar, boolean extEst, int proAlmCod) {
        this.extCod = extCod;
        this.extDes = extDes;
        this.extPrecSol = extPrecSol;
        this.extPrecSolCar = extPrecSolCar;
        this.extEst = extEst;
        this.proAlmCod = proAlmCod;
    }

    public Integer getExtCod() {
        return extCod;
    }

    public void setExtCod(Integer extCod) {
        this.extCod = extCod;
    }

    public String getExtDes() {
        return extDes;
    }

    public void setExtDes(String extDes) {
        this.extDes = extDes;
    }

    public float getExtPrecSol() {
        return extPrecSol;
    }

    public void setExtPrecSol(float extPrecSol) {
        this.extPrecSol = extPrecSol;
    }

    public float getExtPrecSolCar() {
        return extPrecSolCar;
    }

    public void setExtPrecSolCar(float extPrecSolCar) {
        this.extPrecSolCar = extPrecSolCar;
    }

    public boolean getExtEst() {
        return extEst;
    }

    public void setExtEst(boolean extEst) {
        this.extEst = extEst;
    }

    public int getProAlmCod() {
        return proAlmCod;
    }

    public void setProAlmCod(int proAlmCod) {
        this.proAlmCod = proAlmCod;
    }

    public CategoriaExtra getCatExtCod() {
        return catExtCod;
    }

    public void setCatExtCod(CategoriaExtra catExtCod) {
        this.catExtCod = catExtCod;
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
        hash += (extCod != null ? extCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Extras)) {
            return false;
        }
        Extras other = (Extras) object;
        if ((this.extCod == null && other.extCod != null) || (this.extCod != null && !this.extCod.equals(other.extCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.Extras[ extCod=" + extCod + " ]";
    }
    
}
