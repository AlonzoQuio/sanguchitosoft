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
import com.epissoft.sanguchito.persistencia.Facturacion;
import com.epissoft.sanguchito.persistencia.FacturacionDetalle;
import com.epissoft.sanguchito.persistencia.Producto;
import com.epissoft.sanguchito.persistencia.FacturaciondExtra;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.NonexistentEntityException;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Hisae Shizumaru
 */
public class FacturacionDetalleJpaController implements Serializable {

    public FacturacionDetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FacturacionDetalle facturacionDetalle) throws PreexistingEntityException, Exception {
        if (facturacionDetalle.getFacturaciondExtraCollection() == null) {
            facturacionDetalle.setFacturaciondExtraCollection(new ArrayList<FacturaciondExtra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facturacion facCod = facturacionDetalle.getFacCod();
            if (facCod != null) {
                facCod = em.getReference(facCod.getClass(), facCod.getFacCod());
                facturacionDetalle.setFacCod(facCod);
            }
            Producto proCod = facturacionDetalle.getProCod();
            if (proCod != null) {
                proCod = em.getReference(proCod.getClass(), proCod.getProCod());
                facturacionDetalle.setProCod(proCod);
            }
            Collection<FacturaciondExtra> attachedFacturaciondExtraCollection = new ArrayList<FacturaciondExtra>();
            for (FacturaciondExtra facturaciondExtraCollectionFacturaciondExtraToAttach : facturacionDetalle.getFacturaciondExtraCollection()) {
                facturaciondExtraCollectionFacturaciondExtraToAttach = em.getReference(facturaciondExtraCollectionFacturaciondExtraToAttach.getClass(), facturaciondExtraCollectionFacturaciondExtraToAttach.getFacExtDetCod());
                attachedFacturaciondExtraCollection.add(facturaciondExtraCollectionFacturaciondExtraToAttach);
            }
            facturacionDetalle.setFacturaciondExtraCollection(attachedFacturaciondExtraCollection);
            em.persist(facturacionDetalle);
            if (facCod != null) {
                facCod.getFacturacionDetalleCollection().add(facturacionDetalle);
                facCod = em.merge(facCod);
            }
            if (proCod != null) {
                proCod.getFacturacionDetalleCollection().add(facturacionDetalle);
                proCod = em.merge(proCod);
            }
            for (FacturaciondExtra facturaciondExtraCollectionFacturaciondExtra : facturacionDetalle.getFacturaciondExtraCollection()) {
                FacturacionDetalle oldFacDetSecOfFacturaciondExtraCollectionFacturaciondExtra = facturaciondExtraCollectionFacturaciondExtra.getFacDetSec();
                facturaciondExtraCollectionFacturaciondExtra.setFacDetSec(facturacionDetalle);
                facturaciondExtraCollectionFacturaciondExtra = em.merge(facturaciondExtraCollectionFacturaciondExtra);
                if (oldFacDetSecOfFacturaciondExtraCollectionFacturaciondExtra != null) {
                    oldFacDetSecOfFacturaciondExtraCollectionFacturaciondExtra.getFacturaciondExtraCollection().remove(facturaciondExtraCollectionFacturaciondExtra);
                    oldFacDetSecOfFacturaciondExtraCollectionFacturaciondExtra = em.merge(oldFacDetSecOfFacturaciondExtraCollectionFacturaciondExtra);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFacturacionDetalle(facturacionDetalle.getFacDetSec()) != null) {
                throw new PreexistingEntityException("FacturacionDetalle " + facturacionDetalle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FacturacionDetalle facturacionDetalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FacturacionDetalle persistentFacturacionDetalle = em.find(FacturacionDetalle.class, facturacionDetalle.getFacDetSec());
            Facturacion facCodOld = persistentFacturacionDetalle.getFacCod();
            Facturacion facCodNew = facturacionDetalle.getFacCod();
            Producto proCodOld = persistentFacturacionDetalle.getProCod();
            Producto proCodNew = facturacionDetalle.getProCod();
            Collection<FacturaciondExtra> facturaciondExtraCollectionOld = persistentFacturacionDetalle.getFacturaciondExtraCollection();
            Collection<FacturaciondExtra> facturaciondExtraCollectionNew = facturacionDetalle.getFacturaciondExtraCollection();
            if (facCodNew != null) {
                facCodNew = em.getReference(facCodNew.getClass(), facCodNew.getFacCod());
                facturacionDetalle.setFacCod(facCodNew);
            }
            if (proCodNew != null) {
                proCodNew = em.getReference(proCodNew.getClass(), proCodNew.getProCod());
                facturacionDetalle.setProCod(proCodNew);
            }
            Collection<FacturaciondExtra> attachedFacturaciondExtraCollectionNew = new ArrayList<FacturaciondExtra>();
            for (FacturaciondExtra facturaciondExtraCollectionNewFacturaciondExtraToAttach : facturaciondExtraCollectionNew) {
                facturaciondExtraCollectionNewFacturaciondExtraToAttach = em.getReference(facturaciondExtraCollectionNewFacturaciondExtraToAttach.getClass(), facturaciondExtraCollectionNewFacturaciondExtraToAttach.getFacExtDetCod());
                attachedFacturaciondExtraCollectionNew.add(facturaciondExtraCollectionNewFacturaciondExtraToAttach);
            }
            facturaciondExtraCollectionNew = attachedFacturaciondExtraCollectionNew;
            facturacionDetalle.setFacturaciondExtraCollection(facturaciondExtraCollectionNew);
            facturacionDetalle = em.merge(facturacionDetalle);
            if (facCodOld != null && !facCodOld.equals(facCodNew)) {
                facCodOld.getFacturacionDetalleCollection().remove(facturacionDetalle);
                facCodOld = em.merge(facCodOld);
            }
            if (facCodNew != null && !facCodNew.equals(facCodOld)) {
                facCodNew.getFacturacionDetalleCollection().add(facturacionDetalle);
                facCodNew = em.merge(facCodNew);
            }
            if (proCodOld != null && !proCodOld.equals(proCodNew)) {
                proCodOld.getFacturacionDetalleCollection().remove(facturacionDetalle);
                proCodOld = em.merge(proCodOld);
            }
            if (proCodNew != null && !proCodNew.equals(proCodOld)) {
                proCodNew.getFacturacionDetalleCollection().add(facturacionDetalle);
                proCodNew = em.merge(proCodNew);
            }
            for (FacturaciondExtra facturaciondExtraCollectionOldFacturaciondExtra : facturaciondExtraCollectionOld) {
                if (!facturaciondExtraCollectionNew.contains(facturaciondExtraCollectionOldFacturaciondExtra)) {
                    facturaciondExtraCollectionOldFacturaciondExtra.setFacDetSec(null);
                    facturaciondExtraCollectionOldFacturaciondExtra = em.merge(facturaciondExtraCollectionOldFacturaciondExtra);
                }
            }
            for (FacturaciondExtra facturaciondExtraCollectionNewFacturaciondExtra : facturaciondExtraCollectionNew) {
                if (!facturaciondExtraCollectionOld.contains(facturaciondExtraCollectionNewFacturaciondExtra)) {
                    FacturacionDetalle oldFacDetSecOfFacturaciondExtraCollectionNewFacturaciondExtra = facturaciondExtraCollectionNewFacturaciondExtra.getFacDetSec();
                    facturaciondExtraCollectionNewFacturaciondExtra.setFacDetSec(facturacionDetalle);
                    facturaciondExtraCollectionNewFacturaciondExtra = em.merge(facturaciondExtraCollectionNewFacturaciondExtra);
                    if (oldFacDetSecOfFacturaciondExtraCollectionNewFacturaciondExtra != null && !oldFacDetSecOfFacturaciondExtraCollectionNewFacturaciondExtra.equals(facturacionDetalle)) {
                        oldFacDetSecOfFacturaciondExtraCollectionNewFacturaciondExtra.getFacturaciondExtraCollection().remove(facturaciondExtraCollectionNewFacturaciondExtra);
                        oldFacDetSecOfFacturaciondExtraCollectionNewFacturaciondExtra = em.merge(oldFacDetSecOfFacturaciondExtraCollectionNewFacturaciondExtra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = facturacionDetalle.getFacDetSec();
                if (findFacturacionDetalle(id) == null) {
                    throw new NonexistentEntityException("The facturacionDetalle with id " + id + " no longer exists.");
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
            FacturacionDetalle facturacionDetalle;
            try {
                facturacionDetalle = em.getReference(FacturacionDetalle.class, id);
                facturacionDetalle.getFacDetSec();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facturacionDetalle with id " + id + " no longer exists.", enfe);
            }
            Facturacion facCod = facturacionDetalle.getFacCod();
            if (facCod != null) {
                facCod.getFacturacionDetalleCollection().remove(facturacionDetalle);
                facCod = em.merge(facCod);
            }
            Producto proCod = facturacionDetalle.getProCod();
            if (proCod != null) {
                proCod.getFacturacionDetalleCollection().remove(facturacionDetalle);
                proCod = em.merge(proCod);
            }
            Collection<FacturaciondExtra> facturaciondExtraCollection = facturacionDetalle.getFacturaciondExtraCollection();
            for (FacturaciondExtra facturaciondExtraCollectionFacturaciondExtra : facturaciondExtraCollection) {
                facturaciondExtraCollectionFacturaciondExtra.setFacDetSec(null);
                facturaciondExtraCollectionFacturaciondExtra = em.merge(facturaciondExtraCollectionFacturaciondExtra);
            }
            em.remove(facturacionDetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FacturacionDetalle> findFacturacionDetalleEntities() {
        return findFacturacionDetalleEntities(true, -1, -1);
    }

    public List<FacturacionDetalle> findFacturacionDetalleEntities(int maxResults, int firstResult) {
        return findFacturacionDetalleEntities(false, maxResults, firstResult);
    }

    private List<FacturacionDetalle> findFacturacionDetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FacturacionDetalle.class));
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

    public FacturacionDetalle findFacturacionDetalle(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FacturacionDetalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturacionDetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FacturacionDetalle> rt = cq.from(FacturacionDetalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
