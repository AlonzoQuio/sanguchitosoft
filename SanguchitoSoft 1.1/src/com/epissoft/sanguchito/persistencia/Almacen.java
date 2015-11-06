/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hisae Shizumaru
 */
@Entity
@Table(name = "almacen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Almacen.findAll", query = "SELECT a FROM Almacen a"),
    @NamedQuery(name = "Almacen.findByIngCod", query = "SELECT a FROM Almacen a WHERE a.ingCod = :ingCod"),
    @NamedQuery(name = "Almacen.findByAlmCant", query = "SELECT a FROM Almacen a WHERE a.almCant = :almCant")})
public class Almacen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IngCod")
    private Integer ingCod;
    @Basic(optional = false)
    @Column(name = "AlmCant")
    private int almCant;
    @JoinColumn(name = "IngCod", referencedColumnName = "ProdAlmCod", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private ProductoAlmacen productoAlmacen;

    public Almacen() {
    }

    public Almacen(Integer ingCod) {
        this.ingCod = ingCod;
    }

    public Almacen(Integer ingCod, int almCant) {
        this.ingCod = ingCod;
        this.almCant = almCant;
    }

    public Integer getIngCod() {
        return ingCod;
    }

    public void setIngCod(Integer ingCod) {
        this.ingCod = ingCod;
    }

    public int getAlmCant() {
        return almCant;
    }

    public void setAlmCant(int almCant) {
        this.almCant = almCant;
    }

    public ProductoAlmacen getProductoAlmacen() {
        return productoAlmacen;
    }

    public void setProductoAlmacen(ProductoAlmacen productoAlmacen) {
        this.productoAlmacen = productoAlmacen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ingCod != null ? ingCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Almacen)) {
            return false;
        }
        Almacen other = (Almacen) object;
        if ((this.ingCod == null && other.ingCod != null) || (this.ingCod != null && !this.ingCod.equals(other.ingCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.Almacen[ ingCod=" + ingCod + " ]";
    }
    
}
