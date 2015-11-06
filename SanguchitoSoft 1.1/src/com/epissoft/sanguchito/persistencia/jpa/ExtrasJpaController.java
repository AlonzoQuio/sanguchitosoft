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
import com.epissoft.sanguchito.persistencia.CategoriaExtra;
import com.epissoft.sanguchito.persistencia.Extras;
import com.epissoft.sanguchito.persistencia.FacturaciondExtra;
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
public class ExtrasJpaController implements Serializable {

    public ExtrasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Extras extras) {
        if (extras.getFacturaciondExtraCollection() == null) {
            extras.setFacturaciondExtraCollection(new ArrayList<FacturaciondExtra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriaExtra catExtCod = extras.getCatExtCod();
            if (catExtCod != null) {
                catExtCod = em.getReference(catExtCod.getClass(), catExtCod.getCatExtCod());
                extras.setCatExtCod(catExtCod);
            }
            Collection<FacturaciondExtra> attachedFacturaciondExtraCollection = new ArrayList<FacturaciondExtra>();
            for (FacturaciondExtra facturaciondExtraCollectionFacturaciondExtraToAttach : extras.getFacturaciondExtraCollection()) {
                facturaciondExtraCollectionFacturaciondExtraToAttach = em.getReference(facturaciondExtraCollectionFacturaciondExtraToAttach.getClass(), facturaciondExtraCollectionFacturaciondExtraToAttach.getFacExtDetCod());
                attachedFacturaciondExtraCollection.add(facturaciondExtraCollectionFacturaciondExtraToAttach);
            }
            extras.setFacturaciondExtraCollection(attachedFacturaciondExtraCollection);
            em.persist(extras);
            if (catExtCod != null) {
                catExtCod.getExtrasCollection().add(extras);
                catExtCod = em.merge(catExtCod);
            }
            for (FacturaciondExtra facturaciondExtraCollectionFacturaciondExtra : extras.getFacturaciondExtraCollection()) {
                Extras oldExtCodOfFacturaciondExtraCollectionFacturaciondExtra = facturaciondExtraCollectionFacturaciondExtra.getExtCod();
                facturaciondExtraCollectionFacturaciondExtra.setExtCod(extras);
                facturaciondExtraCollectionFacturaciondExtra = em.merge(facturaciondExtraCollectionFacturaciondExtra);
                if (oldExtCodOfFacturaciondExtraCollectionFacturaciondExtra != null) {
                    oldExtCodOfFacturaciondExtraCollectionFacturaciondExtra.getFacturaciondExtraCollection().remove(facturaciondExtraCollectionFacturaciondExtra);
                    oldExtCodOfFacturaciondExtraCollectionFacturaciondExtra = em.merge(oldExtCodOfFacturaciondExtraCollectionFacturaciondExtra);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Extras extras) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Extras persistentExtras = em.find(Extras.class, extras.getExtCod());
            CategoriaExtra catExtCodOld = persistentExtras.getCatExtCod();
            CategoriaExtra catExtCodNew = extras.getCatExtCod();
            Collection<FacturaciondExtra> facturaciondExtraCollectionOld = persistentExtras.getFacturaciondExtraCollection();
            Collection<FacturaciondExtra> facturaciondExtraCollectionNew = extras.getFacturaciondExtraCollection();
            if (catExtCodNew != null) {
                catExtCodNew = em.getReference(catExtCodNew.getClass(), catExtCodNew.getCatExtCod());
                extras.setCatExtCod(catExtCodNew);
            }
            Collection<FacturaciondExtra> attachedFacturaciondExtraCollectionNew = new ArrayList<FacturaciondExtra>();
            for (FacturaciondExtra facturaciondExtraCollectionNewFacturaciondExtraToAttach : facturaciondExtraCollectionNew) {
                facturaciondExtraCollectionNewFacturaciondExtraToAttach = em.getReference(facturaciondExtraCollectionNewFacturaciondExtraToAttach.getClass(), facturaciondExtraCollectionNewFacturaciondExtraToAttach.getFacExtDetCod());
                attachedFacturaciondExtraCollectionNew.add(facturaciondExtraCollectionNewFacturaciondExtraToAttach);
            }
            facturaciondExtraCollectionNew = attachedFacturaciondExtraCollectionNew;
            extras.setFacturaciondExtraCollection(facturaciondExtraCollectionNew);
            extras = em.merge(extras);
            if (catExtCodOld != null && !catExtCodOld.equals(catExtCodNew)) {
                catExtCodOld.getExtrasCollection().remove(extras);
                catExtCodOld = em.merge(catExtCodOld);
            }
            if (catExtCodNew != null && !catExtCodNew.equals(catExtCodOld)) {
                catExtCodNew.getExtrasCollection().add(extras);
                catExtCodNew = em.merge(catExtCodNew);
            }
            for (FacturaciondExtra facturaciondExtraCollectionOldFacturaciondExtra : facturaciondExtraCollectionOld) {
                if (!facturaciondExtraCollectionNew.contains(facturaciondExtraCollectionOldFacturaciondExtra)) {
                    facturaciondExtraCollectionOldFacturaciondExtra.setExtCod(null);
                    facturaciondExtraCollectionOldFacturaciondExtra = em.merge(facturaciondExtraCollectionOldFacturaciondExtra);
                }
            }
            for (FacturaciondExtra facturaciondExtraCollectionNewFacturaciondExtra : facturaciondExtraCollectionNew) {
                if (!facturaciondExtraCollectionOld.contains(facturaciondExtraCollectionNewFacturaciondExtra)) {
                    Extras oldExtCodOfFacturaciondExtraCollectionNewFacturaciondExtra = facturaciondExtraCollectionNewFacturaciondExtra.getExtCod();
                    facturaciondExtraCollectionNewFacturaciondExtra.setExtCod(extras);
                    facturaciondExtraCollectionNewFacturaciondExtra = em.merge(facturaciondExtraCollectionNewFacturaciondExtra);
                    if (oldExtCodOfFacturaciondExtraCollectionNewFacturaciondExtra != null && !oldExtCodOfFacturaciondExtraCollectionNewFacturaciondExtra.equals(extras)) {
                        oldExtCodOfFacturaciondExtraCollectionNewFacturaciondExtra.getFacturaciondExtraCollection().remove(facturaciondExtraCollectionNewFacturaciondExtra);
                        oldExtCodOfFacturaciondExtraCollectionNewFacturaciondExtra = em.merge(oldExtCodOfFacturaciondExtraCollectionNewFacturaciondExtra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = extras.getExtCod();
                if (findExtras(id) == null) {
                    throw new NonexistentEntityException("The extras with id " + id + " no longer exists.");
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
            Extras extras;
            try {
                extras = em.getReference(Extras.class, id);
                extras.getExtCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The extras with id " + id + " no longer exists.", enfe);
            }
            CategoriaExtra catExtCod = extras.getCatExtCod();
            if (catExtCod != null) {
                catExtCod.getExtrasCollection().remove(extras);
                catExtCod = em.merge(catExtCod);
            }
            Collection<FacturaciondExtra> facturaciondExtraCollection = extras.getFacturaciondExtraCollection();
            for (FacturaciondExtra facturaciondExtraCollectionFacturaciondExtra : facturaciondExtraCollection) {
                facturaciondExtraCollectionFacturaciondExtra.setExtCod(null);
                facturaciondExtraCollectionFacturaciondExtra = em.merge(facturaciondExtraCollectionFacturaciondExtra);
            }
            em.remove(extras);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Extras> findExtrasEntities() {
        return findExtrasEntities(true, -1, -1);
    }

    public List<Extras> findExtrasEntities(int maxResults, int firstResult) {
        return findExtrasEntities(false, maxResults, firstResult);
    }

    private List<Extras> findExtrasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Extras.class));
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

    public Extras findExtras(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Extras.class, id);
        } finally {
            em.close();
        }
    }
    public List<Extras> findExtrasByCategoria(int catExt,boolean habilitado){
        Query consulta = getEntityManager().createNamedQuery("Extras.findByCatExtCod");
        consulta.setParameter("catExtCod", new CategoriaExtra(catExt));
        consulta.setParameter("extEst",habilitado);
        List<Extras> lExtras= consulta.getResultList();
        return lExtras;
    }
    public List<Extras> findExtras(boolean habilitado){
        List<Extras> lista=getEntityManager().createNamedQuery("Extras.findByExtEst").setParameter("extEst",habilitado).getResultList();
        return lista;
    }
    public int getExtrasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Extras> rt = cq.from(Extras.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
