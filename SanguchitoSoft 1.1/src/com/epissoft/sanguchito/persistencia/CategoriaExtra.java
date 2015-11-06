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
@Table(name = "categoria_extra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaExtra.findAll", query = "SELECT c FROM CategoriaExtra c"),
    @NamedQuery(name = "CategoriaExtra.findByCatExtCod", query = "SELECT c FROM CategoriaExtra c WHERE c.catExtCod = :catExtCod"),
    @NamedQuery(name = "CategoriaExtra.findByCatExtNom", query = "SELECT c FROM CategoriaExtra c WHERE c.catExtNom = :catExtNom"),
    @NamedQuery(name = "CategoriaExtra.findByCatExtDes", query = "SELECT c FROM CategoriaExtra c WHERE c.catExtDes = :catExtDes"),
    @NamedQuery(name = "CategoriaExtra.findByCatExtImg", query = "SELECT c FROM CategoriaExtra c WHERE c.catExtImg = :catExtImg"),
    @NamedQuery(name = "CategoriaExtra.findByCatExtEst", query = "SELECT c FROM CategoriaExtra c WHERE c.catExtEst = :catExtEst")})
public class CategoriaExtra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CatExtCod")
    private Integer catExtCod;
    @Basic(optional = false)
    @Column(name = "CatExtNom")
    private String catExtNom;
    @Basic(optional = false)
    @Column(name = "CatExtDes")
    private String catExtDes;
    @Basic(optional = false)
    @Column(name = "CatExtImg")
    private String catExtImg;
    @Basic(optional = false)
    @Column(name = "CatExtEst")
    private boolean catExtEst;
    @ManyToMany(mappedBy = "categoriaExtraCollection")
    private Collection<CategoriaProducto> categoriaProductoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "catExtCod")
    private Collection<Extras> extrasCollection;

    public CategoriaExtra() {
    }

    public CategoriaExtra(Integer catExtCod) {
        this.catExtCod = catExtCod;
    }

    public CategoriaExtra(Integer catExtCod, String catExtNom, String catExtDes, String catExtImg, boolean catExtEst) {
        this.catExtCod = catExtCod;
        this.catExtNom = catExtNom;
        this.catExtDes = catExtDes;
        this.catExtImg = catExtImg;
        this.catExtEst = catExtEst;
    }

    public Integer getCatExtCod() {
        return catExtCod;
    }

    public void setCatExtCod(Integer catExtCod) {
        this.catExtCod = catExtCod;
    }

    public String getCatExtNom() {
        return catExtNom;
    }

    public void setCatExtNom(String catExtNom) {
        this.catExtNom = catExtNom;
    }

    public String getCatExtDes() {
        return catExtDes;
    }

    public void setCatExtDes(String catExtDes) {
        this.catExtDes = catExtDes;
    }

    public String getCatExtImg() {
        return catExtImg;
    }

    public void setCatExtImg(String catExtImg) {
        this.catExtImg = catExtImg;
    }

    public boolean getCatExtEst() {
        return catExtEst;
    }

    public void setCatExtEst(boolean catExtEst) {
        this.catExtEst = catExtEst;
    }

    @XmlTransient
    public Collection<CategoriaProducto> getCategoriaProductoCollection() {
        return categoriaProductoCollection;
    }

    public void setCategoriaProductoCollection(Collection<CategoriaProducto> categoriaProductoCollection) {
        this.categoriaProductoCollection = categoriaProductoCollection;
    }

    @XmlTransient
    public Collection<Extras> getExtrasCollection() {
        return extrasCollection;
    }

    public void setExtrasCollection(Collection<Extras> extrasCollection) {
        this.extrasCollection = extrasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catExtCod != null ? catExtCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaExtra)) {
            return false;
        }
        CategoriaExtra other = (CategoriaExtra) object;
        if ((this.catExtCod == null && other.catExtCod != null) || (this.catExtCod != null && !this.catExtCod.equals(other.catExtCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.CategoriaExtra[ catExtCod=" + catExtCod + " ]";
    }
    
}
