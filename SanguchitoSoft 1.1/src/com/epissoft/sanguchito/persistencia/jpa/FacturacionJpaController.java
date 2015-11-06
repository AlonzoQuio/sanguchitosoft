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
import com.epissoft.sanguchito.persistencia.TipoFacturacion;
import com.epissoft.sanguchito.persistencia.Cliente;
import com.epissoft.sanguchito.persistencia.Facturacion;
import com.epissoft.sanguchito.persistencia.Usuario;
import com.epissoft.sanguchito.persistencia.FacturacionDetalle;
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
public class FacturacionJpaController implements Serializable {

    public FacturacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Facturacion facturacion) {
        if (facturacion.getFacturacionDetalleCollection() == null) {
            facturacion.setFacturacionDetalleCollection(new ArrayList<FacturacionDetalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoFacturacion tipFacCod = facturacion.getTipFacCod();
            if (tipFacCod != null) {
                tipFacCod = em.getReference(tipFacCod.getClass(), tipFacCod.getTipFacCod());
                facturacion.setTipFacCod(tipFacCod);
            }
            Cliente clieDni = facturacion.getClieDni();
            if (clieDni != null) {
                clieDni = em.getReference(clieDni.getClass(), clieDni.getClieDni());
                facturacion.setClieDni(clieDni);
            }
            Usuario usuCod = facturacion.getUsuCod();
            if (usuCod != null) {
                usuCod = em.getReference(usuCod.getClass(), usuCod.getUsuCod());
                facturacion.setUsuCod(usuCod);
            }
            Collection<FacturacionDetalle> attachedFacturacionDetalleCollection = new ArrayList<FacturacionDetalle>();
            for (FacturacionDetalle facturacionDetalleCollectionFacturacionDetalleToAttach : facturacion.getFacturacionDetalleCollection()) {
                facturacionDetalleCollectionFacturacionDetalleToAttach = em.getReference(facturacionDetalleCollectionFacturacionDetalleToAttach.getClass(), facturacionDetalleCollectionFacturacionDetalleToAttach.getFacDetSec());
                attachedFacturacionDetalleCollection.add(facturacionDetalleCollectionFacturacionDetalleToAttach);
            }
            facturacion.setFacturacionDetalleCollection(attachedFacturacionDetalleCollection);
            em.persist(facturacion);
            if (tipFacCod != null) {
                tipFacCod.getFacturacionCollection().add(facturacion);
                tipFacCod = em.merge(tipFacCod);
            }
            if (clieDni != null) {
                clieDni.getFacturacionCollection().add(facturacion);
                clieDni = em.merge(clieDni);
            }
            if (usuCod != null) {
                usuCod.getFacturacionCollection().add(facturacion);
                usuCod = em.merge(usuCod);
            }
            for (FacturacionDetalle facturacionDetalleCollectionFacturacionDetalle : facturacion.getFacturacionDetalleCollection()) {
                Facturacion oldFacCodOfFacturacionDetalleCollectionFacturacionDetalle = facturacionDetalleCollectionFacturacionDetalle.getFacCod();
                facturacionDetalleCollectionFacturacionDetalle.setFacCod(facturacion);
                facturacionDetalleCollectionFacturacionDetalle = em.merge(facturacionDetalleCollectionFacturacionDetalle);
                if (oldFacCodOfFacturacionDetalleCollectionFacturacionDetalle != null) {
                    oldFacCodOfFacturacionDetalleCollectionFacturacionDetalle.getFacturacionDetalleCollection().remove(facturacionDetalleCollectionFacturacionDetalle);
                    oldFacCodOfFacturacionDetalleCollectionFacturacionDetalle = em.merge(oldFacCodOfFacturacionDetalleCollectionFacturacionDetalle);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Facturacion facturacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facturacion persistentFacturacion = em.find(Facturacion.class, facturacion.getFacCod());
            TipoFacturacion tipFacCodOld = persistentFacturacion.getTipFacCod();
            TipoFacturacion tipFacCodNew = facturacion.getTipFacCod();
            Cliente clieDniOld = persistentFacturacion.getClieDni();
            Cliente clieDniNew = facturacion.getClieDni();
            Usuario usuCodOld = persistentFacturacion.getUsuCod();
            Usuario usuCodNew = facturacion.getUsuCod();
            Collection<FacturacionDetalle> facturacionDetalleCollectionOld = persistentFacturacion.getFacturacionDetalleCollection();
            Collection<FacturacionDetalle> facturacionDetalleCollectionNew = facturacion.getFacturacionDetalleCollection();
            if (tipFacCodNew != null) {
                tipFacCodNew = em.getReference(tipFacCodNew.getClass(), tipFacCodNew.getTipFacCod());
                facturacion.setTipFacCod(tipFacCodNew);
            }
            if (clieDniNew != null) {
                clieDniNew = em.getReference(clieDniNew.getClass(), clieDniNew.getClieDni());
                facturacion.setClieDni(clieDniNew);
            }
            if (usuCodNew != null) {
                usuCodNew = em.getReference(usuCodNew.getClass(), usuCodNew.getUsuCod());
                facturacion.setUsuCod(usuCodNew);
            }
            Collection<FacturacionDetalle> attachedFacturacionDetalleCollectionNew = new ArrayList<FacturacionDetalle>();
            for (FacturacionDetalle facturacionDetalleCollectionNewFacturacionDetalleToAttach : facturacionDetalleCollectionNew) {
                facturacionDetalleCollectionNewFacturacionDetalleToAttach = em.getReference(facturacionDetalleCollectionNewFacturacionDetalleToAttach.getClass(), facturacionDetalleCollectionNewFacturacionDetalleToAttach.getFacDetSec());
                attachedFacturacionDetalleCollectionNew.add(facturacionDetalleCollectionNewFacturacionDetalleToAttach);
            }
            facturacionDetalleCollectionNew = attachedFacturacionDetalleCollectionNew;
            facturacion.setFacturacionDetalleCollection(facturacionDetalleCollectionNew);
            facturacion = em.merge(facturacion);
            if (tipFacCodOld != null && !tipFacCodOld.equals(tipFacCodNew)) {
                tipFacCodOld.getFacturacionCollection().remove(facturacion);
                tipFacCodOld = em.merge(tipFacCodOld);
            }
            if (tipFacCodNew != null && !tipFacCodNew.equals(tipFacCodOld)) {
                tipFacCodNew.getFacturacionCollection().add(facturacion);
                tipFacCodNew = em.merge(tipFacCodNew);
            }
            if (clieDniOld != null && !clieDniOld.equals(clieDniNew)) {
                clieDniOld.getFacturacionCollection().remove(facturacion);
                clieDniOld = em.merge(clieDniOld);
            }
            if (clieDniNew != null && !clieDniNew.equals(clieDniOld)) {
                clieDniNew.getFacturacionCollection().add(facturacion);
                clieDniNew = em.merge(clieDniNew);
            }
            if (usuCodOld != null && !usuCodOld.equals(usuCodNew)) {
                usuCodOld.getFacturacionCollection().remove(facturacion);
                usuCodOld = em.merge(usuCodOld);
            }
            if (usuCodNew != null && !usuCodNew.equals(usuCodOld)) {
                usuCodNew.getFacturacionCollection().add(facturacion);
                usuCodNew = em.merge(usuCodNew);
            }
            for (FacturacionDetalle facturacionDetalleCollectionOldFacturacionDetalle : facturacionDetalleCollectionOld) {
                if (!facturacionDetalleCollectionNew.contains(facturacionDetalleCollectionOldFacturacionDetalle)) {
                    facturacionDetalleCollectionOldFacturacionDetalle.setFacCod(null);
                    facturacionDetalleCollectionOldFacturacionDetalle = em.merge(facturacionDetalleCollectionOldFacturacionDetalle);
                }
            }
            for (FacturacionDetalle facturacionDetalleCollectionNewFacturacionDetalle : facturacionDetalleCollectionNew) {
                if (!facturacionDetalleCollectionOld.contains(facturacionDetalleCollectionNewFacturacionDetalle)) {
                    Facturacion oldFacCodOfFacturacionDetalleCollectionNewFacturacionDetalle = facturacionDetalleCollectionNewFacturacionDetalle.getFacCod();
                    facturacionDetalleCollectionNewFacturacionDetalle.setFacCod(facturacion);
                    facturacionDetalleCollectionNewFacturacionDetalle = em.merge(facturacionDetalleCollectionNewFacturacionDetalle);
                    if (oldFacCodOfFacturacionDetalleCollectionNewFacturacionDetalle != null && !oldFacCodOfFacturacionDetalleCollectionNewFacturacionDetalle.equals(facturacion)) {
                        oldFacCodOfFacturacionDetalleCollectionNewFacturacionDetalle.getFacturacionDetalleCollection().remove(facturacionDetalleCollectionNewFacturacionDetalle);
                        oldFacCodOfFacturacionDetalleCollectionNewFacturacionDetalle = em.merge(oldFacCodOfFacturacionDetalleCollectionNewFacturacionDetalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = facturacion.getFacCod();
                if (findFacturacion(id) == null) {
                    throw new NonexistentEntityException("The facturacion with id " + id + " no longer exists.");
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
            Facturacion facturacion;
            try {
                facturacion = em.getReference(Facturacion.class, id);
                facturacion.getFacCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facturacion with id " + id + " no longer exists.", enfe);
            }
            TipoFacturacion tipFacCod = facturacion.getTipFacCod();
            if (tipFacCod != null) {
                tipFacCod.getFacturacionCollection().remove(facturacion);
                tipFacCod = em.merge(tipFacCod);
            }
            Cliente clieDni = facturacion.getClieDni();
            if (clieDni != null) {
                clieDni.getFacturacionCollection().remove(facturacion);
                clieDni = em.merge(clieDni);
            }
            Usuario usuCod = facturacion.getUsuCod();
            if (usuCod != null) {
                usuCod.getFacturacionCollection().remove(facturacion);
                usuCod = em.merge(usuCod);
            }
            Collection<FacturacionDetalle> facturacionDetalleCollection = facturacion.getFacturacionDetalleCollection();
            for (FacturacionDetalle facturacionDetalleCollectionFacturacionDetalle : facturacionDetalleCollection) {
                facturacionDetalleCollectionFacturacionDetalle.setFacCod(null);
                facturacionDetalleCollectionFacturacionDetalle = em.merge(facturacionDetalleCollectionFacturacionDetalle);
            }
            em.remove(facturacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Facturacion> findFacturacionEntities() {
        return findFacturacionEntities(true, -1, -1);
    }

    public List<Facturacion> findFacturacionEntities(int maxResults, int firstResult) {
        return findFacturacionEntities(false, maxResults, firstResult);
    }

    private List<Facturacion> findFacturacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Facturacion.class));
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

    public Facturacion findFacturacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Facturacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getFacturacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Facturacion> rt = cq.from(Facturacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
