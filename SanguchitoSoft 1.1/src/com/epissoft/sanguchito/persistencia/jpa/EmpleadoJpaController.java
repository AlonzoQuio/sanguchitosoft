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
import com.epissoft.sanguchito.persistencia.Turno;
import com.epissoft.sanguchito.persistencia.Cargo;
import com.epissoft.sanguchito.persistencia.Empleado;
import com.epissoft.sanguchito.persistencia.Gastos;
import java.util.ArrayList;
import java.util.Collection;
import com.epissoft.sanguchito.persistencia.Usuario;
import com.epissoft.sanguchito.persistencia.RegistroFalta;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.IllegalOrphanException;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.NonexistentEntityException;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Hisae Shizumaru
 */
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) throws PreexistingEntityException, Exception {
        if (empleado.getGastosCollection() == null) {
            empleado.setGastosCollection(new ArrayList<Gastos>());
        }
        if (empleado.getUsuarioCollection() == null) {
            empleado.setUsuarioCollection(new ArrayList<Usuario>());
        }
        if (empleado.getRegistroFaltaCollection() == null) {
            empleado.setRegistroFaltaCollection(new ArrayList<RegistroFalta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turno turCod = empleado.getTurCod();
            if (turCod != null) {
                turCod = em.getReference(turCod.getClass(), turCod.getTurCod());
                empleado.setTurCod(turCod);
            }
            Cargo carCod = empleado.getCarCod();
            if (carCod != null) {
                carCod = em.getReference(carCod.getClass(), carCod.getCarCod());
                empleado.setCarCod(carCod);
            }
            Collection<Gastos> attachedGastosCollection = new ArrayList<Gastos>();
            for (Gastos gastosCollectionGastosToAttach : empleado.getGastosCollection()) {
                gastosCollectionGastosToAttach = em.getReference(gastosCollectionGastosToAttach.getClass(), gastosCollectionGastosToAttach.getGasCod());
                attachedGastosCollection.add(gastosCollectionGastosToAttach);
            }
            empleado.setGastosCollection(attachedGastosCollection);
            Collection<Usuario> attachedUsuarioCollection = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionUsuarioToAttach : empleado.getUsuarioCollection()) {
                usuarioCollectionUsuarioToAttach = em.getReference(usuarioCollectionUsuarioToAttach.getClass(), usuarioCollectionUsuarioToAttach.getUsuCod());
                attachedUsuarioCollection.add(usuarioCollectionUsuarioToAttach);
            }
            empleado.setUsuarioCollection(attachedUsuarioCollection);
            Collection<RegistroFalta> attachedRegistroFaltaCollection = new ArrayList<RegistroFalta>();
            for (RegistroFalta registroFaltaCollectionRegistroFaltaToAttach : empleado.getRegistroFaltaCollection()) {
                registroFaltaCollectionRegistroFaltaToAttach = em.getReference(registroFaltaCollectionRegistroFaltaToAttach.getClass(), registroFaltaCollectionRegistroFaltaToAttach.getRegFaltaCod());
                attachedRegistroFaltaCollection.add(registroFaltaCollectionRegistroFaltaToAttach);
            }
            empleado.setRegistroFaltaCollection(attachedRegistroFaltaCollection);
            em.persist(empleado);
            if (turCod != null) {
                turCod.getEmpleadoCollection().add(empleado);
                turCod = em.merge(turCod);
            }
            if (carCod != null) {
                carCod.getEmpleadoCollection().add(empleado);
                carCod = em.merge(carCod);
            }
            for (Gastos gastosCollectionGastos : empleado.getGastosCollection()) {
                Empleado oldEmpDniOfGastosCollectionGastos = gastosCollectionGastos.getEmpDni();
                gastosCollectionGastos.setEmpDni(empleado);
                gastosCollectionGastos = em.merge(gastosCollectionGastos);
                if (oldEmpDniOfGastosCollectionGastos != null) {
                    oldEmpDniOfGastosCollectionGastos.getGastosCollection().remove(gastosCollectionGastos);
                    oldEmpDniOfGastosCollectionGastos = em.merge(oldEmpDniOfGastosCollectionGastos);
                }
            }
            for (Usuario usuarioCollectionUsuario : empleado.getUsuarioCollection()) {
                Empleado oldEmpDniOfUsuarioCollectionUsuario = usuarioCollectionUsuario.getEmpDni();
                usuarioCollectionUsuario.setEmpDni(empleado);
                usuarioCollectionUsuario = em.merge(usuarioCollectionUsuario);
                if (oldEmpDniOfUsuarioCollectionUsuario != null) {
                    oldEmpDniOfUsuarioCollectionUsuario.getUsuarioCollection().remove(usuarioCollectionUsuario);
                    oldEmpDniOfUsuarioCollectionUsuario = em.merge(oldEmpDniOfUsuarioCollectionUsuario);
                }
            }
            for (RegistroFalta registroFaltaCollectionRegistroFalta : empleado.getRegistroFaltaCollection()) {
                Empleado oldEmpDniOfRegistroFaltaCollectionRegistroFalta = registroFaltaCollectionRegistroFalta.getEmpDni();
                registroFaltaCollectionRegistroFalta.setEmpDni(empleado);
                registroFaltaCollectionRegistroFalta = em.merge(registroFaltaCollectionRegistroFalta);
                if (oldEmpDniOfRegistroFaltaCollectionRegistroFalta != null) {
                    oldEmpDniOfRegistroFaltaCollectionRegistroFalta.getRegistroFaltaCollection().remove(registroFaltaCollectionRegistroFalta);
                    oldEmpDniOfRegistroFaltaCollectionRegistroFalta = em.merge(oldEmpDniOfRegistroFaltaCollectionRegistroFalta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmpleado(empleado.getEmpDni()) != null) {
                throw new PreexistingEntityException("Empleado " + empleado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getEmpDni());
            Turno turCodOld = persistentEmpleado.getTurCod();
            Turno turCodNew = empleado.getTurCod();
            Cargo carCodOld = persistentEmpleado.getCarCod();
            Cargo carCodNew = empleado.getCarCod();
            Collection<Gastos> gastosCollectionOld = persistentEmpleado.getGastosCollection();
            Collection<Gastos> gastosCollectionNew = empleado.getGastosCollection();
            Collection<Usuario> usuarioCollectionOld = persistentEmpleado.getUsuarioCollection();
            Collection<Usuario> usuarioCollectionNew = empleado.getUsuarioCollection();
            Collection<RegistroFalta> registroFaltaCollectionOld = persistentEmpleado.getRegistroFaltaCollection();
            Collection<RegistroFalta> registroFaltaCollectionNew = empleado.getRegistroFaltaCollection();
            List<String> illegalOrphanMessages = null;
            for (Usuario usuarioCollectionOldUsuario : usuarioCollectionOld) {
                if (!usuarioCollectionNew.contains(usuarioCollectionOldUsuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuario " + usuarioCollectionOldUsuario + " since its empDni field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (turCodNew != null) {
                turCodNew = em.getReference(turCodNew.getClass(), turCodNew.getTurCod());
                empleado.setTurCod(turCodNew);
            }
            if (carCodNew != null) {
                carCodNew = em.getReference(carCodNew.getClass(), carCodNew.getCarCod());
                empleado.setCarCod(carCodNew);
            }
            Collection<Gastos> attachedGastosCollectionNew = new ArrayList<Gastos>();
            for (Gastos gastosCollectionNewGastosToAttach : gastosCollectionNew) {
                gastosCollectionNewGastosToAttach = em.getReference(gastosCollectionNewGastosToAttach.getClass(), gastosCollectionNewGastosToAttach.getGasCod());
                attachedGastosCollectionNew.add(gastosCollectionNewGastosToAttach);
            }
            gastosCollectionNew = attachedGastosCollectionNew;
            empleado.setGastosCollection(gastosCollectionNew);
            Collection<Usuario> attachedUsuarioCollectionNew = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionNewUsuarioToAttach : usuarioCollectionNew) {
                usuarioCollectionNewUsuarioToAttach = em.getReference(usuarioCollectionNewUsuarioToAttach.getClass(), usuarioCollectionNewUsuarioToAttach.getUsuCod());
                attachedUsuarioCollectionNew.add(usuarioCollectionNewUsuarioToAttach);
            }
            usuarioCollectionNew = attachedUsuarioCollectionNew;
            empleado.setUsuarioCollection(usuarioCollectionNew);
            Collection<RegistroFalta> attachedRegistroFaltaCollectionNew = new ArrayList<RegistroFalta>();
            for (RegistroFalta registroFaltaCollectionNewRegistroFaltaToAttach : registroFaltaCollectionNew) {
                registroFaltaCollectionNewRegistroFaltaToAttach = em.getReference(registroFaltaCollectionNewRegistroFaltaToAttach.getClass(), registroFaltaCollectionNewRegistroFaltaToAttach.getRegFaltaCod());
                attachedRegistroFaltaCollectionNew.add(registroFaltaCollectionNewRegistroFaltaToAttach);
            }
            registroFaltaCollectionNew = attachedRegistroFaltaCollectionNew;
            empleado.setRegistroFaltaCollection(registroFaltaCollectionNew);
            empleado = em.merge(empleado);
            if (turCodOld != null && !turCodOld.equals(turCodNew)) {
                turCodOld.getEmpleadoCollection().remove(empleado);
                turCodOld = em.merge(turCodOld);
            }
            if (turCodNew != null && !turCodNew.equals(turCodOld)) {
                turCodNew.getEmpleadoCollection().add(empleado);
                turCodNew = em.merge(turCodNew);
            }
            if (carCodOld != null && !carCodOld.equals(carCodNew)) {
                carCodOld.getEmpleadoCollection().remove(empleado);
                carCodOld = em.merge(carCodOld);
            }
            if (carCodNew != null && !carCodNew.equals(carCodOld)) {
                carCodNew.getEmpleadoCollection().add(empleado);
                carCodNew = em.merge(carCodNew);
            }
            for (Gastos gastosCollectionOldGastos : gastosCollectionOld) {
                if (!gastosCollectionNew.contains(gastosCollectionOldGastos)) {
                    gastosCollectionOldGastos.setEmpDni(null);
                    gastosCollectionOldGastos = em.merge(gastosCollectionOldGastos);
                }
            }
            for (Gastos gastosCollectionNewGastos : gastosCollectionNew) {
                if (!gastosCollectionOld.contains(gastosCollectionNewGastos)) {
                    Empleado oldEmpDniOfGastosCollectionNewGastos = gastosCollectionNewGastos.getEmpDni();
                    gastosCollectionNewGastos.setEmpDni(empleado);
                    gastosCollectionNewGastos = em.merge(gastosCollectionNewGastos);
                    if (oldEmpDniOfGastosCollectionNewGastos != null && !oldEmpDniOfGastosCollectionNewGastos.equals(empleado)) {
                        oldEmpDniOfGastosCollectionNewGastos.getGastosCollection().remove(gastosCollectionNewGastos);
                        oldEmpDniOfGastosCollectionNewGastos = em.merge(oldEmpDniOfGastosCollectionNewGastos);
                    }
                }
            }
            for (Usuario usuarioCollectionNewUsuario : usuarioCollectionNew) {
                if (!usuarioCollectionOld.contains(usuarioCollectionNewUsuario)) {
                    Empleado oldEmpDniOfUsuarioCollectionNewUsuario = usuarioCollectionNewUsuario.getEmpDni();
                    usuarioCollectionNewUsuario.setEmpDni(empleado);
                    usuarioCollectionNewUsuario = em.merge(usuarioCollectionNewUsuario);
                    if (oldEmpDniOfUsuarioCollectionNewUsuario != null && !oldEmpDniOfUsuarioCollectionNewUsuario.equals(empleado)) {
                        oldEmpDniOfUsuarioCollectionNewUsuario.getUsuarioCollection().remove(usuarioCollectionNewUsuario);
                        oldEmpDniOfUsuarioCollectionNewUsuario = em.merge(oldEmpDniOfUsuarioCollectionNewUsuario);
                    }
                }
            }
            for (RegistroFalta registroFaltaCollectionOldRegistroFalta : registroFaltaCollectionOld) {
                if (!registroFaltaCollectionNew.contains(registroFaltaCollectionOldRegistroFalta)) {
                    registroFaltaCollectionOldRegistroFalta.setEmpDni(null);
                    registroFaltaCollectionOldRegistroFalta = em.merge(registroFaltaCollectionOldRegistroFalta);
                }
            }
            for (RegistroFalta registroFaltaCollectionNewRegistroFalta : registroFaltaCollectionNew) {
                if (!registroFaltaCollectionOld.contains(registroFaltaCollectionNewRegistroFalta)) {
                    Empleado oldEmpDniOfRegistroFaltaCollectionNewRegistroFalta = registroFaltaCollectionNewRegistroFalta.getEmpDni();
                    registroFaltaCollectionNewRegistroFalta.setEmpDni(empleado);
                    registroFaltaCollectionNewRegistroFalta = em.merge(registroFaltaCollectionNewRegistroFalta);
                    if (oldEmpDniOfRegistroFaltaCollectionNewRegistroFalta != null && !oldEmpDniOfRegistroFaltaCollectionNewRegistroFalta.equals(empleado)) {
                        oldEmpDniOfRegistroFaltaCollectionNewRegistroFalta.getRegistroFaltaCollection().remove(registroFaltaCollectionNewRegistroFalta);
                        oldEmpDniOfRegistroFaltaCollectionNewRegistroFalta = em.merge(oldEmpDniOfRegistroFaltaCollectionNewRegistroFalta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empleado.getEmpDni();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
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
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getEmpDni();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Usuario> usuarioCollectionOrphanCheck = empleado.getUsuarioCollection();
            for (Usuario usuarioCollectionOrphanCheckUsuario : usuarioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Usuario " + usuarioCollectionOrphanCheckUsuario + " in its usuarioCollection field has a non-nullable empDni field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Turno turCod = empleado.getTurCod();
            if (turCod != null) {
                turCod.getEmpleadoCollection().remove(empleado);
                turCod = em.merge(turCod);
            }
            Cargo carCod = empleado.getCarCod();
            if (carCod != null) {
                carCod.getEmpleadoCollection().remove(empleado);
                carCod = em.merge(carCod);
            }
            Collection<Gastos> gastosCollection = empleado.getGastosCollection();
            for (Gastos gastosCollectionGastos : gastosCollection) {
                gastosCollectionGastos.setEmpDni(null);
                gastosCollectionGastos = em.merge(gastosCollectionGastos);
            }
            Collection<RegistroFalta> registroFaltaCollection = empleado.getRegistroFaltaCollection();
            for (RegistroFalta registroFaltaCollectionRegistroFalta : registroFaltaCollection) {
                registroFaltaCollectionRegistroFalta.setEmpDni(null);
                registroFaltaCollectionRegistroFalta = em.merge(registroFaltaCollectionRegistroFalta);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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

    public Empleado findEmpleado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }
    public List<Empleado> findEmpleado(String apellido){
        List<Empleado> list = getEntityManager().createNamedQuery("Empleado.findByEmpApePat").setParameter("empApePat", apellido).getResultList();
        return list;       
    }
    
    public List<Empleado> findEmpleadoCargo(int cargo){
        List<Empleado> list = getEntityManager().createNamedQuery("Empleado.findByCarCod").setParameter("carCod", new Cargo(cargo)).getResultList();
        return list;       
    }
    
    public List<Empleado> findEmpleadoTurno(int turno){
        List<Empleado> list = getEntityManager().createNamedQuery("Empleado.findByTurCod").setParameter("turCod", new Turno(turno)).getResultList();
        return list;       
    }
    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
