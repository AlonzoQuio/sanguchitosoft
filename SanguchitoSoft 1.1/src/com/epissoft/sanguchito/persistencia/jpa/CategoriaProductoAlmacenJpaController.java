
package com.epissoft.sanguchito.persistencia.jpa;

import com.epissoft.sanguchito.persistencia.CategoriaProductoAlmacen;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.epissoft.sanguchito.persistencia.ProductoAlmacen;
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
public class CategoriaProductoAlmacenJpaController implements Serializable {

    public CategoriaProductoAlmacenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CategoriaProductoAlmacen categoriaProductoAlmacen) {
        if (categoriaProductoAlmacen.getProductoAlmacenCollection() == null) {
            categoriaProductoAlmacen.setProductoAlmacenCollection(new ArrayList<ProductoAlmacen>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ProductoAlmacen> attachedProductoAlmacenCollection = new ArrayList<ProductoAlmacen>();
            for (ProductoAlmacen productoAlmacenCollectionProductoAlmacenToAttach : categoriaProductoAlmacen.getProductoAlmacenCollection()) {
                productoAlmacenCollectionProductoAlmacenToAttach = em.getReference(productoAlmacenCollectionProductoAlmacenToAttach.getClass(), productoAlmacenCollectionProductoAlmacenToAttach.getProdAlmCod());
                attachedProductoAlmacenCollection.add(productoAlmacenCollectionProductoAlmacenToAttach);
            }
            categoriaProductoAlmacen.setProductoAlmacenCollection(attachedProductoAlmacenCollection);
            em.persist(categoriaProductoAlmacen);
            for (ProductoAlmacen productoAlmacenCollectionProductoAlmacen : categoriaProductoAlmacen.getProductoAlmacenCollection()) {
                CategoriaProductoAlmacen oldCatProdAlmCodOfProductoAlmacenCollectionProductoAlmacen = productoAlmacenCollectionProductoAlmacen.getCatProdAlmCod();
                productoAlmacenCollectionProductoAlmacen.setCatProdAlmCod(categoriaProductoAlmacen);
                productoAlmacenCollectionProductoAlmacen = em.merge(productoAlmacenCollectionProductoAlmacen);
                if (oldCatProdAlmCodOfProductoAlmacenCollectionProductoAlmacen != null) {
                    oldCatProdAlmCodOfProductoAlmacenCollectionProductoAlmacen.getProductoAlmacenCollection().remove(productoAlmacenCollectionProductoAlmacen);
                    oldCatProdAlmCodOfProductoAlmacenCollectionProductoAlmacen = em.merge(oldCatProdAlmCodOfProductoAlmacenCollectionProductoAlmacen);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CategoriaProductoAlmacen categoriaProductoAlmacen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriaProductoAlmacen persistentCategoriaProductoAlmacen = em.find(CategoriaProductoAlmacen.class, categoriaProductoAlmacen.getCatProdAlmCod());
            Collection<ProductoAlmacen> productoAlmacenCollectionOld = persistentCategoriaProductoAlmacen.getProductoAlmacenCollection();
            Collection<ProductoAlmacen> productoAlmacenCollectionNew = categoriaProductoAlmacen.getProductoAlmacenCollection();
            Collection<ProductoAlmacen> attachedProductoAlmacenCollectionNew = new ArrayList<ProductoAlmacen>();
            for (ProductoAlmacen productoAlmacenCollectionNewProductoAlmacenToAttach : productoAlmacenCollectionNew) {
                productoAlmacenCollectionNewProductoAlmacenToAttach = em.getReference(productoAlmacenCollectionNewProductoAlmacenToAttach.getClass(), productoAlmacenCollectionNewProductoAlmacenToAttach.getProdAlmCod());
                attachedProductoAlmacenCollectionNew.add(productoAlmacenCollectionNewProductoAlmacenToAttach);
            }
            productoAlmacenCollectionNew = attachedProductoAlmacenCollectionNew;
            categoriaProductoAlmacen.setProductoAlmacenCollection(productoAlmacenCollectionNew);
            categoriaProductoAlmacen = em.merge(categoriaProductoAlmacen);
            for (ProductoAlmacen productoAlmacenCollectionOldProductoAlmacen : productoAlmacenCollectionOld) {
                if (!productoAlmacenCollectionNew.contains(productoAlmacenCollectionOldProductoAlmacen)) {
                    productoAlmacenCollectionOldProductoAlmacen.setCatProdAlmCod(null);
                    productoAlmacenCollectionOldProductoAlmacen = em.merge(productoAlmacenCollectionOldProductoAlmacen);
                }
            }
            for (ProductoAlmacen productoAlmacenCollectionNewProductoAlmacen : productoAlmacenCollectionNew) {
                if (!productoAlmacenCollectionOld.contains(productoAlmacenCollectionNewProductoAlmacen)) {
                    CategoriaProductoAlmacen oldCatProdAlmCodOfProductoAlmacenCollectionNewProductoAlmacen = productoAlmacenCollectionNewProductoAlmacen.getCatProdAlmCod();
                    productoAlmacenCollectionNewProductoAlmacen.setCatProdAlmCod(categoriaProductoAlmacen);
                    productoAlmacenCollectionNewProductoAlmacen = em.merge(productoAlmacenCollectionNewProductoAlmacen);
                    if (oldCatProdAlmCodOfProductoAlmacenCollectionNewProductoAlmacen != null && !oldCatProdAlmCodOfProductoAlmacenCollectionNewProductoAlmacen.equals(categoriaProductoAlmacen)) {
                        oldCatProdAlmCodOfProductoAlmacenCollectionNewProductoAlmacen.getProductoAlmacenCollection().remove(productoAlmacenCollectionNewProductoAlmacen);
                        oldCatProdAlmCodOfProductoAlmacenCollectionNewProductoAlmacen = em.merge(oldCatProdAlmCodOfProductoAlmacenCollectionNewProductoAlmacen);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = categoriaProductoAlmacen.getCatProdAlmCod();
                if (findCategoriaProductoAlmacen(id) == null) {
                    throw new NonexistentEntityException("The categoriaProductoAlmacen with id " + id + " no longer exists.");
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
            CategoriaProductoAlmacen categoriaProductoAlmacen;
            try {
                categoriaProductoAlmacen = em.getReference(CategoriaProductoAlmacen.class, id);
                categoriaProductoAlmacen.getCatProdAlmCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoriaProductoAlmacen with id " + id + " no longer exists.", enfe);
            }
            Collection<ProductoAlmacen> productoAlmacenCollection = categoriaProductoAlmacen.getProductoAlmacenCollection();
            for (ProductoAlmacen productoAlmacenCollectionProductoAlmacen : productoAlmacenCollection) {
                productoAlmacenCollectionProductoAlmacen.setCatProdAlmCod(null);
                productoAlmacenCollectionProductoAlmacen = em.merge(productoAlmacenCollectionProductoAlmacen);
            }
            em.remove(categoriaProductoAlmacen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CategoriaProductoAlmacen> findCategoriaProductoAlmacenEntities() {
        return findCategoriaProductoAlmacenEntities(true, -1, -1);
    }

    public List<CategoriaProductoAlmacen> findCategoriaProductoAlmacenEntities(int maxResults, int firstResult) {
        return findCategoriaProductoAlmacenEntities(false, maxResults, firstResult);
    }

    private List<CategoriaProductoAlmacen> findCategoriaProductoAlmacenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CategoriaProductoAlmacen.class));
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

    public CategoriaProductoAlmacen findCategoriaProductoAlmacen(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CategoriaProductoAlmacen.class, id);
        } finally {
            em.close();
        }
    }
    public CategoriaProductoAlmacen findCategoriaProductoAlmacen(String detalle){
        List list=getEntityManager().createNamedQuery("CategoriaProductoAlmacen.findByCatProdAlmDes").setParameter("catProdAlmDes", detalle).getResultList();
        if(list.isEmpty()){
            return null;
        }else{
            return (CategoriaProductoAlmacen)list.get(0);
        }
    }
    public List<CategoriaProductoAlmacen> findCategoriaProductoAlmacen(boolean habilitado){
        List<CategoriaProductoAlmacen> lista=getEntityManager().createNamedQuery("CategoriaProductoAlmacen.findByCatProdAlmEst").setParameter("catProdAlmEst",habilitado).getResultList();
        return lista;
    }
    
    public int getCategoriaProductoAlmacenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CategoriaProductoAlmacen> rt = cq.from(CategoriaProductoAlmacen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
