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
import com.epissoft.sanguchito.persistencia.ProductoAlmacen;
import com.epissoft.sanguchito.persistencia.UnidadMedida;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.IllegalOrphanException;
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
public class UnidadMedidaJpaController implements Serializable {

    public UnidadMedidaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UnidadMedida unidadMedida) {
        if (unidadMedida.getProductoAlmacenCollection() == null) {
            unidadMedida.setProductoAlmacenCollection(new ArrayList<ProductoAlmacen>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ProductoAlmacen> attachedProductoAlmacenCollection = new ArrayList<ProductoAlmacen>();
            for (ProductoAlmacen productoAlmacenCollectionProductoAlmacenToAttach : unidadMedida.getProductoAlmacenCollection()) {
                productoAlmacenCollectionProductoAlmacenToAttach = em.getReference(productoAlmacenCollectionProductoAlmacenToAttach.getClass(), productoAlmacenCollectionProductoAlmacenToAttach.getProdAlmCod());
                attachedProductoAlmacenCollection.add(productoAlmacenCollectionProductoAlmacenToAttach);
            }
            unidadMedida.setProductoAlmacenCollection(attachedProductoAlmacenCollection);
            em.persist(unidadMedida);
            for (ProductoAlmacen productoAlmacenCollectionProductoAlmacen : unidadMedida.getProductoAlmacenCollection()) {
                UnidadMedida oldUniMedCodOfProductoAlmacenCollectionProductoAlmacen = productoAlmacenCollectionProductoAlmacen.getUniMedCod();
                productoAlmacenCollectionProductoAlmacen.setUniMedCod(unidadMedida);
                productoAlmacenCollectionProductoAlmacen = em.merge(productoAlmacenCollectionProductoAlmacen);
                if (oldUniMedCodOfProductoAlmacenCollectionProductoAlmacen != null) {
                    oldUniMedCodOfProductoAlmacenCollectionProductoAlmacen.getProductoAlmacenCollection().remove(productoAlmacenCollectionProductoAlmacen);
                    oldUniMedCodOfProductoAlmacenCollectionProductoAlmacen = em.merge(oldUniMedCodOfProductoAlmacenCollectionProductoAlmacen);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UnidadMedida unidadMedida) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            UnidadMedida persistentUnidadMedida = em.find(UnidadMedida.class, unidadMedida.getUniMedCod());
            Collection<ProductoAlmacen> productoAlmacenCollectionOld = persistentUnidadMedida.getProductoAlmacenCollection();
            Collection<ProductoAlmacen> productoAlmacenCollectionNew = unidadMedida.getProductoAlmacenCollection();
            List<String> illegalOrphanMessages = null;
            for (ProductoAlmacen productoAlmacenCollectionOldProductoAlmacen : productoAlmacenCollectionOld) {
                if (!productoAlmacenCollectionNew.contains(productoAlmacenCollectionOldProductoAlmacen)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductoAlmacen " + productoAlmacenCollectionOldProductoAlmacen + " since its uniMedCod field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<ProductoAlmacen> attachedProductoAlmacenCollectionNew = new ArrayList<ProductoAlmacen>();
            for (ProductoAlmacen productoAlmacenCollectionNewProductoAlmacenToAttach : productoAlmacenCollectionNew) {
                productoAlmacenCollectionNewProductoAlmacenToAttach = em.getReference(productoAlmacenCollectionNewProductoAlmacenToAttach.getClass(), productoAlmacenCollectionNewProductoAlmacenToAttach.getProdAlmCod());
                attachedProductoAlmacenCollectionNew.add(productoAlmacenCollectionNewProductoAlmacenToAttach);
            }
            productoAlmacenCollectionNew = attachedProductoAlmacenCollectionNew;
            unidadMedida.setProductoAlmacenCollection(productoAlmacenCollectionNew);
            unidadMedida = em.merge(unidadMedida);
            for (ProductoAlmacen productoAlmacenCollectionNewProductoAlmacen : productoAlmacenCollectionNew) {
                if (!productoAlmacenCollectionOld.contains(productoAlmacenCollectionNewProductoAlmacen)) {
                    UnidadMedida oldUniMedCodOfProductoAlmacenCollectionNewProductoAlmacen = productoAlmacenCollectionNewProductoAlmacen.getUniMedCod();
                    productoAlmacenCollectionNewProductoAlmacen.setUniMedCod(unidadMedida);
                    productoAlmacenCollectionNewProductoAlmacen = em.merge(productoAlmacenCollectionNewProductoAlmacen);
                    if (oldUniMedCodOfProductoAlmacenCollectionNewProductoAlmacen != null && !oldUniMedCodOfProductoAlmacenCollectionNewProductoAlmacen.equals(unidadMedida)) {
                        oldUniMedCodOfProductoAlmacenCollectionNewProductoAlmacen.getProductoAlmacenCollection().remove(productoAlmacenCollectionNewProductoAlmacen);
                        oldUniMedCodOfProductoAlmacenCollectionNewProductoAlmacen = em.merge(oldUniMedCodOfProductoAlmacenCollectionNewProductoAlmacen);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = unidadMedida.getUniMedCod();
                if (findUnidadMedida(id) == null) {
                    throw new NonexistentEntityException("The unidadMedida with id " + id + " no longer exists.");
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
            UnidadMedida unidadMedida;
            try {
                unidadMedida = em.getReference(UnidadMedida.class, id);
                unidadMedida.getUniMedCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The unidadMedida with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ProductoAlmacen> productoAlmacenCollectionOrphanCheck = unidadMedida.getProductoAlmacenCollection();
            for (ProductoAlmacen productoAlmacenCollectionOrphanCheckProductoAlmacen : productoAlmacenCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This UnidadMedida (" + unidadMedida + ") cannot be destroyed since the ProductoAlmacen " + productoAlmacenCollectionOrphanCheckProductoAlmacen + " in its productoAlmacenCollection field has a non-nullable uniMedCod field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(unidadMedida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UnidadMedida> findUnidadMedidaEntities() {
        return findUnidadMedidaEntities(true, -1, -1);
    }

    public List<UnidadMedida> findUnidadMedidaEntities(int maxResults, int firstResult) {
        return findUnidadMedidaEntities(false, maxResults, firstResult);
    }

    private List<UnidadMedida> findUnidadMedidaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UnidadMedida.class));
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

    public UnidadMedida findUnidadMedida(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UnidadMedida.class, id);
        } finally {
            em.close();
        }
    }

    public int getUnidadMedidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UnidadMedida> rt = cq.from(UnidadMedida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
