/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.persistencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hisae Shizumaru
 */
@Entity
@Table(name = "turno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Turno.findAll", query = "SELECT t FROM Turno t"),
    @NamedQuery(name = "Turno.findByTurCod", query = "SELECT t FROM Turno t WHERE t.turCod = :turCod"),
    @NamedQuery(name = "Turno.findByTurDes", query = "SELECT t FROM Turno t WHERE t.turDes = :turDes"),
    @NamedQuery(name = "Turno.findByTurHorIni", query = "SELECT t FROM Turno t WHERE t.turHorIni = :turHorIni"),
    @NamedQuery(name = "Turno.findByTurHorFin", query = "SELECT t FROM Turno t WHERE t.turHorFin = :turHorFin"),
    @NamedQuery(name = "Turno.findByTurEst", query = "SELECT t FROM Turno t WHERE t.turEst = :turEst")})
public class Turno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TurCod")
    private Integer turCod;
    @Basic(optional = false)
    @Column(name = "TurDes")
    private String turDes;
    @Basic(optional = false)
    @Column(name = "TurHorIni")
    @Temporal(TemporalType.TIME)
    private Date turHorIni;
    @Basic(optional = false)
    @Column(name = "TurHorFin")
    @Temporal(TemporalType.TIME)
    private Date turHorFin;
    @Basic(optional = false)
    @Column(name = "TurEst")
    private boolean turEst;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "turCod")
    private Collection<Empleado> empleadoCollection;

    public Turno() {
    }

    public Turno(Integer turCod) {
        this.turCod = turCod;
    }

    public Turno(Integer turCod, String turDes, Date turHorIni, Date turHorFin, boolean turEst) {
        this.turCod = turCod;
        this.turDes = turDes;
        this.turHorIni = turHorIni;
        this.turHorFin = turHorFin;
        this.turEst = turEst;
    }

    public Integer getTurCod() {
        return turCod;
    }

    public void setTurCod(Integer turCod) {
        this.turCod = turCod;
    }

    public String getTurDes() {
        return turDes;
    }

    public void setTurDes(String turDes) {
        this.turDes = turDes;
    }

    public Date getTurHorIni() {
        return turHorIni;
    }

    public void setTurHorIni(Date turHorIni) {
        this.turHorIni = turHorIni;
    }

    public Date getTurHorFin() {
        return turHorFin;
    }

    public void setTurHorFin(Date turHorFin) {
        this.turHorFin = turHorFin;
    }

    public boolean getTurEst() {
        return turEst;
    }

    public void setTurEst(boolean turEst) {
        this.turEst = turEst;
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
        hash += (turCod != null ? turCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turno)) {
            return false;
        }
        Turno other = (Turno) object;
        if ((this.turCod == null && other.turCod != null) || (this.turCod != null && !this.turCod.equals(other.turCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.Turno[ turCod=" + turCod + " ]";
    }
    
}
