/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.persistencia.jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.epissoft.sanguchito.persistencia.CategoriaProducto;
import com.epissoft.sanguchito.persistencia.Promociones;
import java.util.ArrayList;
import java.util.Collection;
import com.epissoft.sanguchito.persistencia.FacturacionDetalle;
import com.epissoft.sanguchito.persistencia.Producto;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Hisae Shizumaru
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        if (producto.getPromocionesCollection() == null) {
            producto.setPromocionesCollection(new ArrayList<Promociones>());
        }
        if (producto.getFacturacionDetalleCollection() == null) {
            producto.setFacturacionDetalleCollection(new ArrayList<FacturacionDetalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriaProducto catProdCod = producto.getCatProdCod();
            if (catProdCod != null) {
                catProdCod = em.getReference(catProdCod.getClass(), catProdCod.getCatProdCod());
                producto.setCatProdCod(catProdCod);
            }
            Collection<Promociones> attachedPromocionesCollection = new ArrayList<Promociones>();
            for (Promociones promocionesCollectionPromocionesToAttach : producto.getPromocionesCollection()) {
                promocionesCollectionPromocionesToAttach = em.getReference(promocionesCollectionPromocionesToAttach.getClass(), promocionesCollectionPromocionesToAttach.getPromCod());
                attachedPromocionesCollection.add(promocionesCollectionPromocionesToAttach);
            }
            producto.setPromocionesCollection(attachedPromocionesCollection);
            Collection<FacturacionDetalle> attachedFacturacionDetalleCollection = new ArrayList<FacturacionDetalle>();
            for (FacturacionDetalle facturacionDetalleCollectionFacturacionDetalleToAttach : producto.getFacturacionDetalleCollection()) {
                facturacionDetalleCollectionFacturacionDetalleToAttach = em.getReference(facturacionDetalleCollectionFacturacionDetalleToAttach.getClass(), facturacionDetalleCollectionFacturacionDetalleToAttach.getFacDetSec());
                attachedFacturacionDetalleCollection.add(facturacionDetalleCollectionFacturacionDetalleToAttach);
            }
            producto.setFacturacionDetalleCollection(attachedFacturacionDetalleCollection);
            em.persist(producto);
            if (catProdCod != null) {
                catProdCod.getProductoCollection().add(producto);
                catProdCod = em.merge(catProdCod);
            }
            for (Promociones promocionesCollectionPromociones : producto.getPromocionesCollection()) {
                Producto oldProCodOfPromocionesCollectionPromociones = promocionesCollectionPromociones.getProCod();
                promocionesCollectionPromociones.setProCod(producto);
                promocionesCollectionPromociones = em.merge(promocionesCollectionPromociones);
                if (oldProCodOfPromocionesCollectionPromociones != null) {
                    oldProCodOfPromocionesCollectionPromociones.getPromocionesCollection().remove(promocionesCollectionPromociones);
                    oldProCodOfPromocionesCollectionPromociones = em.merge(oldProCodOfPromocionesCollectionPromociones);
                }
            }
            for (FacturacionDetalle facturacionDetalleCollectionFacturacionDetalle : producto.getFacturacionDetalleCollection()) {
                Producto oldProCodOfFacturacionDetalleCollectionFacturacionDetalle = facturacionDetalleCollectionFacturacionDetalle.getProCod();
                facturacionDetalleCollectionFacturacionDetalle.setProCod(producto);
                facturacionDetalleCollectionFacturacionDetalle = em.merge(facturacionDetalleCollectionFacturacionDetalle);
                if (oldProCodOfFacturacionDetalleCollectionFacturacionDetalle != null) {
                    oldProCodOfFacturacionDetalleCollectionFacturacionDetalle.getFacturacionDetalleCollection().remove(facturacionDetalleCollectionFacturacionDetalle);
                    oldProCodOfFacturacionDetalleCollectionFacturacionDetalle = em.merge(oldProCodOfFacturacionDetalleCollectionFacturacionDetalle);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getProCod());
            CategoriaProducto catProdCodOld = persistentProducto.getCatProdCod();
            CategoriaProducto catProdCodNew = producto.getCatProdCod();
            Collection<Promociones> promocionesCollectionOld = persistentProducto.getPromocionesCollection();
            Collection<Promociones> promocionesCollectionNew = producto.getPromocionesCollection();
            Collection<FacturacionDetalle> facturacionDetalleCollectionOld = persistentProducto.getFacturacionDetalleCollection();
            Collection<FacturacionDetalle> facturacionDetalleCollectionNew = producto.getFacturacionDetalleCollection();
            if (catProdCodNew != null) {
                catProdCodNew = em.getReference(catProdCodNew.getClass(), catProdCodNew.getCatProdCod());
                producto.setCatProdCod(catProdCodNew);
            }
            Collection<Promociones> attachedPromocionesCollectionNew = new ArrayList<Promociones>();
            for (Promociones promocionesCollectionNewPromocionesToAttach : promocionesCollectionNew) {
                promocionesCollectionNewPromocionesToAttach = em.getReference(promocionesCollectionNewPromocionesToAttach.getClass(), promocionesCollectionNewPromocionesToAttach.getPromCod());
                attachedPromocionesCollectionNew.add(promocionesCollectionNewPromocionesToAttach);
            }
            promocionesCollectionNew = attachedPromocionesCollectionNew;
            producto.setPromocionesCollection(promocionesCollectionNew);
            Collection<FacturacionDetalle> attachedFacturacionDetalleCollectionNew = new ArrayList<FacturacionDetalle>();
            for (FacturacionDetalle facturacionDetalleCollectionNewFacturacionDetalleToAttach : facturacionDetalleCollectionNew) {
                facturacionDetalleCollectionNewFacturacionDetalleToAttach = em.getReference(facturacionDetalleCollectionNewFacturacionDetalleToAttach.getClass(), facturacionDetalleCollectionNewFacturacionDetalleToAttach.getFacDetSec());
                attachedFacturacionDetalleCollectionNew.add(facturacionDetalleCollectionNewFacturacionDetalleToAttach);
            }
            facturacionDetalleCollectionNew = attachedFacturacionDetalleCollectionNew;
            producto.setFacturacionDetalleCollection(facturacionDetalleCollectionNew);
            producto = em.merge(producto);
            if (catProdCodOld != null && !catProdCodOld.equals(catProdCodNew)) {
                catProdCodOld.getProductoCollection().remove(producto);
                catProdCodOld = em.merge(catProdCodOld);
            }
            if (catProdCodNew != null && !catProdCodNew.equals(catProdCodOld)) {
                catProdCodNew.getProductoCollection().add(producto);
                catProdCodNew = em.merge(catProdCodNew);
            }
            for (Promociones promocionesCollectionOldPromociones : promocionesCollectionOld) {
                if (!promocionesCollectionNew.contains(promocionesCollectionOldPromociones)) {
                    promocionesCollectionOldPromociones.setProCod(null);
                    promocionesCollectionOldPromociones = em.merge(promocionesCollectionOldPromociones);
                }
            }
            for (Promociones promocionesCollectionNewPromociones : promocionesCollectionNew) {
                if (!promocionesCollectionOld.contains(promocionesCollectionNewPromociones)) {
                    Producto oldProCodOfPromocionesCollectionNewPromociones = promocionesCollectionNewPromociones.getProCod();
                    promocionesCollectionNewPromociones.setProCod(producto);
                    promocionesCollectionNewPromociones = em.merge(promocionesCollectionNewPromociones);
                    if (oldProCodOfPromocionesCollectionNewPromociones != null && !oldProCodOfPromocionesCollectionNewPromociones.equals(producto)) {
                        oldProCodOfPromocionesCollectionNewPromociones.getPromocionesCollection().remove(promocionesCollectionNewPromociones);
                        oldProCodOfPromocionesCollectionNewPromociones = em.merge(oldProCodOfPromocionesCollectionNewPromociones);
                    }
                }
            }
            for (FacturacionDetalle facturacionDetalleCollectionOldFacturacionDetalle : facturacionDetalleCollectionOld) {
                if (!facturacionDetalleCollectionNew.contains(facturacionDetalleCollectionOldFacturacionDetalle)) {
                    facturacionDetalleCollectionOldFacturacionDetalle.setProCod(null);
                    facturacionDetalleCollectionOldFacturacionDetalle = em.merge(facturacionDetalleCollectionOldFacturacionDetalle);
                }
            }
            for (FacturacionDetalle facturacionDetalleCollectionNewFacturacionDetalle : facturacionDetalleCollectionNew) {
                if (!facturacionDetalleCollectionOld.contains(facturacionDetalleCollectionNewFacturacionDetalle)) {
                    Producto oldProCodOfFacturacionDetalleCollectionNewFacturacionDetalle = facturacionDetalleCollectionNewFacturacionDetalle.getProCod();
                    facturacionDetalleCollectionNewFacturacionDetalle.setProCod(producto);
                    facturacionDetalleCollectionNewFacturacionDetalle = em.merge(facturacionDetalleCollectionNewFacturacionDetalle);
                    if (oldProCodOfFacturacionDetalleCollectionNewFacturacionDetalle != null && !oldProCodOfFacturacionDetalleCollectionNewFacturacionDetalle.equals(producto)) {
                        oldProCodOfFacturacionDetalleCollectionNewFacturacionDetalle.getFacturacionDetalleCollection().remove(facturacionDetalleCollectionNewFacturacionDetalle);
                        oldProCodOfFacturacionDetalleCollectionNewFacturacionDetalle = em.merge(oldProCodOfFacturacionDetalleCollectionNewFacturacionDetalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producto.getProCod();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getProCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            CategoriaProducto catProdCod = producto.getCatProdCod();
            if (catProdCod != null) {
                catProdCod.getProductoCollection().remove(producto);
                catProdCod = em.merge(catProdCod);
            }
            Collection<Promociones> promocionesCollection = producto.getPromocionesCollection();
            for (Promociones promocionesCollectionPromociones : promocionesCollection) {
                promocionesCollectionPromociones.setProCod(null);
                promocionesCollectionPromociones = em.merge(promocionesCollectionPromociones);
            }
            Collection<FacturacionDetalle> facturacionDetalleCollection = producto.getFacturacionDetalleCollection();
            for (FacturacionDetalle facturacionDetalleCollectionFacturacionDetalle : facturacionDetalleCollection) {
                facturacionDetalleCollectionFacturacionDetalle.setProCod(null);
                facturacionDetalleCollectionFacturacionDetalle = em.merge(facturacionDetalleCollectionFacturacionDetalle);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }
    public Producto findProducto(String detalle){
        List list=getEntityManager().createNamedQuery("Producto.findByProDes").setParameter("proDes", detalle).getResultList();
        if(list.isEmpty()){
            return null;
        }else{
            return (Producto)list.get(0);
        }
    }
    public List<Producto> findProducto(boolean habilitado){
        List<Producto> lista=getEntityManager().createNamedQuery("Producto.findByProEst").setParameter("proEst",habilitado).getResultList();
        return lista;
    }
    public List<Producto> findProductoByCategoria(int catProducto, boolean habilitado){
        Query consulta = getEntityManager().createNamedQuery("Producto.findByCatProdCod");
        consulta.setParameter("catProdCod", new CategoriaProducto(catProducto));
        consulta.setParameter("proEst",habilitado);
        List<Producto> lista= consulta.getResultList();
        return lista;
    }
    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
