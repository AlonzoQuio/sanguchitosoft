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
import com.epissoft.sanguchito.persistencia.TipoFacturacion;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.IllegalOrphanException;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Hisae Shizumaru
 */
public class TipoFacturacionJpaController implements Serializable {

    public TipoFacturacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoFacturacion tipoFacturacion) {
        if (tipoFacturacion.getFacturacionCollection() == null) {
            tipoFacturacion.setFacturacionCollection(new ArrayList<Facturacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Facturacion> attachedFacturacionCollection = new ArrayList<Facturacion>();
            for (Facturacion facturacionCollectionFacturacionToAttach : tipoFacturacion.getFacturacionCollection()) {
                facturacionCollectionFacturacionToAttach = em.getReference(facturacionCollectionFacturacionToAttach.getClass(), facturacionCollectionFacturacionToAttach.getFacCod());
                attachedFacturacionCollection.add(facturacionCollectionFacturacionToAttach);
            }
            tipoFacturacion.setFacturacionCollection(attachedFacturacionCollection);
            em.persist(tipoFacturacion);
            for (Facturacion facturacionCollectionFacturacion : tipoFacturacion.getFacturacionCollection()) {
                TipoFacturacion oldTipFacCodOfFacturacionCollectionFacturacion = facturacionCollectionFacturacion.getTipFacCod();
                facturacionCollectionFacturacion.setTipFacCod(tipoFacturacion);
                facturacionCollectionFacturacion = em.merge(facturacionCollectionFacturacion);
                if (oldTipFacCodOfFacturacionCollectionFacturacion != null) {
                    oldTipFacCodOfFacturacionCollectionFacturacion.getFacturacionCollection().remove(facturacionCollectionFacturacion);
                    oldTipFacCodOfFacturacionCollectionFacturacion = em.merge(oldTipFacCodOfFacturacionCollectionFacturacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoFacturacion tipoFacturacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoFacturacion persistentTipoFacturacion = em.find(TipoFacturacion.class, tipoFacturacion.getTipFacCod());
            Collection<Facturacion> facturacionCollectionOld = persistentTipoFacturacion.getFacturacionCollection();
            Collection<Facturacion> facturacionCollectionNew = tipoFacturacion.getFacturacionCollection();
            List<String> illegalOrphanMessages = null;
            for (Facturacion facturacionCollectionOldFacturacion : facturacionCollectionOld) {
                if (!facturacionCollectionNew.contains(facturacionCollectionOldFacturacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Facturacion " + facturacionCollectionOldFacturacion + " since its tipFacCod field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Facturacion> attachedFacturacionCollectionNew = new ArrayList<Facturacion>();
            for (Facturacion facturacionCollectionNewFacturacionToAttach : facturacionCollectionNew) {
                facturacionCollectionNewFacturacionToAttach = em.getReference(facturacionCollectionNewFacturacionToAttach.getClass(), facturacionCollectionNewFacturacionToAttach.getFacCod());
                attachedFacturacionCollectionNew.add(facturacionCollectionNewFacturacionToAttach);
            }
            facturacionCollectionNew = attachedFacturacionCollectionNew;
            tipoFacturacion.setFacturacionCollection(facturacionCollectionNew);
            tipoFacturacion = em.merge(tipoFacturacion);
            for (Facturacion facturacionCollectionNewFacturacion : facturacionCollectionNew) {
                if (!facturacionCollectionOld.contains(facturacionCollectionNewFacturacion)) {
                    TipoFacturacion oldTipFacCodOfFacturacionCollectionNewFacturacion = facturacionCollectionNewFacturacion.getTipFacCod();
                    facturacionCollectionNewFacturacion.setTipFacCod(tipoFacturacion);
                    facturacionCollectionNewFacturacion = em.merge(facturacionCollectionNewFacturacion);
                    if (oldTipFacCodOfFacturacionCollectionNewFacturacion != null && !oldTipFacCodOfFacturacionCollectionNewFacturacion.equals(tipoFacturacion)) {
                        oldTipFacCodOfFacturacionCollectionNewFacturacion.getFacturacionCollection().remove(facturacionCollectionNewFacturacion);
                        oldTipFacCodOfFacturacionCollectionNewFacturacion = em.merge(oldTipFacCodOfFacturacionCollectionNewFacturacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoFacturacion.getTipFacCod();
                if (findTipoFacturacion(id) == null) {
                    throw new NonexistentEntityException("The tipoFacturacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoFacturacion tipoFacturacion;
            try {
                tipoFacturacion = em.getReference(TipoFacturacion.class, id);
                tipoFacturacion.getTipFacCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoFacturacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Facturacion> facturacionCollectionOrphanCheck = tipoFacturacion.getFacturacionCollection();
            for (Facturacion facturacionCollectionOrphanCheckFacturacion : facturacionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoFacturacion (" + tipoFacturacion + ") cannot be destroyed since the Facturacion " + facturacionCollectionOrphanCheckFacturacion + " in its facturacionCollection field has a non-nullable tipFacCod field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoFacturacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoFacturacion> findTipoFacturacionEntities() {
        return findTipoFacturacionEntities(true, -1, -1);
    }

    public List<TipoFacturacion> findTipoFacturacionEntities(int maxResults, int firstResult) {
        return findTipoFacturacionEntities(false, maxResults, firstResult);
    }

    private List<TipoFacturacion> findTipoFacturacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoFacturacion.class));
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

    public TipoFacturacion findTipoFacturacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoFacturacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoFacturacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoFacturacion> rt = cq.from(TipoFacturacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
