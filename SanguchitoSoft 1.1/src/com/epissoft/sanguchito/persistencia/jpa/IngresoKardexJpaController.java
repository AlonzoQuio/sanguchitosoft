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
import com.epissoft.sanguchito.persistencia.IngresoKardex;
import com.epissoft.sanguchito.persistencia.Kardex;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.IllegalOrphanException;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.NonexistentEntityException;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Hisae Shizumaru
 */
public class IngresoKardexJpaController implements Serializable {

    public IngresoKardexJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IngresoKardex ingresoKardex) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Kardex kardexOrphanCheck = ingresoKardex.getKardex();
        if (kardexOrphanCheck != null) {
            IngresoKardex oldIngresoKardexOfKardex = kardexOrphanCheck.getIngresoKardex();
            if (oldIngresoKardexOfKardex != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Kardex " + kardexOrphanCheck + " already has an item of type IngresoKardex whose kardex column cannot be null. Please make another selection for the kardex field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Gastos gasCod = ingresoKardex.getGasCod();
            if (gasCod != null) {
                gasCod = em.getReference(gasCod.getClass(), gasCod.getGasCod());
                ingresoKardex.setGasCod(gasCod);
            }
            Kardex kardex = ingresoKardex.getKardex();
            if (kardex != null) {
                kardex = em.getReference(kardex.getClass(), kardex.getKarCod());
                ingresoKardex.setKardex(kardex);
            }
            em.persist(ingresoKardex);
            if (gasCod != null) {
                gasCod.getIngresoKardexCollection().add(ingresoKardex);
                gasCod = em.merge(gasCod);
            }
            if (kardex != null) {
                kardex.setIngresoKardex(ingresoKardex);
                kardex = em.merge(kardex);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findIngresoKardex(ingresoKardex.getKarCod()) != null) {
                throw new PreexistingEntityException("IngresoKardex " + ingresoKardex + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IngresoKardex ingresoKardex) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            IngresoKardex persistentIngresoKardex = em.find(IngresoKardex.class, ingresoKardex.getKarCod());
            Gastos gasCodOld = persistentIngresoKardex.getGasCod();
            Gastos gasCodNew = ingresoKardex.getGasCod();
            Kardex kardexOld = persistentIngresoKardex.getKardex();
            Kardex kardexNew = ingresoKardex.getKardex();
            List<String> illegalOrphanMessages = null;
            if (kardexNew != null && !kardexNew.equals(kardexOld)) {
                IngresoKardex oldIngresoKardexOfKardex = kardexNew.getIngresoKardex();
                if (oldIngresoKardexOfKardex != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Kardex " + kardexNew + " already has an item of type IngresoKardex whose kardex column cannot be null. Please make another selection for the kardex field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (gasCodNew != null) {
                gasCodNew = em.getReference(gasCodNew.getClass(), gasCodNew.getGasCod());
                ingresoKardex.setGasCod(gasCodNew);
            }
            if (kardexNew != null) {
                kardexNew = em.getReference(kardexNew.getClass(), kardexNew.getKarCod());
                ingresoKardex.setKardex(kardexNew);
            }
            ingresoKardex = em.merge(ingresoKardex);
            if (gasCodOld != null && !gasCodOld.equals(gasCodNew)) {
                gasCodOld.getIngresoKardexCollection().remove(ingresoKardex);
                gasCodOld = em.merge(gasCodOld);
            }
            if (gasCodNew != null && !gasCodNew.equals(gasCodOld)) {
                gasCodNew.getIngresoKardexCollection().add(ingresoKardex);
                gasCodNew = em.merge(gasCodNew);
            }
            if (kardexOld != null && !kardexOld.equals(kardexNew)) {
                kardexOld.setIngresoKardex(null);
                kardexOld = em.merge(kardexOld);
            }
            if (kardexNew != null && !kardexNew.equals(kardexOld)) {
                kardexNew.setIngresoKardex(ingresoKardex);
                kardexNew = em.merge(kardexNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ingresoKardex.getKarCod();
                if (findIngresoKardex(id) == null) {
                    throw new NonexistentEntityException("The ingresoKardex with id " + id + " no longer exists.");
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
            IngresoKardex ingresoKardex;
            try {
                ingresoKardex = em.getReference(IngresoKardex.class, id);
                ingresoKardex.getKarCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ingresoKardex with id " + id + " no longer exists.", enfe);
            }
            Gastos gasCod = ingresoKardex.getGasCod();
            if (gasCod != null) {
                gasCod.getIngresoKardexCollection().remove(ingresoKardex);
                gasCod = em.merge(gasCod);
            }
            Kardex kardex = ingresoKardex.getKardex();
            if (kardex != null) {
                kardex.setIngresoKardex(null);
                kardex = em.merge(kardex);
            }
            em.remove(ingresoKardex);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IngresoKardex> findIngresoKardexEntities() {
        return findIngresoKardexEntities(true, -1, -1);
    }

    public List<IngresoKardex> findIngresoKardexEntities(int maxResults, int firstResult) {
        return findIngresoKardexEntities(false, maxResults, firstResult);
    }

    private List<IngresoKardex> findIngresoKardexEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(IngresoKardex.class));
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

    public IngresoKardex findIngresoKardex(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IngresoKardex.class, id);
        } finally {
            em.close();
        }
    }

    public int getIngresoKardexCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IngresoKardex> rt = cq.from(IngresoKardex.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
