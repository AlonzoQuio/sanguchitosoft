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
import com.epissoft.sanguchito.persistencia.AperturaCaja;
import com.epissoft.sanguchito.persistencia.CierreCaja;
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
public class CierreCajaJpaController implements Serializable {

    public CierreCajaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CierreCaja cierreCaja) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        AperturaCaja aperturaCajaOrphanCheck = cierreCaja.getAperturaCaja();
        if (aperturaCajaOrphanCheck != null) {
            CierreCaja oldCierreCajaOfAperturaCaja = aperturaCajaOrphanCheck.getCierreCaja();
            if (oldCierreCajaOfAperturaCaja != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The AperturaCaja " + aperturaCajaOrphanCheck + " already has an item of type CierreCaja whose aperturaCaja column cannot be null. Please make another selection for the aperturaCaja field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AperturaCaja aperturaCaja = cierreCaja.getAperturaCaja();
            if (aperturaCaja != null) {
                aperturaCaja = em.getReference(aperturaCaja.getClass(), aperturaCaja.getAperCajCod());
                cierreCaja.setAperturaCaja(aperturaCaja);
            }
            em.persist(cierreCaja);
            if (aperturaCaja != null) {
                aperturaCaja.setCierreCaja(cierreCaja);
                aperturaCaja = em.merge(aperturaCaja);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCierreCaja(cierreCaja.getAperCajCod()) != null) {
                throw new PreexistingEntityException("CierreCaja " + cierreCaja + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CierreCaja cierreCaja) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CierreCaja persistentCierreCaja = em.find(CierreCaja.class, cierreCaja.getAperCajCod());
            AperturaCaja aperturaCajaOld = persistentCierreCaja.getAperturaCaja();
            AperturaCaja aperturaCajaNew = cierreCaja.getAperturaCaja();
            List<String> illegalOrphanMessages = null;
            if (aperturaCajaNew != null && !aperturaCajaNew.equals(aperturaCajaOld)) {
                CierreCaja oldCierreCajaOfAperturaCaja = aperturaCajaNew.getCierreCaja();
                if (oldCierreCajaOfAperturaCaja != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The AperturaCaja " + aperturaCajaNew + " already has an item of type CierreCaja whose aperturaCaja column cannot be null. Please make another selection for the aperturaCaja field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (aperturaCajaNew != null) {
                aperturaCajaNew = em.getReference(aperturaCajaNew.getClass(), aperturaCajaNew.getAperCajCod());
                cierreCaja.setAperturaCaja(aperturaCajaNew);
            }
            cierreCaja = em.merge(cierreCaja);
            if (aperturaCajaOld != null && !aperturaCajaOld.equals(aperturaCajaNew)) {
                aperturaCajaOld.setCierreCaja(null);
                aperturaCajaOld = em.merge(aperturaCajaOld);
            }
            if (aperturaCajaNew != null && !aperturaCajaNew.equals(aperturaCajaOld)) {
                aperturaCajaNew.setCierreCaja(cierreCaja);
                aperturaCajaNew = em.merge(aperturaCajaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cierreCaja.getAperCajCod();
                if (findCierreCaja(id) == null) {
                    throw new NonexistentEntityException("The cierreCaja with id " + id + " no longer exists.");
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
            CierreCaja cierreCaja;
            try {
                cierreCaja = em.getReference(CierreCaja.class, id);
                cierreCaja.getAperCajCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cierreCaja with id " + id + " no longer exists.", enfe);
            }
            AperturaCaja aperturaCaja = cierreCaja.getAperturaCaja();
            if (aperturaCaja != null) {
                aperturaCaja.setCierreCaja(null);
                aperturaCaja = em.merge(aperturaCaja);
            }
            em.remove(cierreCaja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CierreCaja> findCierreCajaEntities() {
        return findCierreCajaEntities(true, -1, -1);
    }

    public List<CierreCaja> findCierreCajaEntities(int maxResults, int firstResult) {
        return findCierreCajaEntities(false, maxResults, firstResult);
    }

    private List<CierreCaja> findCierreCajaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CierreCaja.class));
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

    public CierreCaja findCierreCaja(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CierreCaja.class, id);
        } finally {
            em.close();
        }
    }

    public int getCierreCajaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CierreCaja> rt = cq.from(CierreCaja.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
