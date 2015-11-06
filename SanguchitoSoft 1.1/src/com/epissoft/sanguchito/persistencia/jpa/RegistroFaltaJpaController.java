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
import com.epissoft.sanguchito.persistencia.RegistroFalta;
import com.epissoft.sanguchito.persistencia.TipoFalta;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.NonexistentEntityException;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Hisae Shizumaru
 */
public class RegistroFaltaJpaController implements Serializable {

    public RegistroFaltaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RegistroFalta registroFalta) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empDni = registroFalta.getEmpDni();
            if (empDni != null) {
                empDni = em.getReference(empDni.getClass(), empDni.getEmpDni());
                registroFalta.setEmpDni(empDni);
            }
            TipoFalta tipFalCod = registroFalta.getTipFalCod();
            if (tipFalCod != null) {
                tipFalCod = em.getReference(tipFalCod.getClass(), tipFalCod.getTipFalCod());
                registroFalta.setTipFalCod(tipFalCod);
            }
            em.persist(registroFalta);
            if (empDni != null) {
                empDni.getRegistroFaltaCollection().add(registroFalta);
                empDni = em.merge(empDni);
            }
            if (tipFalCod != null) {
                tipFalCod.getRegistroFaltaCollection().add(registroFalta);
                tipFalCod = em.merge(tipFalCod);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegistroFalta(registroFalta.getRegFaltaCod()) != null) {
                throw new PreexistingEntityException("RegistroFalta " + registroFalta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RegistroFalta registroFalta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RegistroFalta persistentRegistroFalta = em.find(RegistroFalta.class, registroFalta.getRegFaltaCod());
            Empleado empDniOld = persistentRegistroFalta.getEmpDni();
            Empleado empDniNew = registroFalta.getEmpDni();
            TipoFalta tipFalCodOld = persistentRegistroFalta.getTipFalCod();
            TipoFalta tipFalCodNew = registroFalta.getTipFalCod();
            if (empDniNew != null) {
                empDniNew = em.getReference(empDniNew.getClass(), empDniNew.getEmpDni());
                registroFalta.setEmpDni(empDniNew);
            }
            if (tipFalCodNew != null) {
                tipFalCodNew = em.getReference(tipFalCodNew.getClass(), tipFalCodNew.getTipFalCod());
                registroFalta.setTipFalCod(tipFalCodNew);
            }
            registroFalta = em.merge(registroFalta);
            if (empDniOld != null && !empDniOld.equals(empDniNew)) {
                empDniOld.getRegistroFaltaCollection().remove(registroFalta);
                empDniOld = em.merge(empDniOld);
            }
            if (empDniNew != null && !empDniNew.equals(empDniOld)) {
                empDniNew.getRegistroFaltaCollection().add(registroFalta);
                empDniNew = em.merge(empDniNew);
            }
            if (tipFalCodOld != null && !tipFalCodOld.equals(tipFalCodNew)) {
                tipFalCodOld.getRegistroFaltaCollection().remove(registroFalta);
                tipFalCodOld = em.merge(tipFalCodOld);
            }
            if (tipFalCodNew != null && !tipFalCodNew.equals(tipFalCodOld)) {
                tipFalCodNew.getRegistroFaltaCollection().add(registroFalta);
                tipFalCodNew = em.merge(tipFalCodNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = registroFalta.getRegFaltaCod();
                if (findRegistroFalta(id) == null) {
                    throw new NonexistentEntityException("The registroFalta with id " + id + " no longer exists.");
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
            RegistroFalta registroFalta;
            try {
                registroFalta = em.getReference(RegistroFalta.class, id);
                registroFalta.getRegFaltaCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroFalta with id " + id + " no longer exists.", enfe);
            }
            Empleado empDni = registroFalta.getEmpDni();
            if (empDni != null) {
                empDni.getRegistroFaltaCollection().remove(registroFalta);
                empDni = em.merge(empDni);
            }
            TipoFalta tipFalCod = registroFalta.getTipFalCod();
            if (tipFalCod != null) {
                tipFalCod.getRegistroFaltaCollection().remove(registroFalta);
                tipFalCod = em.merge(tipFalCod);
            }
            em.remove(registroFalta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RegistroFalta> findRegistroFaltaEntities() {
        return findRegistroFaltaEntities(true, -1, -1);
    }

    public List<RegistroFalta> findRegistroFaltaEntities(int maxResults, int firstResult) {
        return findRegistroFaltaEntities(false, maxResults, firstResult);
    }

    private List<RegistroFalta> findRegistroFaltaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RegistroFalta.class));
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

    public RegistroFalta findRegistroFalta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RegistroFalta.class, id);
        } finally {
            em.close();
        }
    }
    public List<RegistroFalta> findRegistroFalta(int dni){
        List<RegistroFalta> list = getEntityManager().createNamedQuery("RegistroFalta.findByEmpDni").setParameter("empDni", dni).getResultList();
        return list;
    }
    public int getRegistroFaltaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RegistroFalta> rt = cq.from(RegistroFalta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
