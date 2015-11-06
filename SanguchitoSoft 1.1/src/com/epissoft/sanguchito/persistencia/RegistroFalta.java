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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hisae Shizumaru
 */
@Entity
@Table(name = "registro_falta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroFalta.findAll", query = "SELECT r FROM RegistroFalta r"),
    @NamedQuery(name = "RegistroFalta.findByRegFaltaCod", query = "SELECT r FROM RegistroFalta r WHERE r.regFaltaCod = :regFaltaCod"),
    @NamedQuery(name = "RegistroFalta.findByRegFalFecDia", query = "SELECT r FROM RegistroFalta r WHERE r.regFalFecDia = :regFalFecDia"),
    @NamedQuery(name = "RegistroFalta.findByRegFalFecMes", query = "SELECT r FROM RegistroFalta r WHERE r.regFalFecMes = :regFalFecMes"),
    @NamedQuery(name = "RegistroFalta.findByRegFalFecAnio", query = "SELECT r FROM RegistroFalta r WHERE r.regFalFecAnio = :regFalFecAnio"),
    @NamedQuery(name = "RegistroFalta.findByRegFaltHor", query = "SELECT r FROM RegistroFalta r WHERE r.regFaltHor = :regFaltHor")})
public class RegistroFalta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "RegFaltaCod")
    private Integer regFaltaCod;
    @Basic(optional = false)
    @Column(name = "RegFalFecDia")
    private int regFalFecDia;
    @Basic(optional = false)
    @Column(name = "RegFalFecMes")
    private int regFalFecMes;
    @Basic(optional = false)
    @Column(name = "RegFalFecAnio")
    private int regFalFecAnio;
    @Basic(optional = false)
    @Column(name = "RegFaltHor")
    @Temporal(TemporalType.TIME)
    private Date regFaltHor;
    @JoinColumn(name = "EmpDni", referencedColumnName = "EmpDni")
    @ManyToOne
    private Empleado empDni;
    @JoinColumn(name = "TipFalCod", referencedColumnName = "TipFalCod")
    @ManyToOne
    private TipoFalta tipFalCod;

    public RegistroFalta() {
    }

    public RegistroFalta(Integer regFaltaCod) {
        this.regFaltaCod = regFaltaCod;
    }

    public RegistroFalta(Integer regFaltaCod, int regFalFecDia, int regFalFecMes, int regFalFecAnio, Date regFaltHor) {
        this.regFaltaCod = regFaltaCod;
        this.regFalFecDia = regFalFecDia;
        this.regFalFecMes = regFalFecMes;
        this.regFalFecAnio = regFalFecAnio;
        this.regFaltHor = regFaltHor;
    }

    public Integer getRegFaltaCod() {
        return regFaltaCod;
    }

    public void setRegFaltaCod(Integer regFaltaCod) {
        this.regFaltaCod = regFaltaCod;
    }

    public int getRegFalFecDia() {
        return regFalFecDia;
    }

    public void setRegFalFecDia(int regFalFecDia) {
        this.regFalFecDia = regFalFecDia;
    }

    public int getRegFalFecMes() {
        return regFalFecMes;
    }

    public void setRegFalFecMes(int regFalFecMes) {
        this.regFalFecMes = regFalFecMes;
    }

    public int getRegFalFecAnio() {
        return regFalFecAnio;
    }

    public void setRegFalFecAnio(int regFalFecAnio) {
        this.regFalFecAnio = regFalFecAnio;
    }

    public Date getRegFaltHor() {
        return regFaltHor;
    }

    public void setRegFaltHor(Date regFaltHor) {
        this.regFaltHor = regFaltHor;
    }

    public Empleado getEmpDni() {
        return empDni;
    }

    public void setEmpDni(Empleado empDni) {
        this.empDni = empDni;
    }

    public TipoFalta getTipFalCod() {
        return tipFalCod;
    }

    public void setTipFalCod(TipoFalta tipFalCod) {
        this.tipFalCod = tipFalCod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (regFaltaCod != null ? regFaltaCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroFalta)) {
            return false;
        }
        RegistroFalta other = (RegistroFalta) object;
        if ((this.regFaltaCod == null && other.regFaltaCod != null) || (this.regFaltaCod != null && !this.regFaltaCod.equals(other.regFaltaCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.RegistroFalta[ regFaltaCod=" + regFaltaCod + " ]";
    }
    
}
