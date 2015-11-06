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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsuCod", query = "SELECT u FROM Usuario u WHERE u.usuCod = :usuCod"),
    @NamedQuery(name = "Usuario.findByUsuNomUsu", query = "SELECT u FROM Usuario u WHERE u.usuNomUsu = :usuNomUsu"),
    @NamedQuery(name = "Usuario.findByUsuPasUsu", query = "SELECT u FROM Usuario u WHERE u.usuPasUsu = :usuPasUsu"),
    @NamedQuery(name = "Usuario.findByUsuEst", query = "SELECT u FROM Usuario u WHERE u.usuEst = :usuEst")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UsuCod")
    private Integer usuCod;
    @Basic(optional = false)
    @Column(name = "UsuNomUsu")
    private String usuNomUsu;
    @Basic(optional = false)
    @Column(name = "UsuPasUsu")
    private String usuPasUsu;
    @Basic(optional = false)
    @Column(name = "UsuEst")
    private boolean usuEst;
    @JoinColumn(name = "TipUsuCod", referencedColumnName = "TipUsuCod")
    @ManyToOne
    private Tipousuario tipUsuCod;
    @JoinColumn(name = "EmpDni", referencedColumnName = "EmpDni")
    @ManyToOne(optional = false)
    private Empleado empDni;
    @OneToMany(mappedBy = "usuCod")
    private Collection<AperturaCaja> aperturaCajaCollection;
    @OneToMany(mappedBy = "usuCod")
    private Collection<Facturacion> facturacionCollection;

    public Usuario() {
    }

    public Usuario(Integer usuCod) {
        this.usuCod = usuCod;
    }

    public Usuario(Integer usuCod, String usuNomUsu, String usuPasUsu, boolean usuEst) {
        this.usuCod = usuCod;
        this.usuNomUsu = usuNomUsu;
        this.usuPasUsu = usuPasUsu;
        this.usuEst = usuEst;
    }

    public Integer getUsuCod() {
        return usuCod;
    }

    public void setUsuCod(Integer usuCod) {
        this.usuCod = usuCod;
    }

    public String getUsuNomUsu() {
        return usuNomUsu;
    }

    public void setUsuNomUsu(String usuNomUsu) {
        this.usuNomUsu = usuNomUsu;
    }

    public String getUsuPasUsu() {
        return usuPasUsu;
    }

    public void setUsuPasUsu(String usuPasUsu) {
        this.usuPasUsu = usuPasUsu;
    }

    public boolean getUsuEst() {
        return usuEst;
    }

    public void setUsuEst(boolean usuEst) {
        this.usuEst = usuEst;
    }

    public Tipousuario getTipUsuCod() {
        return tipUsuCod;
    }

    public void setTipUsuCod(Tipousuario tipUsuCod) {
        this.tipUsuCod = tipUsuCod;
    }

    public Empleado getEmpDni() {
        return empDni;
    }

    public void setEmpDni(Empleado empDni) {
        this.empDni = empDni;
    }

    @XmlTransient
    public Collection<AperturaCaja> getAperturaCajaCollection() {
        return aperturaCajaCollection;
    }

    public void setAperturaCajaCollection(Collection<AperturaCaja> aperturaCajaCollection) {
        this.aperturaCajaCollection = aperturaCajaCollection;
    }

    @XmlTransient
    public Collection<Facturacion> getFacturacionCollection() {
        return facturacionCollection;
    }

    public void setFacturacionCollection(Collection<Facturacion> facturacionCollection) {
        this.facturacionCollection = facturacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuCod != null ? usuCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuCod == null && other.usuCod != null) || (this.usuCod != null && !this.usuCod.equals(other.usuCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.Usuario[ usuCod=" + usuCod + " ]";
    }
    
}
