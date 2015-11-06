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
import com.epissoft.sanguchito.persistencia.RegistroFalta;
import com.epissoft.sanguchito.persistencia.TipoFalta;
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
public class TipoFaltaJpaController implements Serializable {

    public TipoFaltaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoFalta tipoFalta) {
        if (tipoFalta.getRegistroFaltaCollection() == null) {
            tipoFalta.setRegistroFaltaCollection(new ArrayList<RegistroFalta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<RegistroFalta> attachedRegistroFaltaCollection = new ArrayList<RegistroFalta>();
            for (RegistroFalta registroFaltaCollectionRegistroFaltaToAttach : tipoFalta.getRegistroFaltaCollection()) {
                registroFaltaCollectionRegistroFaltaToAttach = em.getReference(registroFaltaCollectionRegistroFaltaToAttach.getClass(), registroFaltaCollectionRegistroFaltaToAttach.getRegFaltaCod());
                attachedRegistroFaltaCollection.add(registroFaltaCollectionRegistroFaltaToAttach);
            }
            tipoFalta.setRegistroFaltaCollection(attachedRegistroFaltaCollection);
            em.persist(tipoFalta);
            for (RegistroFalta registroFaltaCollectionRegistroFalta : tipoFalta.getRegistroFaltaCollection()) {
                TipoFalta oldTipFalCodOfRegistroFaltaCollectionRegistroFalta = registroFaltaCollectionRegistroFalta.getTipFalCod();
                registroFaltaCollectionRegistroFalta.setTipFalCod(tipoFalta);
                registroFaltaCollectionRegistroFalta = em.merge(registroFaltaCollectionRegistroFalta);
                if (oldTipFalCodOfRegistroFaltaCollectionRegistroFalta != null) {
                    oldTipFalCodOfRegistroFaltaCollectionRegistroFalta.getRegistroFaltaCollection().remove(registroFaltaCollectionRegistroFalta);
                    oldTipFalCodOfRegistroFaltaCollectionRegistroFalta = em.merge(oldTipFalCodOfRegistroFaltaCollectionRegistroFalta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoFalta tipoFalta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoFalta persistentTipoFalta = em.find(TipoFalta.class, tipoFalta.getTipFalCod());
            Collection<RegistroFalta> registroFaltaCollectionOld = persistentTipoFalta.getRegistroFaltaCollection();
            Collection<RegistroFalta> registroFaltaCollectionNew = tipoFalta.getRegistroFaltaCollection();
            Collection<RegistroFalta> attachedRegistroFaltaCollectionNew = new ArrayList<RegistroFalta>();
            for (RegistroFalta registroFaltaCollectionNewRegistroFaltaToAttach : registroFaltaCollectionNew) {
                registroFaltaCollectionNewRegistroFaltaToAttach = em.getReference(registroFaltaCollectionNewRegistroFaltaToAttach.getClass(), registroFaltaCollectionNewRegistroFaltaToAttach.getRegFaltaCod());
                attachedRegistroFaltaCollectionNew.add(registroFaltaCollectionNewRegistroFaltaToAttach);
            }
            registroFaltaCollectionNew = attachedRegistroFaltaCollectionNew;
            tipoFalta.setRegistroFaltaCollection(registroFaltaCollectionNew);
            tipoFalta = em.merge(tipoFalta);
            for (RegistroFalta registroFaltaCollectionOldRegistroFalta : registroFaltaCollectionOld) {
                if (!registroFaltaCollectionNew.contains(registroFaltaCollectionOldRegistroFalta)) {
                    registroFaltaCollectionOldRegistroFalta.setTipFalCod(null);
                    registroFaltaCollectionOldRegistroFalta = em.merge(registroFaltaCollectionOldRegistroFalta);
                }
            }
            for (RegistroFalta registroFaltaCollectionNewRegistroFalta : registroFaltaCollectionNew) {
                if (!registroFaltaCollectionOld.contains(registroFaltaCollectionNewRegistroFalta)) {
                    TipoFalta oldTipFalCodOfRegistroFaltaCollectionNewRegistroFalta = registroFaltaCollectionNewRegistroFalta.getTipFalCod();
                    registroFaltaCollectionNewRegistroFalta.setTipFalCod(tipoFalta);
                    registroFaltaCollectionNewRegistroFalta = em.merge(registroFaltaCollectionNewRegistroFalta);
                    if (oldTipFalCodOfRegistroFaltaCollectionNewRegistroFalta != null && !oldTipFalCodOfRegistroFaltaCollectionNewRegistroFalta.equals(tipoFalta)) {
                        oldTipFalCodOfRegistroFaltaCollectionNewRegistroFalta.getRegistroFaltaCollection().remove(registroFaltaCollectionNewRegistroFalta);
                        oldTipFalCodOfRegistroFaltaCollectionNewRegistroFalta = em.merge(oldTipFalCodOfRegistroFaltaCollectionNewRegistroFalta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoFalta.getTipFalCod();
                if (findTipoFalta(id) == null) {
                    throw new NonexistentEntityException("The tipoFalta with id " + id + " no longer exists.");
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
            TipoFalta tipoFalta;
            try {
                tipoFalta = em.getReference(TipoFalta.class, id);
                tipoFalta.getTipFalCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoFalta with id " + id + " no longer exists.", enfe);
            }
            Collection<RegistroFalta> registroFaltaCollection = tipoFalta.getRegistroFaltaCollection();
            for (RegistroFalta registroFaltaCollectionRegistroFalta : registroFaltaCollection) {
                registroFaltaCollectionRegistroFalta.setTipFalCod(null);
                registroFaltaCollectionRegistroFalta = em.merge(registroFaltaCollectionRegistroFalta);
            }
            em.remove(tipoFalta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoFalta> findTipoFaltaEntities() {
        return findTipoFaltaEntities(true, -1, -1);
    }

    public List<TipoFalta> findTipoFaltaEntities(int maxResults, int firstResult) {
        return findTipoFaltaEntities(false, maxResults, firstResult);
    }

    private List<TipoFalta> findTipoFaltaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoFalta.class));
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

    public TipoFalta findTipoFalta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoFalta.class, id);
        } finally {
            em.close();
        }
    }
    public TipoFalta findTipoFalta(String detalle){
        List list = getEntityManager().createNamedQuery("TipoFalta.findByTipFalDes").setParameter("tipFalDes", detalle).getResultList();
        if(list.isEmpty()){
            return null;
        }else{
            return (TipoFalta)list.get(0);
        }
    }
    public int getTipoFaltaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoFalta> rt = cq.from(TipoFalta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
