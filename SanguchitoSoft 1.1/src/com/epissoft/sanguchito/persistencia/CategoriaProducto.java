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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "categoria_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaProducto.findAll", query = "SELECT c FROM CategoriaProducto c"),
    @NamedQuery(name = "CategoriaProducto.findByCatProdCod", query = "SELECT c FROM CategoriaProducto c WHERE c.catProdCod = :catProdCod"),
    @NamedQuery(name = "CategoriaProducto.findByCatProdDes", query = "SELECT c FROM CategoriaProducto c WHERE c.catProdDes = :catProdDes"),
    @NamedQuery(name = "CategoriaProducto.findByCatProdEst", query = "SELECT c FROM CategoriaProducto c WHERE c.catProdEst = :catProdEst"),
    @NamedQuery(name = "CategoriaProducto.findByCatProdImg", query = "SELECT c FROM CategoriaProducto c WHERE c.catProdImg = :catProdImg")})
public class CategoriaProducto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CatProdCod")
    private Integer catProdCod;
    @Basic(optional = false)
    @Column(name = "CatProdDes")
    private String catProdDes;
    @Basic(optional = false)
    @Column(name = "CatProdEst")
    private boolean catProdEst;
    @Basic(optional = false)
    @Column(name = "CatProdImg")
    private String catProdImg;
    @JoinTable(name = "categoria_producto_extra", joinColumns = {
        @JoinColumn(name = "CatProdCod", referencedColumnName = "CatProdCod")}, inverseJoinColumns = {
        @JoinColumn(name = "CatExtCod", referencedColumnName = "CatExtCod")})
    @ManyToMany
    private Collection<CategoriaExtra> categoriaExtraCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catProdCod")
    private Collection<Producto> productoCollection;

    public CategoriaProducto() {
    }

    public CategoriaProducto(Integer catProdCod) {
        this.catProdCod = catProdCod;
    }

    public CategoriaProducto(Integer catProdCod, String catProdDes, boolean catProdEst, String catProdImg) {
        this.catProdCod = catProdCod;
        this.catProdDes = catProdDes;
        this.catProdEst = catProdEst;
        this.catProdImg = catProdImg;
    }

    public Integer getCatProdCod() {
        return catProdCod;
    }

    public void setCatProdCod(Integer catProdCod) {
        this.catProdCod = catProdCod;
    }

    public String getCatProdDes() {
        return catProdDes;
    }

    public void setCatProdDes(String catProdDes) {
        this.catProdDes = catProdDes;
    }

    public boolean getCatProdEst() {
        return catProdEst;
    }

    public void setCatProdEst(boolean catProdEst) {
        this.catProdEst = catProdEst;
    }

    public String getCatProdImg() {
        return catProdImg;
    }

    public void setCatProdImg(String catProdImg) {
        this.catProdImg = catProdImg;
    }

    @XmlTransient
    public Collection<CategoriaExtra> getCategoriaExtraCollection() {
        return categoriaExtraCollection;
    }

    public void setCategoriaExtraCollection(Collection<CategoriaExtra> categoriaExtraCollection) {
        this.categoriaExtraCollection = categoriaExtraCollection;
    }

    @XmlTransient
    public Collection<Producto> getProductoCollection() {
        return productoCollection;
    }

    public void setProductoCollection(Collection<Producto> productoCollection) {
        this.productoCollection = productoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catProdCod != null ? catProdCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaProducto)) {
            return false;
        }
        CategoriaProducto other = (CategoriaProducto) object;
        if ((this.catProdCod == null && other.catProdCod != null) || (this.catProdCod != null && !this.catProdCod.equals(other.catProdCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.CategoriaProducto[ catProdCod=" + catProdCod + " ]";
    }
    
}
