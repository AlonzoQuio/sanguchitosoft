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
@Table(name = "empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findByTurCod", query = "SELECT e FROM Empleado e WHERE e.turCod = :turCod"),
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findByEmpDni", query = "SELECT e FROM Empleado e WHERE e.empDni = :empDni"),
    @NamedQuery(name = "Empleado.findByEmpNom", query = "SELECT e FROM Empleado e WHERE e.empNom = :empNom"),
    @NamedQuery(name = "Empleado.findByEmpApePat", query = "SELECT e FROM Empleado e WHERE e.empApePat = :empApePat"),
    @NamedQuery(name = "Empleado.findByEmpApeMat", query = "SELECT e FROM Empleado e WHERE e.empApeMat = :empApeMat"),
    @NamedQuery(name = "Empleado.findByEmpTel1", query = "SELECT e FROM Empleado e WHERE e.empTel1 = :empTel1"),
    @NamedQuery(name = "Empleado.findByEmpTel2", query = "SELECT e FROM Empleado e WHERE e.empTel2 = :empTel2"),
    @NamedQuery(name = "Empleado.findByEmpDir", query = "SELECT e FROM Empleado e WHERE e.empDir = :empDir"),
    @NamedQuery(name = "Empleado.findByEmpDirDis", query = "SELECT e FROM Empleado e WHERE e.empDirDis = :empDirDis"),
    @NamedQuery(name = "Empleado.findByEmpSuel", query = "SELECT e FROM Empleado e WHERE e.empSuel = :empSuel"),
    @NamedQuery(name = "Empleado.findByEmpEst", query = "SELECT e FROM Empleado e WHERE e.empEst = :empEst")})
public class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EmpDni")
    private Integer empDni;
    @Basic(optional = false)
    @Column(name = "EmpNom")
    private String empNom;
    @Basic(optional = false)
    @Column(name = "EmpApePat")
    private String empApePat;
    @Basic(optional = false)
    @Column(name = "EmpApeMat")
    private String empApeMat;
    @Column(name = "EmpTel1")
    private Integer empTel1;
    @Column(name = "EmpTel2")
    private Integer empTel2;
    @Basic(optional = false)
    @Column(name = "EmpDir")
    private String empDir;
    @Basic(optional = false)
    @Column(name = "EmpDirDis")
    private String empDirDis;
    @Basic(optional = false)
    @Column(name = "EmpSuel")
    private float empSuel;
    @Basic(optional = false)
    @Column(name = "EmpEst")
    private boolean empEst;
    @OneToMany(mappedBy = "empDni")
    private Collection<Gastos> gastosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empDni")
    private Collection<Usuario> usuarioCollection;
    @JoinColumn(name = "TurCod", referencedColumnName = "TurCod")
    @ManyToOne(optional = false)
    private Turno turCod;
    @JoinColumn(name = "CarCod", referencedColumnName = "CarCod")
    @ManyToOne(optional = false)
    private Cargo carCod;
    @OneToMany(mappedBy = "empDni")
    private Collection<RegistroFalta> registroFaltaCollection;

    public Empleado() {
    }

    public Empleado(Integer empDni) {
        this.empDni = empDni;
    }

    public Empleado(Integer empDni, String empNom, String empApePat, String empApeMat, String empDir, String empDirDis, float empSuel, boolean empEst) {
        this.empDni = empDni;
        this.empNom = empNom;
        this.empApePat = empApePat;
        this.empApeMat = empApeMat;
        this.empDir = empDir;
        this.empDirDis = empDirDis;
        this.empSuel = empSuel;
        this.empEst = empEst;
    }

    public Integer getEmpDni() {
        return empDni;
    }

    public void setEmpDni(Integer empDni) {
        this.empDni = empDni;
    }

    public String getEmpNom() {
        return empNom;
    }

    public void setEmpNom(String empNom) {
        this.empNom = empNom;
    }

    public String getEmpApePat() {
        return empApePat;
    }

    public void setEmpApePat(String empApePat) {
        this.empApePat = empApePat;
    }

    public String getEmpApeMat() {
        return empApeMat;
    }

    public void setEmpApeMat(String empApeMat) {
        this.empApeMat = empApeMat;
    }

    public Integer getEmpTel1() {
        return empTel1;
    }

    public void setEmpTel1(Integer empTel1) {
        this.empTel1 = empTel1;
    }

    public Integer getEmpTel2() {
        return empTel2;
    }

    public void setEmpTel2(Integer empTel2) {
        this.empTel2 = empTel2;
    }

    public String getEmpDir() {
        return empDir;
    }

    public void setEmpDir(String empDir) {
        this.empDir = empDir;
    }

    public String getEmpDirDis() {
        return empDirDis;
    }

    public void setEmpDirDis(String empDirDis) {
        this.empDirDis = empDirDis;
    }

    public float getEmpSuel() {
        return empSuel;
    }

    public void setEmpSuel(float empSuel) {
        this.empSuel = empSuel;
    }

    public boolean getEmpEst() {
        return empEst;
    }

    public void setEmpEst(boolean empEst) {
        this.empEst = empEst;
    }

    @XmlTransient
    public Collection<Gastos> getGastosCollection() {
        return gastosCollection;
    }

    public void setGastosCollection(Collection<Gastos> gastosCollection) {
        this.gastosCollection = gastosCollection;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    public Turno getTurCod() {
        return turCod;
    }

    public void setTurCod(Turno turCod) {
        this.turCod = turCod;
    }

    public Cargo getCarCod() {
        return carCod;
    }

    public void setCarCod(Cargo carCod) {
        this.carCod = carCod;
    }

    @XmlTransient
    public Collection<RegistroFalta> getRegistroFaltaCollection() {
        return registroFaltaCollection;
    }

    public void setRegistroFaltaCollection(Collection<RegistroFalta> registroFaltaCollection) {
        this.registroFaltaCollection = registroFaltaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empDni != null ? empDni.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.empDni == null && other.empDni != null) || (this.empDni != null && !this.empDni.equals(other.empDni))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.Empleado[ empDni=" + empDni + " ]";
    }
    
}
