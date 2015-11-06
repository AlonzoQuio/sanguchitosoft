/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.persistencia.jpa;

import com.epissoft.sanguchito.persistencia.Almacen;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.epissoft.sanguchito.persistencia.ProductoAlmacen;
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
public class AlmacenJpaController implements Serializable {

    public AlmacenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Almacen almacen) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        ProductoAlmacen productoAlmacenOrphanCheck = almacen.getProductoAlmacen();
        if (productoAlmacenOrphanCheck != null) {
            Almacen oldAlmacenOfProductoAlmacen = productoAlmacenOrphanCheck.getAlmacen();
            if (oldAlmacenOfProductoAlmacen != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The ProductoAlmacen " + productoAlmacenOrphanCheck + " already has an item of type Almacen whose productoAlmacen column cannot be null. Please make another selection for the productoAlmacen field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductoAlmacen productoAlmacen = almacen.getProductoAlmacen();
            if (productoAlmacen != null) {
                productoAlmacen = em.getReference(productoAlmacen.getClass(), productoAlmacen.getProdAlmCod());
                almacen.setProductoAlmacen(productoAlmacen);
            }
            em.persist(almacen);
            if (productoAlmacen != null) {
                productoAlmacen.setAlmacen(almacen);
                productoAlmacen = em.merge(productoAlmacen);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAlmacen(almacen.getIngCod()) != null) {
                throw new PreexistingEntityException("Almacen " + almacen + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Almacen almacen) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Almacen persistentAlmacen = em.find(Almacen.class, almacen.getIngCod());
            ProductoAlmacen productoAlmacenOld = persistentAlmacen.getProductoAlmacen();
            ProductoAlmacen productoAlmacenNew = almacen.getProductoAlmacen();
            List<String> illegalOrphanMessages = null;
            if (productoAlmacenNew != null && !productoAlmacenNew.equals(productoAlmacenOld)) {
                Almacen oldAlmacenOfProductoAlmacen = productoAlmacenNew.getAlmacen();
                if (oldAlmacenOfProductoAlmacen != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The ProductoAlmacen " + productoAlmacenNew + " already has an item of type Almacen whose productoAlmacen column cannot be null. Please make another selection for the productoAlmacen field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (productoAlmacenNew != null) {
                productoAlmacenNew = em.getReference(productoAlmacenNew.getClass(), productoAlmacenNew.getProdAlmCod());
                almacen.setProductoAlmacen(productoAlmacenNew);
            }
            almacen = em.merge(almacen);
            if (productoAlmacenOld != null && !productoAlmacenOld.equals(productoAlmacenNew)) {
                productoAlmacenOld.setAlmacen(null);
                productoAlmacenOld = em.merge(productoAlmacenOld);
            }
            if (productoAlmacenNew != null && !productoAlmacenNew.equals(productoAlmacenOld)) {
                productoAlmacenNew.setAlmacen(almacen);
                productoAlmacenNew = em.merge(productoAlmacenNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = almacen.getIngCod();
                if (findAlmacen(id) == null) {
                    throw new NonexistentEntityException("The almacen with id " + id + " no longer exists.");
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
            Almacen almacen;
            try {
                almacen = em.getReference(Almacen.class, id);
                almacen.getIngCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The almacen with id " + id + " no longer exists.", enfe);
            }
            ProductoAlmacen productoAlmacen = almacen.getProductoAlmacen();
            if (productoAlmacen != null) {
                productoAlmacen.setAlmacen(null);
                productoAlmacen = em.merge(productoAlmacen);
            }
            em.remove(almacen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Almacen> findAlmacenEntities() {
        return findAlmacenEntities(true, -1, -1);
    }

    public List<Almacen> findAlmacenEntities(int maxResults, int firstResult) {
        return findAlmacenEntities(false, maxResults, firstResult);
    }

    private List<Almacen> findAlmacenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Almacen.class));
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

    public Almacen findAlmacen(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Almacen.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlmacenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Almacen> rt = cq.from(Almacen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
