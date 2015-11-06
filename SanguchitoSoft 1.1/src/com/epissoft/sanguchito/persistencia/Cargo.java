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
@Table(name = "cargo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c"),
    @NamedQuery(name = "Cargo.findByCarCod", query = "SELECT c FROM Cargo c WHERE c.carCod = :carCod"),
    @NamedQuery(name = "Cargo.findByCarDes", query = "SELECT c FROM Cargo c WHERE c.carDes = :carDes"),
    @NamedQuery(name = "Cargo.findByCarObs", query = "SELECT c FROM Cargo c WHERE c.carObs = :carObs"),
    @NamedQuery(name = "Cargo.findByCarEst", query = "SELECT c FROM Cargo c WHERE c.carEst = :carEst")})
public class Cargo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CarCod")
    private Integer carCod;
    @Basic(optional = false)
    @Column(name = "CarDes")
    private String carDes;
    @Basic(optional = false)
    @Column(name = "CarObs")
    private String carObs;
    @Basic(optional = false)
    @Column(name = "CarEst")
    private boolean carEst;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carCod")
    private Collection<Empleado> empleadoCollection;

    public Cargo() {
    }

    public Cargo(Integer carCod) {
        this.carCod = carCod;
    }

    public Cargo(Integer carCod, String carDes, String carObs, boolean carEst) {
        this.carCod = carCod;
        this.carDes = carDes;
        this.carObs = carObs;
        this.carEst = carEst;
    }

    public Integer getCarCod() {
        return carCod;
    }

    public void setCarCod(Integer carCod) {
        this.carCod = carCod;
    }

    public String getCarDes() {
        return carDes;
    }

    public void setCarDes(String carDes) {
        this.carDes = carDes;
    }

    public String getCarObs() {
        return carObs;
    }

    public void setCarObs(String carObs) {
        this.carObs = carObs;
    }

    public boolean getCarEst() {
        return carEst;
    }

    public void setCarEst(boolean carEst) {
        this.carEst = carEst;
    }

    @XmlTransient
    public Collection<Empleado> getEmpleadoCollection() {
        return empleadoCollection;
    }

    public void setEmpleadoCollection(Collection<Empleado> empleadoCollection) {
        this.empleadoCollection = empleadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carCod != null ? carCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cargo)) {
            return false;
        }
        Cargo other = (Cargo) object;
        if ((this.carCod == null && other.carCod != null) || (this.carCod != null && !this.carCod.equals(other.carCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.Cargo[ carCod=" + carCod + " ]";
    }
    
}
