/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.persistencia.jpa;

import com.epissoft.sanguchito.persistencia.AperturaCaja;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.epissoft.sanguchito.persistencia.Usuario;
import com.epissoft.sanguchito.persistencia.CierreCaja;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.IllegalOrphanException;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Hisae Shizumaru
 */
public class AperturaCajaJpaController implements Serializable {

    public AperturaCajaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AperturaCaja aperturaCaja) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuCod = aperturaCaja.getUsuCod();
            if (usuCod != null) {
                usuCod = em.getReference(usuCod.getClass(), usuCod.getUsuCod());
                aperturaCaja.setUsuCod(usuCod);
            }
            CierreCaja cierreCaja = aperturaCaja.getCierreCaja();
            if (cierreCaja != null) {
                cierreCaja = em.getReference(cierreCaja.getClass(), cierreCaja.getAperCajCod());
                aperturaCaja.setCierreCaja(cierreCaja);
            }
            em.persist(aperturaCaja);
            if (usuCod != null) {
                usuCod.getAperturaCajaCollection().add(aperturaCaja);
                usuCod = em.merge(usuCod);
            }
            if (cierreCaja != null) {
                AperturaCaja oldAperturaCajaOfCierreCaja = cierreCaja.getAperturaCaja();
                if (oldAperturaCajaOfCierreCaja != null) {
                    oldAperturaCajaOfCierreCaja.setCierreCaja(null);
                    oldAperturaCajaOfCierreCaja = em.merge(oldAperturaCajaOfCierreCaja);
                }
                cierreCaja.setAperturaCaja(aperturaCaja);
                cierreCaja = em.merge(cierreCaja);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AperturaCaja aperturaCaja) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AperturaCaja persistentAperturaCaja = em.find(AperturaCaja.class, aperturaCaja.getAperCajCod());
            Usuario usuCodOld = persistentAperturaCaja.getUsuCod();
            Usuario usuCodNew = aperturaCaja.getUsuCod();
            CierreCaja cierreCajaOld = persistentAperturaCaja.getCierreCaja();
            CierreCaja cierreCajaNew = aperturaCaja.getCierreCaja();
            List<String> illegalOrphanMessages = null;
            if (cierreCajaOld != null && !cierreCajaOld.equals(cierreCajaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain CierreCaja " + cierreCajaOld + " since its aperturaCaja field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuCodNew != null) {
                usuCodNew = em.getReference(usuCodNew.getClass(), usuCodNew.getUsuCod());
                aperturaCaja.setUsuCod(usuCodNew);
            }
            if (cierreCajaNew != null) {
                cierreCajaNew = em.getReference(cierreCajaNew.getClass(), cierreCajaNew.getAperCajCod());
                aperturaCaja.setCierreCaja(cierreCajaNew);
            }
            aperturaCaja = em.merge(aperturaCaja);
            if (usuCodOld != null && !usuCodOld.equals(usuCodNew)) {
                usuCodOld.getAperturaCajaCollection().remove(aperturaCaja);
                usuCodOld = em.merge(usuCodOld);
            }
            if (usuCodNew != null && !usuCodNew.equals(usuCodOld)) {
                usuCodNew.getAperturaCajaCollection().add(aperturaCaja);
                usuCodNew = em.merge(usuCodNew);
            }
            if (cierreCajaNew != null && !cierreCajaNew.equals(cierreCajaOld)) {
                AperturaCaja oldAperturaCajaOfCierreCaja = cierreCajaNew.getAperturaCaja();
                if (oldAperturaCajaOfCierreCaja != null) {
                    oldAperturaCajaOfCierreCaja.setCierreCaja(null);
                    oldAperturaCajaOfCierreCaja = em.merge(oldAperturaCajaOfCierreCaja);
                }
                cierreCajaNew.setAperturaCaja(aperturaCaja);
                cierreCajaNew = em.merge(cierreCajaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = aperturaCaja.getAperCajCod();
                if (findAperturaCaja(id) == null) {
                    throw new NonexistentEntityException("The aperturaCaja with id " + id + " no longer exists.");
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
            AperturaCaja aperturaCaja;
            try {
                aperturaCaja = em.getReference(AperturaCaja.class, id);
                aperturaCaja.getAperCajCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aperturaCaja with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            CierreCaja cierreCajaOrphanCheck = aperturaCaja.getCierreCaja();
            if (cierreCajaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AperturaCaja (" + aperturaCaja + ") cannot be destroyed since the CierreCaja " + cierreCajaOrphanCheck + " in its cierreCaja field has a non-nullable aperturaCaja field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario usuCod = aperturaCaja.getUsuCod();
            if (usuCod != null) {
                usuCod.getAperturaCajaCollection().remove(aperturaCaja);
                usuCod = em.merge(usuCod);
            }
            em.remove(aperturaCaja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AperturaCaja> findAperturaCajaEntities() {
        return findAperturaCajaEntities(true, -1, -1);
    }

    public List<AperturaCaja> findAperturaCajaEntities(int maxResults, int firstResult) {
        return findAperturaCajaEntities(false, maxResults, firstResult);
    }

    private List<AperturaCaja> findAperturaCajaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AperturaCaja.class));
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

    public AperturaCaja findAperturaCaja(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AperturaCaja.class, id);
        } finally {
            em.close();
        }
    }

    public int getAperturaCajaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AperturaCaja> rt = cq.from(AperturaCaja.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
