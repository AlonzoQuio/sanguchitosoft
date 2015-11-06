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
@Table(name = "unidad_medida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UnidadMedida.findAll", query = "SELECT u FROM UnidadMedida u"),
    @NamedQuery(name = "UnidadMedida.findByUniMedCod", query = "SELECT u FROM UnidadMedida u WHERE u.uniMedCod = :uniMedCod"),
    @NamedQuery(name = "UnidadMedida.findByUniMedDes", query = "SELECT u FROM UnidadMedida u WHERE u.uniMedDes = :uniMedDes"),
    @NamedQuery(name = "UnidadMedida.findByUniMedEst", query = "SELECT u FROM UnidadMedida u WHERE u.uniMedEst = :uniMedEst")})
public class UnidadMedida implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UniMedCod")
    private Integer uniMedCod;
    @Basic(optional = false)
    @Column(name = "UniMedDes")
    private String uniMedDes;
    @Basic(optional = false)
    @Column(name = "UniMedEst")
    private boolean uniMedEst;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uniMedCod")
    private Collection<ProductoAlmacen> productoAlmacenCollection;

    public UnidadMedida() {
    }

    public UnidadMedida(Integer uniMedCod) {
        this.uniMedCod = uniMedCod;
    }

    public UnidadMedida(Integer uniMedCod, String uniMedDes, boolean uniMedEst) {
        this.uniMedCod = uniMedCod;
        this.uniMedDes = uniMedDes;
        this.uniMedEst = uniMedEst;
    }

    public Integer getUniMedCod() {
        return uniMedCod;
    }

    public void setUniMedCod(Integer uniMedCod) {
        this.uniMedCod = uniMedCod;
    }

    public String getUniMedDes() {
        return uniMedDes;
    }

    public void setUniMedDes(String uniMedDes) {
        this.uniMedDes = uniMedDes;
    }

    public boolean getUniMedEst() {
        return uniMedEst;
    }

    public void setUniMedEst(boolean uniMedEst) {
        this.uniMedEst = uniMedEst;
    }

    @XmlTransient
    public Collection<ProductoAlmacen> getProductoAlmacenCollection() {
        return productoAlmacenCollection;
    }

    public void setProductoAlmacenCollection(Collection<ProductoAlmacen> productoAlmacenCollection) {
        this.productoAlmacenCollection = productoAlmacenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uniMedCod != null ? uniMedCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadMedida)) {
            return false;
        }
        UnidadMedida other = (UnidadMedida) object;
        if ((this.uniMedCod == null && other.uniMedCod != null) || (this.uniMedCod != null && !this.uniMedCod.equals(other.uniMedCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.UnidadMedida[ uniMedCod=" + uniMedCod + " ]";
    }
    
}
