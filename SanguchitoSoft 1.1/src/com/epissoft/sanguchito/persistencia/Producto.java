
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

@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findByCatProdCod", query = "SELECT p FROM Producto p WHERE p.catProdCod = :catProdCod AND p.proEst = :proEst"),
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByProCod", query = "SELECT p FROM Producto p WHERE p.proCod = :proCod"),
    @NamedQuery(name = "Producto.findByProDes", query = "SELECT p FROM Producto p WHERE p.proDes = :proDes"),
    @NamedQuery(name = "Producto.findByProPreSol", query = "SELECT p FROM Producto p WHERE p.proPreSol = :proPreSol"),
    @NamedQuery(name = "Producto.findByProPreCar", query = "SELECT p FROM Producto p WHERE p.proPreCar = :proPreCar"),
    @NamedQuery(name = "Producto.findByProImg", query = "SELECT p FROM Producto p WHERE p.proImg = :proImg"),
    @NamedQuery(name = "Producto.findByProEst", query = "SELECT p FROM Producto p WHERE p.proEst = :proEst"),
    @NamedQuery(name = "Producto.findByProAlmCod", query = "SELECT p FROM Producto p WHERE p.proAlmCod = :proAlmCod")})
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ProCod")
    private Integer proCod;
    @Basic(optional = false)
    @Column(name = "ProDes")
    private String proDes;
    @Basic(optional = false)
    @Column(name = "ProPreSol")
    private float proPreSol;
    @Basic(optional = false)
    @Column(name = "ProPreCar")
    private float proPreCar;
    @Basic(optional = false)
    @Column(name = "ProImg")
    private String proImg;
    @Basic(optional = false)
    @Column(name = "ProEst")
    private boolean proEst;
    @Basic(optional = false)
    @Column(name = "ProAlmCod")
    private int proAlmCod;
    @OneToMany(mappedBy = "proCod")
    private Collection<Promociones> promocionesCollection;
    @OneToMany(mappedBy = "proCod")
    private Collection<FacturacionDetalle> facturacionDetalleCollection;
    @JoinColumn(name = "CatProdCod", referencedColumnName = "CatProdCod")
    @ManyToOne(optional = false)
    private CategoriaProducto catProdCod;

    public Producto() {
    }

    public Producto(Integer proCod) {
        this.proCod = proCod;
    }

    public Producto(Integer proCod, String proDes, float proPreSol, float proPreCar, String proImg, boolean proEst, int proAlmCod) {
        this.proCod = proCod;
        this.proDes = proDes;
        this.proPreSol = proPreSol;
        this.proPreCar = proPreCar;
        this.proImg = proImg;
        this.proEst = proEst;
        this.proAlmCod = proAlmCod;
    }

    public Integer getProCod() {
        return proCod;
    }

    public void setProCod(Integer proCod) {
        this.proCod = proCod;
    }

    public String getProDes() {
        return proDes;
    }

    public void setProDes(String proDes) {
        this.proDes = proDes;
    }

    public float getProPreSol() {
        return proPreSol;
    }

    public void setProPreSol(float proPreSol) {
        this.proPreSol = proPreSol;
    }

    public float getProPreCar() {
        return proPreCar;
    }

    public void setProPreCar(float proPreCar) {
        this.proPreCar = proPreCar;
    }

    public String getProImg() {
        return proImg;
    }

    public void setProImg(String proImg) {
        this.proImg = proImg;
    }

    public boolean getProEst() {
        return proEst;
    }

    public void setProEst(boolean proEst) {
        this.proEst = proEst;
    }

    public int getProAlmCod() {
        return proAlmCod;
    }

    public void setProAlmCod(int proAlmCod) {
        this.proAlmCod = proAlmCod;
    }

    @XmlTransient
    public Collection<Promociones> getPromocionesCollection() {
        return promocionesCollection;
    }

    public void setPromocionesCollection(Collection<Promociones> promocionesCollection) {
        this.promocionesCollection = promocionesCollection;
    }

    @XmlTransient
    public Collection<FacturacionDetalle> getFacturacionDetalleCollection() {
        return facturacionDetalleCollection;
    }

    public void setFacturacionDetalleCollection(Collection<FacturacionDetalle> facturacionDetalleCollection) {
        this.facturacionDetalleCollection = facturacionDetalleCollection;
    }

    public CategoriaProducto getCatProdCod() {
        return catProdCod;
    }

    public void setCatProdCod(CategoriaProducto catProdCod) {
        this.catProdCod = catProdCod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proCod != null ? proCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.proCod == null && other.proCod != null) || (this.proCod != null && !this.proCod.equals(other.proCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.Producto[ proCod=" + proCod + " ]";
    }
    
}
