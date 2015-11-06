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
@Table(name = "gastos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gastos.findAll", query = "SELECT g FROM Gastos g"),
    @NamedQuery(name = "Gastos.findByGasCod", query = "SELECT g FROM Gastos g WHERE g.gasCod = :gasCod"),
    @NamedQuery(name = "Gastos.findByGasDes", query = "SELECT g FROM Gastos g WHERE g.gasDes = :gasDes"),
    @NamedQuery(name = "Gastos.findByGasDia", query = "SELECT g FROM Gastos g WHERE g.gasDia = :gasDia"),
    @NamedQuery(name = "Gastos.findByGasMes", query = "SELECT g FROM Gastos g WHERE g.gasMes = :gasMes"),
    @NamedQuery(name = "Gastos.findByGasAnio", query = "SELECT g FROM Gastos g WHERE g.gasAnio = :gasAnio"),
    @NamedQuery(name = "Gastos.findByGasTot", query = "SELECT g FROM Gastos g WHERE g.gasTot = :gasTot"),
    @NamedQuery(name = "Gastos.findByGasNBol", query = "SELECT g FROM Gastos g WHERE g.gasNBol = :gasNBol"),
    @NamedQuery(name = "Gastos.findByGasNumDoc", query = "SELECT g FROM Gastos g WHERE g.gasNumDoc = :gasNumDoc")})
public class Gastos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "GasCod")
    private Integer gasCod;
    @Column(name = "GasDes")
    private String gasDes;
    @Basic(optional = false)
    @Column(name = "GasDia")
    private int gasDia;
    @Basic(optional = false)
    @Column(name = "GasMes")
    private int gasMes;
    @Basic(optional = false)
    @Column(name = "GasAnio")
    private int gasAnio;
    @Basic(optional = false)
    @Column(name = "GasTot")
    private float gasTot;
    @Column(name = "GasNBol")
    private Integer gasNBol;
    @Column(name = "GasNumDoc")
    private Integer gasNumDoc;
    @JoinColumn(name = "EmpDni", referencedColumnName = "EmpDni")
    @ManyToOne
    private Empleado empDni;
    @JoinColumn(name = "ProvRuc", referencedColumnName = "ProvRuc")
    @ManyToOne
    private Proveedor provRuc;
    @JoinColumn(name = "TipDocCod", referencedColumnName = "TipDocCod")
    @ManyToOne
    private TipoDocumento tipDocCod;
    @JoinColumn(name = "TipGasCod", referencedColumnName = "TipGasCod")
    @ManyToOne(optional = false)
    private TipoGasto tipGasCod;
    @OneToMany(mappedBy = "gasCod")
    private Collection<IngresoKardex> ingresoKardexCollection;

    public Gastos() {
    }

    public Gastos(Integer gasCod) {
        this.gasCod = gasCod;
    }

    public Gastos(Integer gasCod, int gasDia, int gasMes, int gasAnio, float gasTot) {
        this.gasCod = gasCod;
        this.gasDia = gasDia;
        this.gasMes = gasMes;
        this.gasAnio = gasAnio;
        this.gasTot = gasTot;
    }

    public Integer getGasCod() {
        return gasCod;
    }

    public void setGasCod(Integer gasCod) {
        this.gasCod = gasCod;
    }

    public String getGasDes() {
        return gasDes;
    }

    public void setGasDes(String gasDes) {
        this.gasDes = gasDes;
    }

    public int getGasDia() {
        return gasDia;
    }

    public void setGasDia(int gasDia) {
        this.gasDia = gasDia;
    }

    public int getGasMes() {
        return gasMes;
    }

    public void setGasMes(int gasMes) {
        this.gasMes = gasMes;
    }

    public int getGasAnio() {
        return gasAnio;
    }

    public void setGasAnio(int gasAnio) {
        this.gasAnio = gasAnio;
    }

    public float getGasTot() {
        return gasTot;
    }

    public void setGasTot(float gasTot) {
        this.gasTot = gasTot;
    }

    public Integer getGasNBol() {
        return gasNBol;
    }

    public void setGasNBol(Integer gasNBol) {
        this.gasNBol = gasNBol;
    }

    public Integer getGasNumDoc() {
        return gasNumDoc;
    }

    public void setGasNumDoc(Integer gasNumDoc) {
        this.gasNumDoc = gasNumDoc;
    }

    public Empleado getEmpDni() {
        return empDni;
    }

    public void setEmpDni(Empleado empDni) {
        this.empDni = empDni;
    }

    public Proveedor getProvRuc() {
        return provRuc;
    }

    public void setProvRuc(Proveedor provRuc) {
        this.provRuc = provRuc;
    }

    public TipoDocumento getTipDocCod() {
        return tipDocCod;
    }

    public void setTipDocCod(TipoDocumento tipDocCod) {
        this.tipDocCod = tipDocCod;
    }

    public TipoGasto getTipGasCod() {
        return tipGasCod;
    }

    public void setTipGasCod(TipoGasto tipGasCod) {
        this.tipGasCod = tipGasCod;
    }

    @XmlTransient
    public Collection<IngresoKardex> getIngresoKardexCollection() {
        return ingresoKardexCollection;
    }

    public void setIngresoKardexCollection(Collection<IngresoKardex> ingresoKardexCollection) {
        this.ingresoKardexCollection = ingresoKardexCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gasCod != null ? gasCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gastos)) {
            return false;
        }
        Gastos other = (Gastos) object;
        if ((this.gasCod == null && other.gasCod != null) || (this.gasCod != null && !this.gasCod.equals(other.gasCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.Gastos[ gasCod=" + gasCod + " ]";
    }
    
}
