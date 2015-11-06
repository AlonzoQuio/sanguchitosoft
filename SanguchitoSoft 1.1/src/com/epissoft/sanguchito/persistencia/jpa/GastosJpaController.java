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
import com.epissoft.sanguchito.persistencia.Empleado;
import com.epissoft.sanguchito.persistencia.Gastos;
import com.epissoft.sanguchito.persistencia.Proveedor;
import com.epissoft.sanguchito.persistencia.TipoDocumento;
import com.epissoft.sanguchito.persistencia.TipoGasto;
import com.epissoft.sanguchito.persistencia.IngresoKardex;
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
public class GastosJpaController implements Serializable {

    public GastosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Gastos gastos) {
        if (gastos.getIngresoKardexCollection() == null) {
            gastos.setIngresoKardexCollection(new ArrayList<IngresoKardex>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empDni = gastos.getEmpDni();
            if (empDni != null) {
                empDni = em.getReference(empDni.getClass(), empDni.getEmpDni());
                gastos.setEmpDni(empDni);
            }
            Proveedor provRuc = gastos.getProvRuc();
            if (provRuc != null) {
                provRuc = em.getReference(provRuc.getClass(), provRuc.getProvRuc());
                gastos.setProvRuc(provRuc);
            }
            TipoDocumento tipDocCod = gastos.getTipDocCod();
            if (tipDocCod != null) {
                tipDocCod = em.getReference(tipDocCod.getClass(), tipDocCod.getTipDocCod());
                gastos.setTipDocCod(tipDocCod);
            }
            TipoGasto tipGasCod = gastos.getTipGasCod();
            if (tipGasCod != null) {
                tipGasCod = em.getReference(tipGasCod.getClass(), tipGasCod.getTipGasCod());
                gastos.setTipGasCod(tipGasCod);
            }
            Collection<IngresoKardex> attachedIngresoKardexCollection = new ArrayList<IngresoKardex>();
            for (IngresoKardex ingresoKardexCollectionIngresoKardexToAttach : gastos.getIngresoKardexCollection()) {
                ingresoKardexCollectionIngresoKardexToAttach = em.getReference(ingresoKardexCollectionIngresoKardexToAttach.getClass(), ingresoKardexCollectionIngresoKardexToAttach.getKarCod());
                attachedIngresoKardexCollection.add(ingresoKardexCollectionIngresoKardexToAttach);
            }
            gastos.setIngresoKardexCollection(attachedIngresoKardexCollection);
            em.persist(gastos);
            if (empDni != null) {
                empDni.getGastosCollection().add(gastos);
                empDni = em.merge(empDni);
            }
            if (provRuc != null) {
                provRuc.getGastosCollection().add(gastos);
                provRuc = em.merge(provRuc);
            }
            if (tipDocCod != null) {
                tipDocCod.getGastosCollection().add(gastos);
                tipDocCod = em.merge(tipDocCod);
            }
            if (tipGasCod != null) {
                tipGasCod.getGastosCollection().add(gastos);
                tipGasCod = em.merge(tipGasCod);
            }
            for (IngresoKardex ingresoKardexCollectionIngresoKardex : gastos.getIngresoKardexCollection()) {
                Gastos oldGasCodOfIngresoKardexCollectionIngresoKardex = ingresoKardexCollectionIngresoKardex.getGasCod();
                ingresoKardexCollectionIngresoKardex.setGasCod(gastos);
                ingresoKardexCollectionIngresoKardex = em.merge(ingresoKardexCollectionIngresoKardex);
                if (oldGasCodOfIngresoKardexCollectionIngresoKardex != null) {
                    oldGasCodOfIngresoKardexCollectionIngresoKardex.getIngresoKardexCollection().remove(ingresoKardexCollectionIngresoKardex);
                    oldGasCodOfIngresoKardexCollectionIngresoKardex = em.merge(oldGasCodOfIngresoKardexCollectionIngresoKardex);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Gastos gastos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Gastos persistentGastos = em.find(Gastos.class, gastos.getGasCod());
            Empleado empDniOld = persistentGastos.getEmpDni();
            Empleado empDniNew = gastos.getEmpDni();
            Proveedor provRucOld = persistentGastos.getProvRuc();
            Proveedor provRucNew = gastos.getProvRuc();
            TipoDocumento tipDocCodOld = persistentGastos.getTipDocCod();
            TipoDocumento tipDocCodNew = gastos.getTipDocCod();
            TipoGasto tipGasCodOld = persistentGastos.getTipGasCod();
            TipoGasto tipGasCodNew = gastos.getTipGasCod();
            Collection<IngresoKardex> ingresoKardexCollectionOld = persistentGastos.getIngresoKardexCollection();
            Collection<IngresoKardex> ingresoKardexCollectionNew = gastos.getIngresoKardexCollection();
            if (empDniNew != null) {
                empDniNew = em.getReference(empDniNew.getClass(), empDniNew.getEmpDni());
                gastos.setEmpDni(empDniNew);
            }
            if (provRucNew != null) {
                provRucNew = em.getReference(provRucNew.getClass(), provRucNew.getProvRuc());
                gastos.setProvRuc(provRucNew);
            }
            if (tipDocCodNew != null) {
                tipDocCodNew = em.getReference(tipDocCodNew.getClass(), tipDocCodNew.getTipDocCod());
                gastos.setTipDocCod(tipDocCodNew);
            }
            if (tipGasCodNew != null) {
                tipGasCodNew = em.getReference(tipGasCodNew.getClass(), tipGasCodNew.getTipGasCod());
                gastos.setTipGasCod(tipGasCodNew);
            }
            Collection<IngresoKardex> attachedIngresoKardexCollectionNew = new ArrayList<IngresoKardex>();
            for (IngresoKardex ingresoKardexCollectionNewIngresoKardexToAttach : ingresoKardexCollectionNew) {
                ingresoKardexCollectionNewIngresoKardexToAttach = em.getReference(ingresoKardexCollectionNewIngresoKardexToAttach.getClass(), ingresoKardexCollectionNewIngresoKardexToAttach.getKarCod());
                attachedIngresoKardexCollectionNew.add(ingresoKardexCollectionNewIngresoKardexToAttach);
            }
            ingresoKardexCollectionNew = attachedIngresoKardexCollectionNew;
            gastos.setIngresoKardexCollection(ingresoKardexCollectionNew);
            gastos = em.merge(gastos);
            if (empDniOld != null && !empDniOld.equals(empDniNew)) {
                empDniOld.getGastosCollection().remove(gastos);
                empDniOld = em.merge(empDniOld);
            }
            if (empDniNew != null && !empDniNew.equals(empDniOld)) {
                empDniNew.getGastosCollection().add(gastos);
                empDniNew = em.merge(empDniNew);
            }
            if (provRucOld != null && !provRucOld.equals(provRucNew)) {
                provRucOld.getGastosCollection().remove(gastos);
                provRucOld = em.merge(provRucOld);
            }
            if (provRucNew != null && !provRucNew.equals(provRucOld)) {
                provRucNew.getGastosCollection().add(gastos);
                provRucNew = em.merge(provRucNew);
            }
            if (tipDocCodOld != null && !tipDocCodOld.equals(tipDocCodNew)) {
                tipDocCodOld.getGastosCollection().remove(gastos);
                tipDocCodOld = em.merge(tipDocCodOld);
            }
            if (tipDocCodNew != null && !tipDocCodNew.equals(tipDocCodOld)) {
                tipDocCodNew.getGastosCollection().add(gastos);
                tipDocCodNew = em.merge(tipDocCodNew);
            }
            if (tipGasCodOld != null && !tipGasCodOld.equals(tipGasCodNew)) {
                tipGasCodOld.getGastosCollection().remove(gastos);
                tipGasCodOld = em.merge(tipGasCodOld);
            }
            if (tipGasCodNew != null && !tipGasCodNew.equals(tipGasCodOld)) {
                tipGasCodNew.getGastosCollection().add(gastos);
                tipGasCodNew = em.merge(tipGasCodNew);
            }
            for (IngresoKardex ingresoKardexCollectionOldIngresoKardex : ingresoKardexCollectionOld) {
                if (!ingresoKardexCollectionNew.contains(ingresoKardexCollectionOldIngresoKardex)) {
                    ingresoKardexCollectionOldIngresoKardex.setGasCod(null);
                    ingresoKardexCollectionOldIngresoKardex = em.merge(ingresoKardexCollectionOldIngresoKardex);
                }
            }
            for (IngresoKardex ingresoKardexCollectionNewIngresoKardex : ingresoKardexCollectionNew) {
                if (!ingresoKardexCollectionOld.contains(ingresoKardexCollectionNewIngresoKardex)) {
                    Gastos oldGasCodOfIngresoKardexCollectionNewIngresoKardex = ingresoKardexCollectionNewIngresoKardex.getGasCod();
                    ingresoKardexCollectionNewIngresoKardex.setGasCod(gastos);
                    ingresoKardexCollectionNewIngresoKardex = em.merge(ingresoKardexCollectionNewIngresoKardex);
                    if (oldGasCodOfIngresoKardexCollectionNewIngresoKardex != null && !oldGasCodOfIngresoKardexCollectionNewIngresoKardex.equals(gastos)) {
                        oldGasCodOfIngresoKardexCollectionNewIngresoKardex.getIngresoKardexCollection().remove(ingresoKardexCollectionNewIngresoKardex);
                        oldGasCodOfIngresoKardexCollectionNewIngresoKardex = em.merge(oldGasCodOfIngresoKardexCollectionNewIngresoKardex);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = gastos.getGasCod();
                if (findGastos(id) == null) {
                    throw new NonexistentEntityException("The gastos with id " + id + " no longer exists.");
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
            Gastos gastos;
            try {
                gastos = em.getReference(Gastos.class, id);
                gastos.getGasCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The gastos with id " + id + " no longer exists.", enfe);
            }
            Empleado empDni = gastos.getEmpDni();
            if (empDni != null) {
                empDni.getGastosCollection().remove(gastos);
                empDni = em.merge(empDni);
            }
            Proveedor provRuc = gastos.getProvRuc();
            if (provRuc != null) {
                provRuc.getGastosCollection().remove(gastos);
                provRuc = em.merge(provRuc);
            }
            TipoDocumento tipDocCod = gastos.getTipDocCod();
            if (tipDocCod != null) {
                tipDocCod.getGastosCollection().remove(gastos);
                tipDocCod = em.merge(tipDocCod);
            }
            TipoGasto tipGasCod = gastos.getTipGasCod();
            if (tipGasCod != null) {
                tipGasCod.getGastosCollection().remove(gastos);
                tipGasCod = em.merge(tipGasCod);
            }
            Collection<IngresoKardex> ingresoKardexCollection = gastos.getIngresoKardexCollection();
            for (IngresoKardex ingresoKardexCollectionIngresoKardex : ingresoKardexCollection) {
                ingresoKardexCollectionIngresoKardex.setGasCod(null);
                ingresoKardexCollectionIngresoKardex = em.merge(ingresoKardexCollectionIngresoKardex);
            }
            em.remove(gastos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Gastos> findGastosEntities() {
        return findGastosEntities(true, -1, -1);
    }

    public List<Gastos> findGastosEntities(int maxResults, int firstResult) {
        return findGastosEntities(false, maxResults, firstResult);
    }

    private List<Gastos> findGastosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Gastos.class));
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

    public Gastos findGastos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Gastos.class, id);
        } finally {
            em.close();
        }
    }

    public int getGastosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Gastos> rt = cq.from(Gastos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
