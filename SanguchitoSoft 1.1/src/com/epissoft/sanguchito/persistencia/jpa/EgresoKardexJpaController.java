/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.persistencia.jpa;

import com.epissoft.sanguchito.persistencia.EgresoKardex;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
public class EgresoKardexJpaController implements Serializable {

    public EgresoKardexJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EgresoKardex egresoKardex) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Kardex kardexOrphanCheck = egresoKardex.getKardex();
        if (kardexOrphanCheck != null) {
            EgresoKardex oldEgresoKardexOfKardex = kardexOrphanCheck.getEgresoKardex();
            if (oldEgresoKardexOfKardex != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Kardex " + kardexOrphanCheck + " already has an item of type EgresoKardex whose kardex column cannot be null. Please make another selection for the kardex field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Kardex kardex = egresoKardex.getKardex();
            if (kardex != null) {
                kardex = em.getReference(kardex.getClass(), kardex.getKarCod());
                egresoKardex.setKardex(kardex);
            }
            em.persist(egresoKardex);
            if (kardex != null) {
                kardex.setEgresoKardex(egresoKardex);
                kardex = em.merge(kardex);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEgresoKardex(egresoKardex.getKarCod()) != null) {
                throw new PreexistingEntityException("EgresoKardex " + egresoKardex + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EgresoKardex egresoKardex) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EgresoKardex persistentEgresoKardex = em.find(EgresoKardex.class, egresoKardex.getKarCod());
            Kardex kardexOld = persistentEgresoKardex.getKardex();
            Kardex kardexNew = egresoKardex.getKardex();
            List<String> illegalOrphanMessages = null;
            if (kardexNew != null && !kardexNew.equals(kardexOld)) {
                EgresoKardex oldEgresoKardexOfKardex = kardexNew.getEgresoKardex();
                if (oldEgresoKardexOfKardex != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Kardex " + kardexNew + " already has an item of type EgresoKardex whose kardex column cannot be null. Please make another selection for the kardex field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (kardexNew != null) {
                kardexNew = em.getReference(kardexNew.getClass(), kardexNew.getKarCod());
                egresoKardex.setKardex(kardexNew);
            }
            egresoKardex = em.merge(egresoKardex);
            if (kardexOld != null && !kardexOld.equals(kardexNew)) {
                kardexOld.setEgresoKardex(null);
                kardexOld = em.merge(kardexOld);
            }
            if (kardexNew != null && !kardexNew.equals(kardexOld)) {
                kardexNew.setEgresoKardex(egresoKardex);
                kardexNew = em.merge(kardexNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = egresoKardex.getKarCod();
                if (findEgresoKardex(id) == null) {
                    throw new NonexistentEntityException("The egresoKardex with id " + id + " no longer exists.");
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
            EgresoKardex egresoKardex;
            try {
                egresoKardex = em.getReference(EgresoKardex.class, id);
                egresoKardex.getKarCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The egresoKardex with id " + id + " no longer exists.", enfe);
            }
            Kardex kardex = egresoKardex.getKardex();
            if (kardex != null) {
                kardex.setEgresoKardex(null);
                kardex = em.merge(kardex);
            }
            em.remove(egresoKardex);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EgresoKardex> findEgresoKardexEntities() {
        return findEgresoKardexEntities(true, -1, -1);
    }

    public List<EgresoKardex> findEgresoKardexEntities(int maxResults, int firstResult) {
        return findEgresoKardexEntities(false, maxResults, firstResult);
    }

    private List<EgresoKardex> findEgresoKardexEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EgresoKardex.class));
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

    public EgresoKardex findEgresoKardex(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EgresoKardex.class, id);
        } finally {
            em.close();
        }
    }

    public int getEgresoKardexCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EgresoKardex> rt = cq.from(EgresoKardex.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
