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
import com.epissoft.sanguchito.persistencia.TipoGasto;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.IllegalOrphanException;
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
public class TipoGastoJpaController implements Serializable {

    public TipoGastoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoGasto tipoGasto) throws PreexistingEntityException, Exception {
        if (tipoGasto.getGastosCollection() == null) {
            tipoGasto.setGastosCollection(new ArrayList<Gastos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Gastos> attachedGastosCollection = new ArrayList<Gastos>();
            for (Gastos gastosCollectionGastosToAttach : tipoGasto.getGastosCollection()) {
                gastosCollectionGastosToAttach = em.getReference(gastosCollectionGastosToAttach.getClass(), gastosCollectionGastosToAttach.getGasCod());
                attachedGastosCollection.add(gastosCollectionGastosToAttach);
            }
            tipoGasto.setGastosCollection(attachedGastosCollection);
            em.persist(tipoGasto);
            for (Gastos gastosCollectionGastos : tipoGasto.getGastosCollection()) {
                TipoGasto oldTipGasCodOfGastosCollectionGastos = gastosCollectionGastos.getTipGasCod();
                gastosCollectionGastos.setTipGasCod(tipoGasto);
                gastosCollectionGastos = em.merge(gastosCollectionGastos);
                if (oldTipGasCodOfGastosCollectionGastos != null) {
                    oldTipGasCodOfGastosCollectionGastos.getGastosCollection().remove(gastosCollectionGastos);
                    oldTipGasCodOfGastosCollectionGastos = em.merge(oldTipGasCodOfGastosCollectionGastos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoGasto(tipoGasto.getTipGasCod()) != null) {
                throw new PreexistingEntityException("TipoGasto " + tipoGasto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoGasto tipoGasto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoGasto persistentTipoGasto = em.find(TipoGasto.class, tipoGasto.getTipGasCod());
            Collection<Gastos> gastosCollectionOld = persistentTipoGasto.getGastosCollection();
            Collection<Gastos> gastosCollectionNew = tipoGasto.getGastosCollection();
            List<String> illegalOrphanMessages = null;
            for (Gastos gastosCollectionOldGastos : gastosCollectionOld) {
                if (!gastosCollectionNew.contains(gastosCollectionOldGastos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Gastos " + gastosCollectionOldGastos + " since its tipGasCod field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Gastos> attachedGastosCollectionNew = new ArrayList<Gastos>();
            for (Gastos gastosCollectionNewGastosToAttach : gastosCollectionNew) {
                gastosCollectionNewGastosToAttach = em.getReference(gastosCollectionNewGastosToAttach.getClass(), gastosCollectionNewGastosToAttach.getGasCod());
                attachedGastosCollectionNew.add(gastosCollectionNewGastosToAttach);
            }
            gastosCollectionNew = attachedGastosCollectionNew;
            tipoGasto.setGastosCollection(gastosCollectionNew);
            tipoGasto = em.merge(tipoGasto);
            for (Gastos gastosCollectionNewGastos : gastosCollectionNew) {
                if (!gastosCollectionOld.contains(gastosCollectionNewGastos)) {
                    TipoGasto oldTipGasCodOfGastosCollectionNewGastos = gastosCollectionNewGastos.getTipGasCod();
                    gastosCollectionNewGastos.setTipGasCod(tipoGasto);
                    gastosCollectionNewGastos = em.merge(gastosCollectionNewGastos);
                    if (oldTipGasCodOfGastosCollectionNewGastos != null && !oldTipGasCodOfGastosCollectionNewGastos.equals(tipoGasto)) {
                        oldTipGasCodOfGastosCollectionNewGastos.getGastosCollection().remove(gastosCollectionNewGastos);
                        oldTipGasCodOfGastosCollectionNewGastos = em.merge(oldTipGasCodOfGastosCollectionNewGastos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoGasto.getTipGasCod();
                if (findTipoGasto(id) == null) {
                    throw new NonexistentEntityException("The tipoGasto with id " + id + " no longer exists.");
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
            TipoGasto tipoGasto;
            try {
                tipoGasto = em.getReference(TipoGasto.class, id);
                tipoGasto.getTipGasCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoGasto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Gastos> gastosCollectionOrphanCheck = tipoGasto.getGastosCollection();
            for (Gastos gastosCollectionOrphanCheckGastos : gastosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoGasto (" + tipoGasto + ") cannot be destroyed since the Gastos " + gastosCollectionOrphanCheckGastos + " in its gastosCollection field has a non-nullable tipGasCod field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoGasto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoGasto> findTipoGastoEntities() {
        return findTipoGastoEntities(true, -1, -1);
    }

    public List<TipoGasto> findTipoGastoEntities(int maxResults, int firstResult) {
        return findTipoGastoEntities(false, maxResults, firstResult);
    }

    private List<TipoGasto> findTipoGastoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoGasto.class));
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

    public TipoGasto findTipoGasto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoGasto.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoGastoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoGasto> rt = cq.from(TipoGasto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
