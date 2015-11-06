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
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByClieDni", query = "SELECT c FROM Cliente c WHERE c.clieDni = :clieDni"),
    @NamedQuery(name = "Cliente.findByClieNom", query = "SELECT c FROM Cliente c WHERE c.clieNom = :clieNom"),
    @NamedQuery(name = "Cliente.findByClieApe", query = "SELECT c FROM Cliente c WHERE c.clieApe = :clieApe"),
    @NamedQuery(name = "Cliente.findByClieTel", query = "SELECT c FROM Cliente c WHERE c.clieTel = :clieTel"),
    @NamedQuery(name = "Cliente.findByClieMail", query = "SELECT c FROM Cliente c WHERE c.clieMail = :clieMail"),
    @NamedQuery(name = "Cliente.findByClieEst", query = "SELECT c FROM Cliente c WHERE c.clieEst = :clieEst")})
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ClieDni")
    private Integer clieDni;
    @Column(name = "ClieNom")
    private String clieNom;
    @Column(name = "ClieApe")
    private String clieApe;
    @Column(name = "ClieTel")
    private Integer clieTel;
    @Column(name = "ClieMail")
    private String clieMail;
    @Basic(optional = false)
    @Column(name = "ClieEst")
    private boolean clieEst;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clieDni")
    private Collection<Facturacion> facturacionCollection;
    @JoinColumn(name = "TipClieCod", referencedColumnName = "TipClieCod")
    @ManyToOne(optional = false)
    private TipoCliente tipClieCod;

    public Cliente() {
    }

    public Cliente(Integer clieDni) {
        this.clieDni = clieDni;
    }

    public Cliente(Integer clieDni, boolean clieEst) {
        this.clieDni = clieDni;
        this.clieEst = clieEst;
    }

    public Integer getClieDni() {
        return clieDni;
    }

    public void setClieDni(Integer clieDni) {
        this.clieDni = clieDni;
    }

    public String getClieNom() {
        return clieNom;
    }

    public void setClieNom(String clieNom) {
        this.clieNom = clieNom;
    }

    public String getClieApe() {
        return clieApe;
    }

    public void setClieApe(String clieApe) {
        this.clieApe = clieApe;
    }

    public Integer getClieTel() {
        return clieTel;
    }

    public void setClieTel(Integer clieTel) {
        this.clieTel = clieTel;
    }

    public String getClieMail() {
        return clieMail;
    }

    public void setClieMail(String clieMail) {
        this.clieMail = clieMail;
    }

    public boolean getClieEst() {
        return clieEst;
    }

    public void setClieEst(boolean clieEst) {
        this.clieEst = clieEst;
    }

    @XmlTransient
    public Collection<Facturacion> getFacturacionCollection() {
        return facturacionCollection;
    }

    public void setFacturacionCollection(Collection<Facturacion> facturacionCollection) {
        this.facturacionCollection = facturacionCollection;
    }

    public TipoCliente getTipClieCod() {
        return tipClieCod;
    }

    public void setTipClieCod(TipoCliente tipClieCod) {
        this.tipClieCod = tipClieCod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clieDni != null ? clieDni.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.clieDni == null && other.clieDni != null) || (this.clieDni != null && !this.clieDni.equals(other.clieDni))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.Cliente[ clieDni=" + clieDni + " ]";
    }
    
}
