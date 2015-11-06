
package com.epissoft.sanguchito.persistencia.jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.epissoft.sanguchito.persistencia.CategoriaProductoAlmacen;
import com.epissoft.sanguchito.persistencia.UnidadMedida;
import com.epissoft.sanguchito.persistencia.Almacen;
import com.epissoft.sanguchito.persistencia.Kardex;
import com.epissoft.sanguchito.persistencia.ProductoAlmacen;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.IllegalOrphanException;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ProductoAlmacenJpaController implements Serializable {

    public ProductoAlmacenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductoAlmacen productoAlmacen) {
        if (productoAlmacen.getKardexCollection() == null) {
            productoAlmacen.setKardexCollection(new ArrayList<Kardex>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriaProductoAlmacen catProdAlmCod = productoAlmacen.getCatProdAlmCod();
            if (catProdAlmCod != null) {
                catProdAlmCod = em.getReference(catProdAlmCod.getClass(), catProdAlmCod.getCatProdAlmCod());
                productoAlmacen.setCatProdAlmCod(catProdAlmCod);
            }
            UnidadMedida uniMedCod = productoAlmacen.getUniMedCod();
            if (uniMedCod != null) {
                uniMedCod = em.getReference(uniMedCod.getClass(), uniMedCod.getUniMedCod());
                productoAlmacen.setUniMedCod(uniMedCod);
            }
            Almacen almacen = productoAlmacen.getAlmacen();
            if (almacen != null) {
                almacen = em.getReference(almacen.getClass(), almacen.getIngCod());
                productoAlmacen.setAlmacen(almacen);
            }
            Collection<Kardex> attachedKardexCollection = new ArrayList<Kardex>();
            for (Kardex kardexCollectionKardexToAttach : productoAlmacen.getKardexCollection()) {
                kardexCollectionKardexToAttach = em.getReference(kardexCollectionKardexToAttach.getClass(), kardexCollectionKardexToAttach.getKarCod());
                attachedKardexCollection.add(kardexCollectionKardexToAttach);
            }
            productoAlmacen.setKardexCollection(attachedKardexCollection);
            em.persist(productoAlmacen);
            if (catProdAlmCod != null) {
                catProdAlmCod.getProductoAlmacenCollection().add(productoAlmacen);
                catProdAlmCod = em.merge(catProdAlmCod);
            }
            if (uniMedCod != null) {
                uniMedCod.getProductoAlmacenCollection().add(productoAlmacen);
                uniMedCod = em.merge(uniMedCod);
            }
            if (almacen != null) {
                ProductoAlmacen oldProductoAlmacenOfAlmacen = almacen.getProductoAlmacen();
                if (oldProductoAlmacenOfAlmacen != null) {
                    oldProductoAlmacenOfAlmacen.setAlmacen(null);
                    oldProductoAlmacenOfAlmacen = em.merge(oldProductoAlmacenOfAlmacen);
                }
                almacen.setProductoAlmacen(productoAlmacen);
                almacen = em.merge(almacen);
            }
            for (Kardex kardexCollectionKardex : productoAlmacen.getKardexCollection()) {
                ProductoAlmacen oldProdAlmCodOfKardexCollectionKardex = kardexCollectionKardex.getProdAlmCod();
                kardexCollectionKardex.setProdAlmCod(productoAlmacen);
                kardexCollectionKardex = em.merge(kardexCollectionKardex);
                if (oldProdAlmCodOfKardexCollectionKardex != null) {
                    oldProdAlmCodOfKardexCollectionKardex.getKardexCollection().remove(kardexCollectionKardex);
                    oldProdAlmCodOfKardexCollectionKardex = em.merge(oldProdAlmCodOfKardexCollectionKardex);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductoAlmacen productoAlmacen) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductoAlmacen persistentProductoAlmacen = em.find(ProductoAlmacen.class, productoAlmacen.getProdAlmCod());
            CategoriaProductoAlmacen catProdAlmCodOld = persistentProductoAlmacen.getCatProdAlmCod();
            CategoriaProductoAlmacen catProdAlmCodNew = productoAlmacen.getCatProdAlmCod();
            UnidadMedida uniMedCodOld = persistentProductoAlmacen.getUniMedCod();
            UnidadMedida uniMedCodNew = productoAlmacen.getUniMedCod();
            Almacen almacenOld = persistentProductoAlmacen.getAlmacen();
            Almacen almacenNew = productoAlmacen.getAlmacen();
            Collection<Kardex> kardexCollectionOld = persistentProductoAlmacen.getKardexCollection();
            Collection<Kardex> kardexCollectionNew = productoAlmacen.getKardexCollection();
            List<String> illegalOrphanMessages = null;
            if (almacenOld != null && !almacenOld.equals(almacenNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Almacen " + almacenOld + " since its productoAlmacen field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (catProdAlmCodNew != null) {
                catProdAlmCodNew = em.getReference(catProdAlmCodNew.getClass(), catProdAlmCodNew.getCatProdAlmCod());
                productoAlmacen.setCatProdAlmCod(catProdAlmCodNew);
            }
            if (uniMedCodNew != null) {
                uniMedCodNew = em.getReference(uniMedCodNew.getClass(), uniMedCodNew.getUniMedCod());
                productoAlmacen.setUniMedCod(uniMedCodNew);
            }
            if (almacenNew != null) {
                almacenNew = em.getReference(almacenNew.getClass(), almacenNew.getIngCod());
                productoAlmacen.setAlmacen(almacenNew);
            }
            Collection<Kardex> attachedKardexCollectionNew = new ArrayList<Kardex>();
            for (Kardex kardexCollectionNewKardexToAttach : kardexCollectionNew) {
                kardexCollectionNewKardexToAttach = em.getReference(kardexCollectionNewKardexToAttach.getClass(), kardexCollectionNewKardexToAttach.getKarCod());
                attachedKardexCollectionNew.add(kardexCollectionNewKardexToAttach);
            }
            kardexCollectionNew = attachedKardexCollectionNew;
            productoAlmacen.setKardexCollection(kardexCollectionNew);
            productoAlmacen = em.merge(productoAlmacen);
            if (catProdAlmCodOld != null && !catProdAlmCodOld.equals(catProdAlmCodNew)) {
                catProdAlmCodOld.getProductoAlmacenCollection().remove(productoAlmacen);
                catProdAlmCodOld = em.merge(catProdAlmCodOld);
            }
            if (catProdAlmCodNew != null && !catProdAlmCodNew.equals(catProdAlmCodOld)) {
                catProdAlmCodNew.getProductoAlmacenCollection().add(productoAlmacen);
                catProdAlmCodNew = em.merge(catProdAlmCodNew);
            }
            if (uniMedCodOld != null && !uniMedCodOld.equals(uniMedCodNew)) {
                uniMedCodOld.getProductoAlmacenCollection().remove(productoAlmacen);
                uniMedCodOld = em.merge(uniMedCodOld);
            }
            if (uniMedCodNew != null && !uniMedCodNew.equals(uniMedCodOld)) {
                uniMedCodNew.getProductoAlmacenCollection().add(productoAlmacen);
                uniMedCodNew = em.merge(uniMedCodNew);
            }
            if (almacenNew != null && !almacenNew.equals(almacenOld)) {
                ProductoAlmacen oldProductoAlmacenOfAlmacen = almacenNew.getProductoAlmacen();
                if (oldProductoAlmacenOfAlmacen != null) {
                    oldProductoAlmacenOfAlmacen.setAlmacen(null);
                    oldProductoAlmacenOfAlmacen = em.merge(oldProductoAlmacenOfAlmacen);
                }
                almacenNew.setProductoAlmacen(productoAlmacen);
                almacenNew = em.merge(almacenNew);
            }
            for (Kardex kardexCollectionOldKardex : kardexCollectionOld) {
                if (!kardexCollectionNew.contains(kardexCollectionOldKardex)) {
                    kardexCollectionOldKardex.setProdAlmCod(null);
                    kardexCollectionOldKardex = em.merge(kardexCollectionOldKardex);
                }
            }
            for (Kardex kardexCollectionNewKardex : kardexCollectionNew) {
                if (!kardexCollectionOld.contains(kardexCollectionNewKardex)) {
                    ProductoAlmacen oldProdAlmCodOfKardexCollectionNewKardex = kardexCollectionNewKardex.getProdAlmCod();
                    kardexCollectionNewKardex.setProdAlmCod(productoAlmacen);
                    kardexCollectionNewKardex = em.merge(kardexCollectionNewKardex);
                    if (oldProdAlmCodOfKardexCollectionNewKardex != null && !oldProdAlmCodOfKardexCollectionNewKardex.equals(productoAlmacen)) {
                        oldProdAlmCodOfKardexCollectionNewKardex.getKardexCollection().remove(kardexCollectionNewKardex);
                        oldProdAlmCodOfKardexCollectionNewKardex = em.merge(oldProdAlmCodOfKardexCollectionNewKardex);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = productoAlmacen.getProdAlmCod();
                if (findProductoAlmacen(id) == null) {
                    throw new NonexistentEntityException("The productoAlmacen with id " + id + " no longer exists.");
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
            ProductoAlmacen productoAlmacen;
            try {
                productoAlmacen = em.getReference(ProductoAlmacen.class, id);
                productoAlmacen.getProdAlmCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productoAlmacen with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Almacen almacenOrphanCheck = productoAlmacen.getAlmacen();
            if (almacenOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ProductoAlmacen (" + productoAlmacen + ") cannot be destroyed since the Almacen " + almacenOrphanCheck + " in its almacen field has a non-nullable productoAlmacen field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CategoriaProductoAlmacen catProdAlmCod = productoAlmacen.getCatProdAlmCod();
            if (catProdAlmCod != null) {
                catProdAlmCod.getProductoAlmacenCollection().remove(productoAlmacen);
                catProdAlmCod = em.merge(catProdAlmCod);
            }
            UnidadMedida uniMedCod = productoAlmacen.getUniMedCod();
            if (uniMedCod != null) {
                uniMedCod.getProductoAlmacenCollection().remove(productoAlmacen);
                uniMedCod = em.merge(uniMedCod);
            }
            Collection<Kardex> kardexCollection = productoAlmacen.getKardexCollection();
            for (Kardex kardexCollectionKardex : kardexCollection) {
                kardexCollectionKardex.setProdAlmCod(null);
                kardexCollectionKardex = em.merge(kardexCollectionKardex);
            }
            em.remove(productoAlmacen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductoAlmacen> findProductoAlmacenEntities() {
        return findProductoAlmacenEntities(true, -1, -1);
    }

    public List<ProductoAlmacen> findProductoAlmacenEntities(int maxResults, int firstResult) {
        return findProductoAlmacenEntities(false, maxResults, firstResult);
    }

    private List<ProductoAlmacen> findProductoAlmacenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductoAlmacen.class));
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

    public ProductoAlmacen findProductoAlmacen(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductoAlmacen.class, id);
        } finally {
            em.close();
        }
    }
    public ProductoAlmacen findProductoAlmacen(String detalle){
        List list=getEntityManager().createNamedQuery("ProductoAlmacen.findByProdAlmDes").setParameter("prodAlmDes", detalle).getResultList();
        if(list.isEmpty()){
            return null;
        }else{
            return (ProductoAlmacen)list.get(0);
        }
    }
    public List<ProductoAlmacen> findProductoAlmacenByCategoria(int catProductoAlmacen,boolean habilitado){
        Query consulta = getEntityManager().createNamedQuery("ProductoAlmacen.findByCatProdAlmCod");
        consulta.setParameter("catProdAlmCod", new CategoriaProductoAlmacen(catProductoAlmacen));
        consulta.setParameter("prodAlmEst",habilitado);
        List<ProductoAlmacen> lista= consulta.getResultList();
        return lista;
    }
    public List<ProductoAlmacen> findProductoAlmacen(boolean habilitado){
        List<ProductoAlmacen> lista=getEntityManager().createNamedQuery("ProductoAlmacen.findByProdAlmEst").setParameter("prodAlmEst",habilitado).getResultList();
        return lista;
    }
    public int getProductoAlmacenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductoAlmacen> rt = cq.from(ProductoAlmacen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
