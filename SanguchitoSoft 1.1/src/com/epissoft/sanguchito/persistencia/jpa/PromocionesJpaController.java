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
import com.epissoft.sanguchito.persistencia.Producto;
import com.epissoft.sanguchito.persistencia.Promociones;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.NonexistentEntityException;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Hisae Shizumaru
 */
public class PromocionesJpaController implements Serializable {

    public PromocionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Promociones promociones) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto proCod = promociones.getProCod();
            if (proCod != null) {
                proCod = em.getReference(proCod.getClass(), proCod.getProCod());
                promociones.setProCod(proCod);
            }
            em.persist(promociones);
            if (proCod != null) {
                proCod.getPromocionesCollection().add(promociones);
                proCod = em.merge(proCod);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPromociones(promociones.getPromCod()) != null) {
                throw new PreexistingEntityException("Promociones " + promociones + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Promociones promociones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Promociones persistentPromociones = em.find(Promociones.class, promociones.getPromCod());
            Producto proCodOld = persistentPromociones.getProCod();
            Producto proCodNew = promociones.getProCod();
            if (proCodNew != null) {
                proCodNew = em.getReference(proCodNew.getClass(), proCodNew.getProCod());
                promociones.setProCod(proCodNew);
            }
            promociones = em.merge(promociones);
            if (proCodOld != null && !proCodOld.equals(proCodNew)) {
                proCodOld.getPromocionesCollection().remove(promociones);
                proCodOld = em.merge(proCodOld);
            }
            if (proCodNew != null && !proCodNew.equals(proCodOld)) {
                proCodNew.getPromocionesCollection().add(promociones);
                proCodNew = em.merge(proCodNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = promociones.getPromCod();
                if (findPromociones(id) == null) {
                    throw new NonexistentEntityException("The promociones with id " + id + " no longer exists.");
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
            Promociones promociones;
            try {
                promociones = em.getReference(Promociones.class, id);
                promociones.getPromCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The promociones with id " + id + " no longer exists.", enfe);
            }
            Producto proCod = promociones.getProCod();
            if (proCod != null) {
                proCod.getPromocionesCollection().remove(promociones);
                proCod = em.merge(proCod);
            }
            em.remove(promociones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Promociones> findPromocionesEntities() {
        return findPromocionesEntities(true, -1, -1);
    }

    public List<Promociones> findPromocionesEntities(int maxResults, int firstResult) {
        return findPromocionesEntities(false, maxResults, firstResult);
    }

    private List<Promociones> findPromocionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Promociones.class));
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

    public Promociones findPromociones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Promociones.class, id);
        } finally {
            em.close();
        }
    }

    public int getPromocionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Promociones> rt = cq.from(Promociones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
