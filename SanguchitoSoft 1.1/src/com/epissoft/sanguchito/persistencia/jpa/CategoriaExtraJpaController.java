
package com.epissoft.sanguchito.persistencia.jpa;

import com.epissoft.sanguchito.persistencia.CategoriaExtra;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.epissoft.sanguchito.persistencia.CategoriaProducto;
import java.util.ArrayList;
import java.util.Collection;
import com.epissoft.sanguchito.persistencia.Extras;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.IllegalOrphanException;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Hisae Shizumaru
 */
public class CategoriaExtraJpaController implements Serializable {

    public CategoriaExtraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CategoriaExtra categoriaExtra) {
        if (categoriaExtra.getCategoriaProductoCollection() == null) {
            categoriaExtra.setCategoriaProductoCollection(new ArrayList<CategoriaProducto>());
        }
        if (categoriaExtra.getExtrasCollection() == null) {
            categoriaExtra.setExtrasCollection(new ArrayList<Extras>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<CategoriaProducto> attachedCategoriaProductoCollection = new ArrayList<CategoriaProducto>();
            for (CategoriaProducto categoriaProductoCollectionCategoriaProductoToAttach : categoriaExtra.getCategoriaProductoCollection()) {
                categoriaProductoCollectionCategoriaProductoToAttach = em.getReference(categoriaProductoCollectionCategoriaProductoToAttach.getClass(), categoriaProductoCollectionCategoriaProductoToAttach.getCatProdCod());
                attachedCategoriaProductoCollection.add(categoriaProductoCollectionCategoriaProductoToAttach);
            }
            categoriaExtra.setCategoriaProductoCollection(attachedCategoriaProductoCollection);
            Collection<Extras> attachedExtrasCollection = new ArrayList<Extras>();
            for (Extras extrasCollectionExtrasToAttach : categoriaExtra.getExtrasCollection()) {
                extrasCollectionExtrasToAttach = em.getReference(extrasCollectionExtrasToAttach.getClass(), extrasCollectionExtrasToAttach.getExtCod());
                attachedExtrasCollection.add(extrasCollectionExtrasToAttach);
            }
            categoriaExtra.setExtrasCollection(attachedExtrasCollection);
            em.persist(categoriaExtra);
            for (CategoriaProducto categoriaProductoCollectionCategoriaProducto : categoriaExtra.getCategoriaProductoCollection()) {
                categoriaProductoCollectionCategoriaProducto.getCategoriaExtraCollection().add(categoriaExtra);
                categoriaProductoCollectionCategoriaProducto = em.merge(categoriaProductoCollectionCategoriaProducto);
            }
            for (Extras extrasCollectionExtras : categoriaExtra.getExtrasCollection()) {
                CategoriaExtra oldCatExtCodOfExtrasCollectionExtras = extrasCollectionExtras.getCatExtCod();
                extrasCollectionExtras.setCatExtCod(categoriaExtra);
                extrasCollectionExtras = em.merge(extrasCollectionExtras);
                if (oldCatExtCodOfExtrasCollectionExtras != null) {
                    oldCatExtCodOfExtrasCollectionExtras.getExtrasCollection().remove(extrasCollectionExtras);
                    oldCatExtCodOfExtrasCollectionExtras = em.merge(oldCatExtCodOfExtrasCollectionExtras);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CategoriaExtra categoriaExtra) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriaExtra persistentCategoriaExtra = em.find(CategoriaExtra.class, categoriaExtra.getCatExtCod());
            Collection<CategoriaProducto> categoriaProductoCollectionOld = persistentCategoriaExtra.getCategoriaProductoCollection();
            Collection<CategoriaProducto> categoriaProductoCollectionNew = categoriaExtra.getCategoriaProductoCollection();
            Collection<Extras> extrasCollectionOld = persistentCategoriaExtra.getExtrasCollection();
            Collection<Extras> extrasCollectionNew = categoriaExtra.getExtrasCollection();
            List<String> illegalOrphanMessages = null;
            for (Extras extrasCollectionOldExtras : extrasCollectionOld) {
                if (!extrasCollectionNew.contains(extrasCollectionOldExtras)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Extras " + extrasCollectionOldExtras + " since its catExtCod field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<CategoriaProducto> attachedCategoriaProductoCollectionNew = new ArrayList<CategoriaProducto>();
            for (CategoriaProducto categoriaProductoCollectionNewCategoriaProductoToAttach : categoriaProductoCollectionNew) {
                categoriaProductoCollectionNewCategoriaProductoToAttach = em.getReference(categoriaProductoCollectionNewCategoriaProductoToAttach.getClass(), categoriaProductoCollectionNewCategoriaProductoToAttach.getCatProdCod());
                attachedCategoriaProductoCollectionNew.add(categoriaProductoCollectionNewCategoriaProductoToAttach);
            }
            categoriaProductoCollectionNew = attachedCategoriaProductoCollectionNew;
            categoriaExtra.setCategoriaProductoCollection(categoriaProductoCollectionNew);
            Collection<Extras> attachedExtrasCollectionNew = new ArrayList<Extras>();
            for (Extras extrasCollectionNewExtrasToAttach : extrasCollectionNew) {
                extrasCollectionNewExtrasToAttach = em.getReference(extrasCollectionNewExtrasToAttach.getClass(), extrasCollectionNewExtrasToAttach.getExtCod());
                attachedExtrasCollectionNew.add(extrasCollectionNewExtrasToAttach);
            }
            extrasCollectionNew = attachedExtrasCollectionNew;
            categoriaExtra.setExtrasCollection(extrasCollectionNew);
            categoriaExtra = em.merge(categoriaExtra);
            for (CategoriaProducto categoriaProductoCollectionOldCategoriaProducto : categoriaProductoCollectionOld) {
                if (!categoriaProductoCollectionNew.contains(categoriaProductoCollectionOldCategoriaProducto)) {
                    categoriaProductoCollectionOldCategoriaProducto.getCategoriaExtraCollection().remove(categoriaExtra);
                    categoriaProductoCollectionOldCategoriaProducto = em.merge(categoriaProductoCollectionOldCategoriaProducto);
                }
            }
            for (CategoriaProducto categoriaProductoCollectionNewCategoriaProducto : categoriaProductoCollectionNew) {
                if (!categoriaProductoCollectionOld.contains(categoriaProductoCollectionNewCategoriaProducto)) {
                    categoriaProductoCollectionNewCategoriaProducto.getCategoriaExtraCollection().add(categoriaExtra);
                    categoriaProductoCollectionNewCategoriaProducto = em.merge(categoriaProductoCollectionNewCategoriaProducto);
                }
            }
            for (Extras extrasCollectionNewExtras : extrasCollectionNew) {
                if (!extrasCollectionOld.contains(extrasCollectionNewExtras)) {
                    CategoriaExtra oldCatExtCodOfExtrasCollectionNewExtras = extrasCollectionNewExtras.getCatExtCod();
                    extrasCollectionNewExtras.setCatExtCod(categoriaExtra);
                    extrasCollectionNewExtras = em.merge(extrasCollectionNewExtras);
                    if (oldCatExtCodOfExtrasCollectionNewExtras != null && !oldCatExtCodOfExtrasCollectionNewExtras.equals(categoriaExtra)) {
                        oldCatExtCodOfExtrasCollectionNewExtras.getExtrasCollection().remove(extrasCollectionNewExtras);
                        oldCatExtCodOfExtrasCollectionNewExtras = em.merge(oldCatExtCodOfExtrasCollectionNewExtras);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = categoriaExtra.getCatExtCod();
                if (findCategoriaExtra(id) == null) {
                    throw new NonexistentEntityException("The categoriaExtra with id " + id + " no longer exists.");
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
            CategoriaExtra categoriaExtra;
            try {
                categoriaExtra = em.getReference(CategoriaExtra.class, id);
                categoriaExtra.getCatExtCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoriaExtra with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Extras> extrasCollectionOrphanCheck = categoriaExtra.getExtrasCollection();
            for (Extras extrasCollectionOrphanCheckExtras : extrasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CategoriaExtra (" + categoriaExtra + ") cannot be destroyed since the Extras " + extrasCollectionOrphanCheckExtras + " in its extrasCollection field has a non-nullable catExtCod field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<CategoriaProducto> categoriaProductoCollection = categoriaExtra.getCategoriaProductoCollection();
            for (CategoriaProducto categoriaProductoCollectionCategoriaProducto : categoriaProductoCollection) {
                categoriaProductoCollectionCategoriaProducto.getCategoriaExtraCollection().remove(categoriaExtra);
                categoriaProductoCollectionCategoriaProducto = em.merge(categoriaProductoCollectionCategoriaProducto);
            }
            em.remove(categoriaExtra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CategoriaExtra> findCategoriaExtraEntities() {
        return findCategoriaExtraEntities(true, -1, -1);
    }

    public List<CategoriaExtra> findCategoriaExtraEntities(int maxResults, int firstResult) {
        return findCategoriaExtraEntities(false, maxResults, firstResult);
    }

    private List<CategoriaExtra> findCategoriaExtraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CategoriaExtra.class));
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

    public CategoriaExtra findCategoriaExtra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CategoriaExtra.class, id);
        } finally {
            em.close();
        }
    }
    public List<CategoriaExtra> findCategoriaExtra(boolean habilitado){
        List<CategoriaExtra> lCategoria=getEntityManager().createNamedQuery("CategoriaExtra.findByCatExtEst").setParameter("catExtEst",habilitado).getResultList();
        return lCategoria;
    }
    public int getCategoriaExtraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CategoriaExtra> rt = cq.from(CategoriaExtra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
