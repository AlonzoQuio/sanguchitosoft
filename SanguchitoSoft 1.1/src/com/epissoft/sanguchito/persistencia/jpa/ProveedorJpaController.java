package com.epissoft.sanguchito.persistencia.jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.epissoft.sanguchito.persistencia.Gastos;
import com.epissoft.sanguchito.persistencia.Proveedor;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.NonexistentEntityException;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class ProveedorJpaController implements Serializable {

    public ProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedor proveedor) throws PreexistingEntityException, Exception {
        if (proveedor.getGastosCollection() == null) {
            proveedor.setGastosCollection(new ArrayList<Gastos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Gastos> attachedGastosCollection = new ArrayList<Gastos>();
            for (Gastos gastosCollectionGastosToAttach : proveedor.getGastosCollection()) {
                gastosCollectionGastosToAttach = em.getReference(gastosCollectionGastosToAttach.getClass(), gastosCollectionGastosToAttach.getGasCod());
                attachedGastosCollection.add(gastosCollectionGastosToAttach);
            }
            proveedor.setGastosCollection(attachedGastosCollection);
            em.persist(proveedor);
            for (Gastos gastosCollectionGastos : proveedor.getGastosCollection()) {
                Proveedor oldProvRucOfGastosCollectionGastos = gastosCollectionGastos.getProvRuc();
                gastosCollectionGastos.setProvRuc(proveedor);
                gastosCollectionGastos = em.merge(gastosCollectionGastos);
                if (oldProvRucOfGastosCollectionGastos != null) {
                    oldProvRucOfGastosCollectionGastos.getGastosCollection().remove(gastosCollectionGastos);
                    oldProvRucOfGastosCollectionGastos = em.merge(oldProvRucOfGastosCollectionGastos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProveedor(proveedor.getProvRuc()) != null) {
                throw new PreexistingEntityException("Proveedor " + proveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedor proveedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor persistentProveedor = em.find(Proveedor.class, proveedor.getProvRuc());
            Collection<Gastos> gastosCollectionOld = persistentProveedor.getGastosCollection();
            Collection<Gastos> gastosCollectionNew = proveedor.getGastosCollection();
            Collection<Gastos> attachedGastosCollectionNew = new ArrayList<Gastos>();
            for (Gastos gastosCollectionNewGastosToAttach : gastosCollectionNew) {
                gastosCollectionNewGastosToAttach = em.getReference(gastosCollectionNewGastosToAttach.getClass(), gastosCollectionNewGastosToAttach.getGasCod());
                attachedGastosCollectionNew.add(gastosCollectionNewGastosToAttach);
            }
            gastosCollectionNew = attachedGastosCollectionNew;
            proveedor.setGastosCollection(gastosCollectionNew);
            proveedor = em.merge(proveedor);
            for (Gastos gastosCollectionOldGastos : gastosCollectionOld) {
                if (!gastosCollectionNew.contains(gastosCollectionOldGastos)) {
                    gastosCollectionOldGastos.setProvRuc(null);
                    gastosCollectionOldGastos = em.merge(gastosCollectionOldGastos);
                }
            }
            for (Gastos gastosCollectionNewGastos : gastosCollectionNew) {
                if (!gastosCollectionOld.contains(gastosCollectionNewGastos)) {
                    Proveedor oldProvRucOfGastosCollectionNewGastos = gastosCollectionNewGastos.getProvRuc();
                    gastosCollectionNewGastos.setProvRuc(proveedor);
                    gastosCollectionNewGastos = em.merge(gastosCollectionNewGastos);
                    if (oldProvRucOfGastosCollectionNewGastos != null && !oldProvRucOfGastosCollectionNewGastos.equals(proveedor)) {
                        oldProvRucOfGastosCollectionNewGastos.getGastosCollection().remove(gastosCollectionNewGastos);
                        oldProvRucOfGastosCollectionNewGastos = em.merge(oldProvRucOfGastosCollectionNewGastos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = proveedor.getProvRuc();
                if (findProveedor(id) == null) {
                    throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.");
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
            Proveedor proveedor;
            try {
                proveedor = em.getReference(Proveedor.class, id);
                proveedor.getProvRuc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.", enfe);
            }
            Collection<Gastos> gastosCollection = proveedor.getGastosCollection();
            for (Gastos gastosCollectionGastos : gastosCollection) {
                gastosCollectionGastos.setProvRuc(null);
                gastosCollectionGastos = em.merge(gastosCollectionGastos);
            }
            em.remove(proveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedor> findProveedorEntities() {
        return findProveedorEntities(true, -1, -1);
    }

    public List<Proveedor> findProveedorEntities(int maxResults, int firstResult) {
        return findProveedorEntities(false, maxResults, firstResult);
    }

    private List<Proveedor> findProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedor.class));
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

    public Proveedor findProveedor(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedor.class, id);
        } finally {
            em.close();
        }
    }
    public List<Proveedor> findProveedor(boolean habilitado){
        List<Proveedor> lista=getEntityManager().createNamedQuery("Proveedor.findByProvEst").setParameter("provEst",habilitado).getResultList();
        return lista;
    }
    public int getProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedor> rt = cq.from(Proveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
