/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.persistencia;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hisae Shizumaru
 */
@Entity
@Table(name = "kardex")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kardex.findByProdAlmCod", query = "SELECT k FROM Kardex k WHERE k.prodAlmCod = :prodAlmCod"),
    @NamedQuery(name = "Kardex.findAll", query = "SELECT k FROM Kardex k"),
    @NamedQuery(name = "Kardex.findByKarCod", query = "SELECT k FROM Kardex k WHERE k.karCod = :karCod"),
    @NamedQuery(name = "Kardex.findByKarFecDia", query = "SELECT k FROM Kardex k WHERE k.karFecDia = :karFecDia"),
    @NamedQuery(name = "Kardex.findByKarFecMes", query = "SELECT k FROM Kardex k WHERE k.karFecMes = :karFecMes"),
    @NamedQuery(name = "Kardex.findByKarFecAnio", query = "SELECT k FROM Kardex k WHERE k.karFecAnio = :karFecAnio")})
public class Kardex implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "KarCod")
    private Integer karCod;
    @Basic(optional = false)
    @Column(name = "KarFecDia")
    private int karFecDia;
    @Basic(optional = false)
    @Column(name = "KarFecMes")
    private int karFecMes;
    @Basic(optional = false)
    @Column(name = "KarFecAnio")
    private int karFecAnio;
    @JoinColumn(name = "ProdAlmCod", referencedColumnName = "ProdAlmCod")
    @ManyToOne
    private ProductoAlmacen prodAlmCod;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "kardex")
    private EgresoKardex egresoKardex;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "kardex")
    private IngresoKardex ingresoKardex;

    public Kardex() {
    }

    public Kardex(Integer karCod) {
        this.karCod = karCod;
    }

    public Kardex(Integer karCod, int karFecDia, int karFecMes, int karFecAnio) {
        this.karCod = karCod;
        this.karFecDia = karFecDia;
        this.karFecMes = karFecMes;
        this.karFecAnio = karFecAnio;
    }

    public Integer getKarCod() {
        return karCod;
    }

    public void setKarCod(Integer karCod) {
        this.karCod = karCod;
    }

    public int getKarFecDia() {
        return karFecDia;
    }

    public void setKarFecDia(int karFecDia) {
        this.karFecDia = karFecDia;
    }

    public int getKarFecMes() {
        return karFecMes;
    }

    public void setKarFecMes(int karFecMes) {
        this.karFecMes = karFecMes;
    }

    public int getKarFecAnio() {
        return karFecAnio;
    }

    public void setKarFecAnio(int karFecAnio) {
        this.karFecAnio = karFecAnio;
    }

    public ProductoAlmacen getProdAlmCod() {
        return prodAlmCod;
    }

    public void setProdAlmCod(ProductoAlmacen prodAlmCod) {
        this.prodAlmCod = prodAlmCod;
    }

    public EgresoKardex getEgresoKardex() {
        return egresoKardex;
    }

    public void setEgresoKardex(EgresoKardex egresoKardex) {
        this.egresoKardex = egresoKardex;
    }

    public IngresoKardex getIngresoKardex() {
        return ingresoKardex;
    }

    public void setIngresoKardex(IngresoKardex ingresoKardex) {
        this.ingresoKardex = ingresoKardex;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (karCod != null ? karCod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kardex)) {
            return false;
        }
        Kardex other = (Kardex) object;
        if ((this.karCod == null && other.karCod != null) || (this.karCod != null && !this.karCod.equals(other.karCod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.epissoft.sanguchito.persistencia.Kardex[ karCod=" + karCod + " ]";
    }
    
}
