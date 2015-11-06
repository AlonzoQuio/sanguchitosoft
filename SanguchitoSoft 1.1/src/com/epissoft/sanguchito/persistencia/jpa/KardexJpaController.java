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
import com.epissoft.sanguchito.persistencia.EgresoKardex;
import com.epissoft.sanguchito.persistencia.IngresoKardex;
import com.epissoft.sanguchito.persistencia.Kardex;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.IllegalOrphanException;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Hisae Shizumaru
 */
public class KardexJpaController implements Serializable {

    public KardexJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Kardex kardex) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductoAlmacen prodAlmCod = kardex.getProdAlmCod();
            if (prodAlmCod != null) {
                prodAlmCod = em.getReference(prodAlmCod.getClass(), prodAlmCod.getProdAlmCod());
                kardex.setProdAlmCod(prodAlmCod);
            }
            EgresoKardex egresoKardex = kardex.getEgresoKardex();
            if (egresoKardex != null) {
                egresoKardex = em.getReference(egresoKardex.getClass(), egresoKardex.getKarCod());
                kardex.setEgresoKardex(egresoKardex);
            }
            IngresoKardex ingresoKardex = kardex.getIngresoKardex();
            if (ingresoKardex != null) {
                ingresoKardex = em.getReference(ingresoKardex.getClass(), ingresoKardex.getKarCod());
                kardex.setIngresoKardex(ingresoKardex);
            }
            em.persist(kardex);
            if (prodAlmCod != null) {
                prodAlmCod.getKardexCollection().add(kardex);
                prodAlmCod = em.merge(prodAlmCod);
            }
            if (egresoKardex != null) {
                Kardex oldKardexOfEgresoKardex = egresoKardex.getKardex();
                if (oldKardexOfEgresoKardex != null) {
                    oldKardexOfEgresoKardex.setEgresoKardex(null);
                    oldKardexOfEgresoKardex = em.merge(oldKardexOfEgresoKardex);
                }
                egresoKardex.setKardex(kardex);
                egresoKardex = em.merge(egresoKardex);
            }
            if (ingresoKardex != null) {
                Kardex oldKardexOfIngresoKardex = ingresoKardex.getKardex();
                if (oldKardexOfIngresoKardex != null) {
                    oldKardexOfIngresoKardex.setIngresoKardex(null);
                    oldKardexOfIngresoKardex = em.merge(oldKardexOfIngresoKardex);
                }
                ingresoKardex.setKardex(kardex);
                ingresoKardex = em.merge(ingresoKardex);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Kardex kardex) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Kardex persistentKardex = em.find(Kardex.class, kardex.getKarCod());
            ProductoAlmacen prodAlmCodOld = persistentKardex.getProdAlmCod();
            ProductoAlmacen prodAlmCodNew = kardex.getProdAlmCod();
            EgresoKardex egresoKardexOld = persistentKardex.getEgresoKardex();
            EgresoKardex egresoKardexNew = kardex.getEgresoKardex();
            IngresoKardex ingresoKardexOld = persistentKardex.getIngresoKardex();
            IngresoKardex ingresoKardexNew = kardex.getIngresoKardex();
            List<String> illegalOrphanMessages = null;
            if (egresoKardexOld != null && !egresoKardexOld.equals(egresoKardexNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain EgresoKardex " + egresoKardexOld + " since its kardex field is not nullable.");
            }
            if (ingresoKardexOld != null && !ingresoKardexOld.equals(ingresoKardexNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain IngresoKardex " + ingresoKardexOld + " since its kardex field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (prodAlmCodNew != null) {
                prodAlmCodNew = em.getReference(prodAlmCodNew.getClass(), prodAlmCodNew.getProdAlmCod());
                kardex.setProdAlmCod(prodAlmCodNew);
            }
            if (egresoKardexNew != null) {
                egresoKardexNew = em.getReference(egresoKardexNew.getClass(), egresoKardexNew.getKarCod());
                kardex.setEgresoKardex(egresoKardexNew);
            }
            if (ingresoKardexNew != null) {
                ingresoKardexNew = em.getReference(ingresoKardexNew.getClass(), ingresoKardexNew.getKarCod());
                kardex.setIngresoKardex(ingresoKardexNew);
            }
            kardex = em.merge(kardex);
            if (prodAlmCodOld != null && !prodAlmCodOld.equals(prodAlmCodNew)) {
                prodAlmCodOld.getKardexCollection().remove(kardex);
                prodAlmCodOld = em.merge(prodAlmCodOld);
            }
            if (prodAlmCodNew != null && !prodAlmCodNew.equals(prodAlmCodOld)) {
                prodAlmCodNew.getKardexCollection().add(kardex);
                prodAlmCodNew = em.merge(prodAlmCodNew);
            }
            if (egresoKardexNew != null && !egresoKardexNew.equals(egresoKardexOld)) {
                Kardex oldKardexOfEgresoKardex = egresoKardexNew.getKardex();
                if (oldKardexOfEgresoKardex != null) {
                    oldKardexOfEgresoKardex.setEgresoKardex(null);
                    oldKardexOfEgresoKardex = em.merge(oldKardexOfEgresoKardex);
                }
                egresoKardexNew.setKardex(kardex);
                egresoKardexNew = em.merge(egresoKardexNew);
            }
            if (ingresoKardexNew != null && !ingresoKardexNew.equals(ingresoKardexOld)) {
                Kardex oldKardexOfIngresoKardex = ingresoKardexNew.getKardex();
                if (oldKardexOfIngresoKardex != null) {
                    oldKardexOfIngresoKardex.setIngresoKardex(null);
                    oldKardexOfIngresoKardex = em.merge(oldKardexOfIngresoKardex);
                }
                ingresoKardexNew.setKardex(kardex);
                ingresoKardexNew = em.merge(ingresoKardexNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = kardex.getKarCod();
                if (findKardex(id) == null) {
                    throw new NonexistentEntityException("The kardex with id " + id + " no longer exists.");
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
            Kardex kardex;
            try {
                kardex = em.getReference(Kardex.class, id);
                kardex.getKarCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The kardex with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            EgresoKardex egresoKardexOrphanCheck = kardex.getEgresoKardex();
            if (egresoKardexOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Kardex (" + kardex + ") cannot be destroyed since the EgresoKardex " + egresoKardexOrphanCheck + " in its egresoKardex field has a non-nullable kardex field.");
            }
            IngresoKardex ingresoKardexOrphanCheck = kardex.getIngresoKardex();
            if (ingresoKardexOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Kardex (" + kardex + ") cannot be destroyed since the IngresoKardex " + ingresoKardexOrphanCheck + " in its ingresoKardex field has a non-nullable kardex field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            ProductoAlmacen prodAlmCod = kardex.getProdAlmCod();
            if (prodAlmCod != null) {
                prodAlmCod.getKardexCollection().remove(kardex);
                prodAlmCod = em.merge(prodAlmCod);
            }
            em.remove(kardex);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Kardex> findKardexEntities() {
        return findKardexEntities(true, -1, -1);
    }

    public List<Kardex> findKardexEntities(int maxResults, int firstResult) {
        return findKardexEntities(false, maxResults, firstResult);
    }

    private List<Kardex> findKardexEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Kardex.class));
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

    public Kardex findKardex(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Kardex.class, id);
        } finally {
            em.close();
        }
    }
    public List<Kardex> findKardexByProdAlm(int codProd){
        Query consulta = getEntityManager().createNamedQuery("Kardex.findByProdAlmCod");
        consulta.setParameter("prodAlmCod", new ProductoAlmacen(codProd));
        List<Kardex> lista= consulta.getResultList();
        return lista;
    }
    public int getKardexCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Kardex> rt = cq.from(Kardex.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
