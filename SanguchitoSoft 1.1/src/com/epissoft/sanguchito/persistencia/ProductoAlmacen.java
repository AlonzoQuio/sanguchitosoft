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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hisae Shizumaru
 */
@Entity
@Table(name = "producto_almacen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoAlmacen.findByCatProdAlmCod", query = "SELECT p FROM ProductoAlmacen p WHERE p.catProdAlmCod = :catProdAlmCod AND p.prodAlmEst = :prodAlmEst"),
    @NamedQuery(name = "ProductoAlmacen.findAll", query = "SELECT p FROM ProductoAlmacen p"),
    @NamedQuery(name = "ProductoAlmacen.findByProdAlmCod", query = "SELECT p FROM ProductoAlmacen p WHERE p.prodAlmCod = :prodAlmCod"),
    @NamedQuery(name = "ProductoAlmacen.findByProdAlmDes", query = "SELECT p FROM ProductoAlmacen p WHERE p.prodAlmDes = :prodAlmDes"),
    @NamedQuery(name = "ProductoAlmacen.findByProdAlmCant", query = "SELECT p FROM ProductoAlmacen p WHERE p.prodAlmCant = :prodAlmCant"),
    @NamedQuery(name = "ProductoAlmacen.findByProdAlmEst", query = "SELECT p FROM ProductoAlmacen p WHERE p.prodAlmEst = :prodAlmEst"),
    @NamedQuery(name = "ProductoAlmacen.findByProAlmImg", query = "SELECT p FROM ProductoAlmacen p WHERE p.proAlmImg = :proAlmImg")})
public class ProductoAlmacen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ProdAlmCod")
    private Integer prodAlmCod;
    @Basic(optional = false)
    @Column(name = "ProdAlmDes")
    private String prodAlmDes;
    @Basic(optional = false)
    @Column(name = "ProdAlmCant")
    private float prodAlmCant;
    @Basic(optional = false)
    @Column(name = "ProdAlmEst")
    private boolean prodAlmEst;
    @Basic(optional = false)
    @Column(name = "ProAlmImg")
    private String proAlmImg;
    @OneToMany(mappedBy = "prodAlmCod")
    private Collection<Kardex> kardexCollection;
    @JoinColumn(name = "CatProdAlmCod", referencedColumnName = "CatProdAlmCod")
    @ManyToOne
    private CategoriaProductoAlmacen catProdAlmCod;
    @JoinColumn(name = "UniMedCod", referencedColumnName = "UniMedCod")
    @ManyToOne(optional = false)
    private UnidadMedida uniMedCod;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "productoAlmacen")
    private Almacen almacen;

    public ProductoAlmacen() {
    }

    public ProductoAlmacen(Integer prodAlmCod) {
        this.prodAlmCod = prodAlmCod;
    }

    public ProductoAlmacen(Integer prodAlmCod, String prodAlmDes, float prodAlmCant, boolean prodAlmEst, String proAlmImg) {
        this.prodAlmCod = prodAlmCod;
        this.prodAlmDes = prodAlmDes;
        this.prodAlmCant = prodAlmCant;
        this.prodAlmEst = prodAlmEst;
        this.proAlmImg = proAlmImg;
    }

    public Integer getProdAlmCod() {
        return prodAlmCod;
    }

    public void setProdAlmCod(Integer prodAlmCod) {
        this.prodAlmCod = prodAlmCod;
    }

    public String getProdAlmDes() {
        return prodAlmDes;
    }

    public void setProdAlmDes(String prodAlmDes) {
        this.prodAlmDes = prodAlmDes;
    }

    public float getProdAlmCant() {
        return prodAlmCant;
    }

    public void setProdAlmCant(float prodAlmCant) {
        this.prodAlmCant = prodAlmCant;
    }

    public boolean getProdAlmEst() {
        return prodAlmEst;
    }

    public void setProdAlmEst(boolean prodAlmEst) {
        this.prodAlmEst = prodAlmEst;
    }

    public String getProAlmImg() {
        return proAlmImg;
    }

    public void setProAlmImg(String proAlmImg) {
        this.proAlmImg = proAlmImg;
    }

    @XmlTransient
    public Collection<Kardex> getKardexCollection() {
        return kardexCollection;
    }

    public void setKardexCollection(Collection<Kardex> kardexCollection) {
        this.kardexCollection = kardexCollection;
    }

    public CategoriaProductoAlmacen getCatProdAlmCod() {
        return catProdAlmCod;
    }

    public void setCatProdAlmCod(CategoriaProductoAlmacen catProdAlmCod) {
        this.catProdAlmCod = catProdAlmCod;
    }

    public UnidadMedida getUniMedCod() {
        return uniMedCod;
    }

    public void setUniMedCod(UnidadMedida uniMedCod) {
        this.uniMedCod = uniMedCod;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prodAlmCod != null ? prodAlmCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoAlmacen)) {
            return false;
        }
        ProductoAlmacen other = (ProductoAlmacen) object;
        if ((this.prodAlmCod == null && other.prodAlmCod != null) || (this.prodAlmCod != null && !this.prodAlmCod.equals(other.prodAlmCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.ProductoAlmacen[ prodAlmCod=" + prodAlmCod + " ]";
    }
    
}
