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
import com.epissoft.sanguchito.persistencia.CategoriaProducto;
import java.util.ArrayList;
import java.util.Collection;
import com.epissoft.sanguchito.persistencia.Producto;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.IllegalOrphanException;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Hisae Shizumaru
 */
public class CategoriaProductoJpaController implements Serializable {

    public CategoriaProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CategoriaProducto categoriaProducto) {
        if (categoriaProducto.getCategoriaExtraCollection() == null) {
            categoriaProducto.setCategoriaExtraCollection(new ArrayList<CategoriaExtra>());
        }
        if (categoriaProducto.getProductoCollection() == null) {
            categoriaProducto.setProductoCollection(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<CategoriaExtra> attachedCategoriaExtraCollection = new ArrayList<CategoriaExtra>();
            for (CategoriaExtra categoriaExtraCollectionCategoriaExtraToAttach : categoriaProducto.getCategoriaExtraCollection()) {
                categoriaExtraCollectionCategoriaExtraToAttach = em.getReference(categoriaExtraCollectionCategoriaExtraToAttach.getClass(), categoriaExtraCollectionCategoriaExtraToAttach.getCatExtCod());
                attachedCategoriaExtraCollection.add(categoriaExtraCollectionCategoriaExtraToAttach);
            }
            categoriaProducto.setCategoriaExtraCollection(attachedCategoriaExtraCollection);
            Collection<Producto> attachedProductoCollection = new ArrayList<Producto>();
            for (Producto productoCollectionProductoToAttach : categoriaProducto.getProductoCollection()) {
                productoCollectionProductoToAttach = em.getReference(productoCollectionProductoToAttach.getClass(), productoCollectionProductoToAttach.getProCod());
                attachedProductoCollection.add(productoCollectionProductoToAttach);
            }
            categoriaProducto.setProductoCollection(attachedProductoCollection);
            em.persist(categoriaProducto);
            for (CategoriaExtra categoriaExtraCollectionCategoriaExtra : categoriaProducto.getCategoriaExtraCollection()) {
                categoriaExtraCollectionCategoriaExtra.getCategoriaProductoCollection().add(categoriaProducto);
                categoriaExtraCollectionCategoriaExtra = em.merge(categoriaExtraCollectionCategoriaExtra);
            }
            for (Producto productoCollectionProducto : categoriaProducto.getProductoCollection()) {
                CategoriaProducto oldCatProdCodOfProductoCollectionProducto = productoCollectionProducto.getCatProdCod();
                productoCollectionProducto.setCatProdCod(categoriaProducto);
                productoCollectionProducto = em.merge(productoCollectionProducto);
                if (oldCatProdCodOfProductoCollectionProducto != null) {
                    oldCatProdCodOfProductoCollectionProducto.getProductoCollection().remove(productoCollectionProducto);
                    oldCatProdCodOfProductoCollectionProducto = em.merge(oldCatProdCodOfProductoCollectionProducto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CategoriaProducto categoriaProducto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriaProducto persistentCategoriaProducto = em.find(CategoriaProducto.class, categoriaProducto.getCatProdCod());
            Collection<CategoriaExtra> categoriaExtraCollectionOld = persistentCategoriaProducto.getCategoriaExtraCollection();
            Collection<CategoriaExtra> categoriaExtraCollectionNew = categoriaProducto.getCategoriaExtraCollection();
            Collection<Producto> productoCollectionOld = persistentCategoriaProducto.getProductoCollection();
            Collection<Producto> productoCollectionNew = categoriaProducto.getProductoCollection();
            List<String> illegalOrphanMessages = null;
            for (Producto productoCollectionOldProducto : productoCollectionOld) {
                if (!productoCollectionNew.contains(productoCollectionOldProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Producto " + productoCollectionOldProducto + " since its catProdCod field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<CategoriaExtra> attachedCategoriaExtraCollectionNew = new ArrayList<CategoriaExtra>();
            for (CategoriaExtra categoriaExtraCollectionNewCategoriaExtraToAttach : categoriaExtraCollectionNew) {
                categoriaExtraCollectionNewCategoriaExtraToAttach = em.getReference(categoriaExtraCollectionNewCategoriaExtraToAttach.getClass(), categoriaExtraCollectionNewCategoriaExtraToAttach.getCatExtCod());
                attachedCategoriaExtraCollectionNew.add(categoriaExtraCollectionNewCategoriaExtraToAttach);
            }
            categoriaExtraCollectionNew = attachedCategoriaExtraCollectionNew;
            categoriaProducto.setCategoriaExtraCollection(categoriaExtraCollectionNew);
            Collection<Producto> attachedProductoCollectionNew = new ArrayList<Producto>();
            for (Producto productoCollectionNewProductoToAttach : productoCollectionNew) {
                productoCollectionNewProductoToAttach = em.getReference(productoCollectionNewProductoToAttach.getClass(), productoCollectionNewProductoToAttach.getProCod());
                attachedProductoCollectionNew.add(productoCollectionNewProductoToAttach);
            }
            productoCollectionNew = attachedProductoCollectionNew;
            categoriaProducto.setProductoCollection(productoCollectionNew);
            categoriaProducto = em.merge(categoriaProducto);
            for (CategoriaExtra categoriaExtraCollectionOldCategoriaExtra : categoriaExtraCollectionOld) {
                if (!categoriaExtraCollectionNew.contains(categoriaExtraCollectionOldCategoriaExtra)) {
                    categoriaExtraCollectionOldCategoriaExtra.getCategoriaProductoCollection().remove(categoriaProducto);
                    categoriaExtraCollectionOldCategoriaExtra = em.merge(categoriaExtraCollectionOldCategoriaExtra);
                }
            }
            for (CategoriaExtra categoriaExtraCollectionNewCategoriaExtra : categoriaExtraCollectionNew) {
                if (!categoriaExtraCollectionOld.contains(categoriaExtraCollectionNewCategoriaExtra)) {
                    categoriaExtraCollectionNewCategoriaExtra.getCategoriaProductoCollection().add(categoriaProducto);
                    categoriaExtraCollectionNewCategoriaExtra = em.merge(categoriaExtraCollectionNewCategoriaExtra);
                }
            }
            for (Producto productoCollectionNewProducto : productoCollectionNew) {
                if (!productoCollectionOld.contains(productoCollectionNewProducto)) {
                    CategoriaProducto oldCatProdCodOfProductoCollectionNewProducto = productoCollectionNewProducto.getCatProdCod();
                    productoCollectionNewProducto.setCatProdCod(categoriaProducto);
                    productoCollectionNewProducto = em.merge(productoCollectionNewProducto);
                    if (oldCatProdCodOfProductoCollectionNewProducto != null && !oldCatProdCodOfProductoCollectionNewProducto.equals(categoriaProducto)) {
                        oldCatProdCodOfProductoCollectionNewProducto.getProductoCollection().remove(productoCollectionNewProducto);
                        oldCatProdCodOfProductoCollectionNewProducto = em.merge(oldCatProdCodOfProductoCollectionNewProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = categoriaProducto.getCatProdCod();
                if (findCategoriaProducto(id) == null) {
                    throw new NonexistentEntityException("The categoriaProducto with id " + id + " no longer exists.");
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
            CategoriaProducto categoriaProducto;
            try {
                categoriaProducto = em.getReference(CategoriaProducto.class, id);
                categoriaProducto.getCatProdCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoriaProducto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Producto> productoCollectionOrphanCheck = categoriaProducto.getProductoCollection();
            for (Producto productoCollectionOrphanCheckProducto : productoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CategoriaProducto (" + categoriaProducto + ") cannot be destroyed since the Producto " + productoCollectionOrphanCheckProducto + " in its productoCollection field has a non-nullable catProdCod field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<CategoriaExtra> categoriaExtraCollection = categoriaProducto.getCategoriaExtraCollection();
            for (CategoriaExtra categoriaExtraCollectionCategoriaExtra : categoriaExtraCollection) {
                categoriaExtraCollectionCategoriaExtra.getCategoriaProductoCollection().remove(categoriaProducto);
                categoriaExtraCollectionCategoriaExtra = em.merge(categoriaExtraCollectionCategoriaExtra);
            }
            em.remove(categoriaProducto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CategoriaProducto> findCategoriaProductoEntities() {
        return findCategoriaProductoEntities(true, -1, -1);
    }

    public List<CategoriaProducto> findCategoriaProductoEntities(int maxResults, int firstResult) {
        return findCategoriaProductoEntities(false, maxResults, firstResult);
    }

    private List<CategoriaProducto> findCategoriaProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CategoriaProducto.class));
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

    public CategoriaProducto findCategoriaProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CategoriaProducto.class, id);
        } finally {
            em.close();
        }
    }
    public CategoriaProducto findCategoriaProducto(String detalle){
        List list=getEntityManager().createNamedQuery("CategoriaProducto.findByCatProdDes").setParameter("catProdDes", detalle).getResultList();
        if(list.isEmpty()){
            return null;
        }else{
            return (CategoriaProducto)list.get(0);
        }
    }
    public List<CategoriaProducto> findCategoriaProducto(boolean habilitado){
        List<CategoriaProducto> lista=getEntityManager().createNamedQuery("CategoriaProducto.findByCatProdEst").setParameter("catProdEst",habilitado).getResultList();
        return lista;
    }
    public int getCategoriaProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CategoriaProducto> rt = cq.from(CategoriaProducto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
