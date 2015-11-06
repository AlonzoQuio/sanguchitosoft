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
import com.epissoft.sanguchito.persistencia.Extras;
import com.epissoft.sanguchito.persistencia.FacturacionDetalle;
import com.epissoft.sanguchito.persistencia.FacturaciondExtra;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Hisae Shizumaru
 */
public class FacturaciondExtraJpaController implements Serializable {

    public FacturaciondExtraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FacturaciondExtra facturaciondExtra) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Extras extCod = facturaciondExtra.getExtCod();
            if (extCod != null) {
                extCod = em.getReference(extCod.getClass(), extCod.getExtCod());
                facturaciondExtra.setExtCod(extCod);
            }
            FacturacionDetalle facDetSec = facturaciondExtra.getFacDetSec();
            if (facDetSec != null) {
                facDetSec = em.getReference(facDetSec.getClass(), facDetSec.getFacDetSec());
                facturaciondExtra.setFacDetSec(facDetSec);
            }
            em.persist(facturaciondExtra);
            if (extCod != null) {
                extCod.getFacturaciondExtraCollection().add(facturaciondExtra);
                extCod = em.merge(extCod);
            }
            if (facDetSec != null) {
                facDetSec.getFacturaciondExtraCollection().add(facturaciondExtra);
                facDetSec = em.merge(facDetSec);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FacturaciondExtra facturaciondExtra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FacturaciondExtra persistentFacturaciondExtra = em.find(FacturaciondExtra.class, facturaciondExtra.getFacExtDetCod());
            Extras extCodOld = persistentFacturaciondExtra.getExtCod();
            Extras extCodNew = facturaciondExtra.getExtCod();
            FacturacionDetalle facDetSecOld = persistentFacturaciondExtra.getFacDetSec();
            FacturacionDetalle facDetSecNew = facturaciondExtra.getFacDetSec();
            if (extCodNew != null) {
                extCodNew = em.getReference(extCodNew.getClass(), extCodNew.getExtCod());
                facturaciondExtra.setExtCod(extCodNew);
            }
            if (facDetSecNew != null) {
                facDetSecNew = em.getReference(facDetSecNew.getClass(), facDetSecNew.getFacDetSec());
                facturaciondExtra.setFacDetSec(facDetSecNew);
            }
            facturaciondExtra = em.merge(facturaciondExtra);
            if (extCodOld != null && !extCodOld.equals(extCodNew)) {
                extCodOld.getFacturaciondExtraCollection().remove(facturaciondExtra);
                extCodOld = em.merge(extCodOld);
            }
            if (extCodNew != null && !extCodNew.equals(extCodOld)) {
                extCodNew.getFacturaciondExtraCollection().add(facturaciondExtra);
                extCodNew = em.merge(extCodNew);
            }
            if (facDetSecOld != null && !facDetSecOld.equals(facDetSecNew)) {
                facDetSecOld.getFacturaciondExtraCollection().remove(facturaciondExtra);
                facDetSecOld = em.merge(facDetSecOld);
            }
            if (facDetSecNew != null && !facDetSecNew.equals(facDetSecOld)) {
                facDetSecNew.getFacturaciondExtraCollection().add(facturaciondExtra);
                facDetSecNew = em.merge(facDetSecNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = facturaciondExtra.getFacExtDetCod();
                if (findFacturaciondExtra(id) == null) {
                    throw new NonexistentEntityException("The facturaciondExtra with id " + id + " no longer exists.");
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
            FacturaciondExtra facturaciondExtra;
            try {
                facturaciondExtra = em.getReference(FacturaciondExtra.class, id);
                facturaciondExtra.getFacExtDetCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facturaciondExtra with id " + id + " no longer exists.", enfe);
            }
            Extras extCod = facturaciondExtra.getExtCod();
            if (extCod != null) {
                extCod.getFacturaciondExtraCollection().remove(facturaciondExtra);
                extCod = em.merge(extCod);
            }
            FacturacionDetalle facDetSec = facturaciondExtra.getFacDetSec();
            if (facDetSec != null) {
                facDetSec.getFacturaciondExtraCollection().remove(facturaciondExtra);
                facDetSec = em.merge(facDetSec);
            }
            em.remove(facturaciondExtra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FacturaciondExtra> findFacturaciondExtraEntities() {
        return findFacturaciondExtraEntities(true, -1, -1);
    }

    public List<FacturaciondExtra> findFacturaciondExtraEntities(int maxResults, int firstResult) {
        return findFacturaciondExtraEntities(false, maxResults, firstResult);
    }

    private List<FacturaciondExtra> findFacturaciondExtraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FacturaciondExtra.class));
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

    public FacturaciondExtra findFacturaciondExtra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FacturaciondExtra.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturaciondExtraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FacturaciondExtra> rt = cq.from(FacturaciondExtra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
