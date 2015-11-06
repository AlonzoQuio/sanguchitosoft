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
@Table(name = "categoria_producto_almacen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaProductoAlmacen.findAll", query = "SELECT c FROM CategoriaProductoAlmacen c"),
    @NamedQuery(name = "CategoriaProductoAlmacen.findByCatProdAlmCod", query = "SELECT c FROM CategoriaProductoAlmacen c WHERE c.catProdAlmCod = :catProdAlmCod"),
    @NamedQuery(name = "CategoriaProductoAlmacen.findByCatProdAlmDes", query = "SELECT c FROM CategoriaProductoAlmacen c WHERE c.catProdAlmDes = :catProdAlmDes"),
    @NamedQuery(name = "CategoriaProductoAlmacen.findByCatProdAlmImg", query = "SELECT c FROM CategoriaProductoAlmacen c WHERE c.catProdAlmImg = :catProdAlmImg"),
    @NamedQuery(name = "CategoriaProductoAlmacen.findByCatProdAlmEst", query = "SELECT c FROM CategoriaProductoAlmacen c WHERE c.catProdAlmEst = :catProdAlmEst")})
public class CategoriaProductoAlmacen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CatProdAlmCod")
    private Integer catProdAlmCod;
    @Basic(optional = false)
    @Column(name = "CatProdAlmDes")
    private String catProdAlmDes;
    @Basic(optional = false)
    @Column(name = "CatProdAlmImg")
    private String catProdAlmImg;
    @Basic(optional = false)
    @Column(name = "CatProdAlmEst")
    private boolean catProdAlmEst;
    @OneToMany(mappedBy = "catProdAlmCod")
    private Collection<ProductoAlmacen> productoAlmacenCollection;

    public CategoriaProductoAlmacen() {
    }

    public CategoriaProductoAlmacen(Integer catProdAlmCod) {
        this.catProdAlmCod = catProdAlmCod;
    }

    public CategoriaProductoAlmacen(Integer catProdAlmCod, String catProdAlmDes, String catProdAlmImg, boolean catProdAlmEst) {
        this.catProdAlmCod = catProdAlmCod;
        this.catProdAlmDes = catProdAlmDes;
        this.catProdAlmImg = catProdAlmImg;
        this.catProdAlmEst = catProdAlmEst;
    }

    public Integer getCatProdAlmCod() {
        return catProdAlmCod;
    }

    public void setCatProdAlmCod(Integer catProdAlmCod) {
        this.catProdAlmCod = catProdAlmCod;
    }

    public String getCatProdAlmDes() {
        return catProdAlmDes;
    }

    public void setCatProdAlmDes(String catProdAlmDes) {
        this.catProdAlmDes = catProdAlmDes;
    }

    public String getCatProdAlmImg() {
        return catProdAlmImg;
    }

    public void setCatProdAlmImg(String catProdAlmImg) {
        this.catProdAlmImg = catProdAlmImg;
    }

    public boolean getCatProdAlmEst() {
        return catProdAlmEst;
    }

    public void setCatProdAlmEst(boolean catProdAlmEst) {
        this.catProdAlmEst = catProdAlmEst;
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
        hash += (catProdAlmCod != null ? catProdAlmCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaProductoAlmacen)) {
            return false;
        }
        CategoriaProductoAlmacen other = (CategoriaProductoAlmacen) object;
        if ((this.catProdAlmCod == null && other.catProdAlmCod != null) || (this.catProdAlmCod != null && !this.catProdAlmCod.equals(other.catProdAlmCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.CategoriaProductoAlmacen[ catProdAlmCod=" + catProdAlmCod + " ]";
    }
    
}
