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
@Table(name = "tipo_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCliente.findAll", query = "SELECT t FROM TipoCliente t"),
    @NamedQuery(name = "TipoCliente.findByTipClieCod", query = "SELECT t FROM TipoCliente t WHERE t.tipClieCod = :tipClieCod"),
    @NamedQuery(name = "TipoCliente.findByTipClieDes", query = "SELECT t FROM TipoCliente t WHERE t.tipClieDes = :tipClieDes"),
    @NamedQuery(name = "TipoCliente.findByTipClieEst", query = "SELECT t FROM TipoCliente t WHERE t.tipClieEst = :tipClieEst")})
public class TipoCliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TipClieCod")
    private Integer tipClieCod;
    @Basic(optional = false)
    @Column(name = "TipClieDes")
    private String tipClieDes;
    @Basic(optional = false)
    @Column(name = "TipClieEst")
    private boolean tipClieEst;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipClieCod")
    private Collection<Cliente> clienteCollection;

    public TipoCliente() {
    }

    public TipoCliente(Integer tipClieCod) {
        this.tipClieCod = tipClieCod;
    }

    public TipoCliente(Integer tipClieCod, String tipClieDes, boolean tipClieEst) {
        this.tipClieCod = tipClieCod;
        this.tipClieDes = tipClieDes;
        this.tipClieEst = tipClieEst;
    }

    public Integer getTipClieCod() {
        return tipClieCod;
    }

    public void setTipClieCod(Integer tipClieCod) {
        this.tipClieCod = tipClieCod;
    }

    public String getTipClieDes() {
        return tipClieDes;
    }

    public void setTipClieDes(String tipClieDes) {
        this.tipClieDes = tipClieDes;
    }

    public boolean getTipClieEst() {
        return tipClieEst;
    }

    public void setTipClieEst(boolean tipClieEst) {
        this.tipClieEst = tipClieEst;
    }

    @XmlTransient
    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipClieCod != null ? tipClieCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCliente)) {
            return false;
        }
        TipoCliente other = (TipoCliente) object;
        if ((this.tipClieCod == null && other.tipClieCod != null) || (this.tipClieCod != null && !this.tipClieCod.equals(other.tipClieCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.TipoCliente[ tipClieCod=" + tipClieCod + " ]";
    }
    
}
