/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.persistencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hisae Shizumaru
 */
@Entity
@Table(name = "facturacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facturacion.findAll", query = "SELECT f FROM Facturacion f"),
    @NamedQuery(name = "Facturacion.findByFacCod", query = "SELECT f FROM Facturacion f WHERE f.facCod = :facCod"),
    @NamedQuery(name = "Facturacion.findByFacFecDia", query = "SELECT f FROM Facturacion f WHERE f.facFecDia = :facFecDia"),
    @NamedQuery(name = "Facturacion.findByFacFecMes", query = "SELECT f FROM Facturacion f WHERE f.facFecMes = :facFecMes"),
    @NamedQuery(name = "Facturacion.findByFacFecAnio", query = "SELECT f FROM Facturacion f WHERE f.facFecAnio = :facFecAnio"),
    @NamedQuery(name = "Facturacion.findByFacHor", query = "SELECT f FROM Facturacion f WHERE f.facHor = :facHor"),
    @NamedQuery(name = "Facturacion.findByFacObs", query = "SELECT f FROM Facturacion f WHERE f.facObs = :facObs"),
    @NamedQuery(name = "Facturacion.findByFacTot", query = "SELECT f FROM Facturacion f WHERE f.facTot = :facTot"),
    @NamedQuery(name = "Facturacion.findByFacDesc", query = "SELECT f FROM Facturacion f WHERE f.facDesc = :facDesc")})
public class Facturacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FacCod")
    private Integer facCod;
    @Basic(optional = false)
    @Column(name = "FacFecDia")
    private int facFecDia;
    @Basic(optional = false)
    @Column(name = "FacFecMes")
    private int facFecMes;
    @Basic(optional = false)
    @Column(name = "FacFecAnio")
    private int facFecAnio;
    @Basic(optional = false)
    @Column(name = "FacHor")
    @Temporal(TemporalType.TIME)
    private Date facHor;
    @Column(name = "FacObs")
    private String facObs;
    @Basic(optional = false)
    @Column(name = "FacTot")
    private float facTot;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "FacDesc")
    private Float facDesc;
    @JoinColumn(name = "TipFacCod", referencedColumnName = "TipFacCod")
    @ManyToOne(optional = false)
    private TipoFacturacion tipFacCod;
    @JoinColumn(name = "ClieDni", referencedColumnName = "ClieDni")
    @ManyToOne(optional = false)
    private Cliente clieDni;
    @JoinColumn(name = "UsuCod", referencedColumnName = "UsuCod")
    @ManyToOne
    private Usuario usuCod;
    @OneToMany(mappedBy = "facCod")
    private Collection<FacturacionDetalle> facturacionDetalleCollection;

    public Facturacion() {
    }

    public Facturacion(Integer facCod) {
        this.facCod = facCod;
    }

    public Facturacion(Integer facCod, int facFecDia, int facFecMes, int facFecAnio, Date facHor, float facTot) {
        this.facCod = facCod;
        this.facFecDia = facFecDia;
        this.facFecMes = facFecMes;
        this.facFecAnio = facFecAnio;
        this.facHor = facHor;
        this.facTot = facTot;
    }

    public Integer getFacCod() {
        return facCod;
    }

    public void setFacCod(Integer facCod) {
        this.facCod = facCod;
    }

    public int getFacFecDia() {
        return facFecDia;
    }

    public void setFacFecDia(int facFecDia) {
        this.facFecDia = facFecDia;
    }

    public int getFacFecMes() {
        return facFecMes;
    }

    public void setFacFecMes(int facFecMes) {
        this.facFecMes = facFecMes;
    }

    public int getFacFecAnio() {
        return facFecAnio;
    }

    public void setFacFecAnio(int facFecAnio) {
        this.facFecAnio = facFecAnio;
    }

    public Date getFacHor() {
        return facHor;
    }

    public void setFacHor(Date facHor) {
        this.facHor = facHor;
    }

    public String getFacObs() {
        return facObs;
    }

    public void setFacObs(String facObs) {
        this.facObs = facObs;
    }

    public float getFacTot() {
        return facTot;
    }

    public void setFacTot(float facTot) {
        this.facTot = facTot;
    }

    public Float getFacDesc() {
        return facDesc;
    }

    public void setFacDesc(Float facDesc) {
        this.facDesc = facDesc;
    }

    public TipoFacturacion getTipFacCod() {
        return tipFacCod;
    }

    public void setTipFacCod(TipoFacturacion tipFacCod) {
        this.tipFacCod = tipFacCod;
    }

    public Cliente getClieDni() {
        return clieDni;
    }

    public void setClieDni(Cliente clieDni) {
        this.clieDni = clieDni;
    }

    public Usuario getUsuCod() {
        return usuCod;
    }

    public void setUsuCod(Usuario usuCod) {
        this.usuCod = usuCod;
    }

    @XmlTransient
    public Collection<FacturacionDetalle> getFacturacionDetalleCollection() {
        return facturacionDetalleCollection;
    }

    public void setFacturacionDetalleCollection(Collection<FacturacionDetalle> facturacionDetalleCollection) {
        this.facturacionDetalleCollection = facturacionDetalleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facCod != null ? facCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturacion)) {
            return false;
        }
        Facturacion other = (Facturacion) object;
        if ((this.facCod == null && other.facCod != null) || (this.facCod != null && !this.facCod.equals(other.facCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.Facturacion[ facCod=" + facCod + " ]";
    }
    
}
