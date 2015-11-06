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
import com.epissoft.sanguchito.persistencia.Gastos;
import com.epissoft.sanguchito.persistencia.TipoDocumento;
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
public class TipoDocumentoJpaController implements Serializable {

    public TipoDocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoDocumento tipoDocumento) {
        if (tipoDocumento.getGastosCollection() == null) {
            tipoDocumento.setGastosCollection(new ArrayList<Gastos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Gastos> attachedGastosCollection = new ArrayList<Gastos>();
            for (Gastos gastosCollectionGastosToAttach : tipoDocumento.getGastosCollection()) {
                gastosCollectionGastosToAttach = em.getReference(gastosCollectionGastosToAttach.getClass(), gastosCollectionGastosToAttach.getGasCod());
                attachedGastosCollection.add(gastosCollectionGastosToAttach);
            }
            tipoDocumento.setGastosCollection(attachedGastosCollection);
            em.persist(tipoDocumento);
            for (Gastos gastosCollectionGastos : tipoDocumento.getGastosCollection()) {
                TipoDocumento oldTipDocCodOfGastosCollectionGastos = gastosCollectionGastos.getTipDocCod();
                gastosCollectionGastos.setTipDocCod(tipoDocumento);
                gastosCollectionGastos = em.merge(gastosCollectionGastos);
                if (oldTipDocCodOfGastosCollectionGastos != null) {
                    oldTipDocCodOfGastosCollectionGastos.getGastosCollection().remove(gastosCollectionGastos);
                    oldTipDocCodOfGastosCollectionGastos = em.merge(oldTipDocCodOfGastosCollectionGastos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoDocumento tipoDocumento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoDocumento persistentTipoDocumento = em.find(TipoDocumento.class, tipoDocumento.getTipDocCod());
            Collection<Gastos> gastosCollectionOld = persistentTipoDocumento.getGastosCollection();
            Collection<Gastos> gastosCollectionNew = tipoDocumento.getGastosCollection();
            Collection<Gastos> attachedGastosCollectionNew = new ArrayList<Gastos>();
            for (Gastos gastosCollectionNewGastosToAttach : gastosCollectionNew) {
                gastosCollectionNewGastosToAttach = em.getReference(gastosCollectionNewGastosToAttach.getClass(), gastosCollectionNewGastosToAttach.getGasCod());
                attachedGastosCollectionNew.add(gastosCollectionNewGastosToAttach);
            }
            gastosCollectionNew = attachedGastosCollectionNew;
            tipoDocumento.setGastosCollection(gastosCollectionNew);
            tipoDocumento = em.merge(tipoDocumento);
            for (Gastos gastosCollectionOldGastos : gastosCollectionOld) {
                if (!gastosCollectionNew.contains(gastosCollectionOldGastos)) {
                    gastosCollectionOldGastos.setTipDocCod(null);
                    gastosCollectionOldGastos = em.merge(gastosCollectionOldGastos);
                }
            }
            for (Gastos gastosCollectionNewGastos : gastosCollectionNew) {
                if (!gastosCollectionOld.contains(gastosCollectionNewGastos)) {
                    TipoDocumento oldTipDocCodOfGastosCollectionNewGastos = gastosCollectionNewGastos.getTipDocCod();
                    gastosCollectionNewGastos.setTipDocCod(tipoDocumento);
                    gastosCollectionNewGastos = em.merge(gastosCollectionNewGastos);
                    if (oldTipDocCodOfGastosCollectionNewGastos != null && !oldTipDocCodOfGastosCollectionNewGastos.equals(tipoDocumento)) {
                        oldTipDocCodOfGastosCollectionNewGastos.getGastosCollection().remove(gastosCollectionNewGastos);
                        oldTipDocCodOfGastosCollectionNewGastos = em.merge(oldTipDocCodOfGastosCollectionNewGastos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoDocumento.getTipDocCod();
                if (findTipoDocumento(id) == null) {
                    throw new NonexistentEntityException("The tipoDocumento with id " + id + " no longer exists.");
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
            TipoDocumento tipoDocumento;
            try {
                tipoDocumento = em.getReference(TipoDocumento.class, id);
                tipoDocumento.getTipDocCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoDocumento with id " + id + " no longer exists.", enfe);
            }
            Collection<Gastos> gastosCollection = tipoDocumento.getGastosCollection();
            for (Gastos gastosCollectionGastos : gastosCollection) {
                gastosCollectionGastos.setTipDocCod(null);
                gastosCollectionGastos = em.merge(gastosCollectionGastos);
            }
            em.remove(tipoDocumento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoDocumento> findTipoDocumentoEntities() {
        return findTipoDocumentoEntities(true, -1, -1);
    }

    public List<TipoDocumento> findTipoDocumentoEntities(int maxResults, int firstResult) {
        return findTipoDocumentoEntities(false, maxResults, firstResult);
    }

    private List<TipoDocumento> findTipoDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoDocumento.class));
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

    public TipoDocumento findTipoDocumento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoDocumento.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoDocumento> rt = cq.from(TipoDocumento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
