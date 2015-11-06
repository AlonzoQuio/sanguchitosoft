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
import com.epissoft.sanguchito.persistencia.Tipousuario;
import com.epissoft.sanguchito.persistencia.Empleado;
import com.epissoft.sanguchito.persistencia.AperturaCaja;
import java.util.ArrayList;
import java.util.Collection;
import com.epissoft.sanguchito.persistencia.Facturacion;
import com.epissoft.sanguchito.persistencia.Usuario;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Hisae Shizumaru
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getAperturaCajaCollection() == null) {
            usuario.setAperturaCajaCollection(new ArrayList<AperturaCaja>());
        }
        if (usuario.getFacturacionCollection() == null) {
            usuario.setFacturacionCollection(new ArrayList<Facturacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipousuario tipUsuCod = usuario.getTipUsuCod();
            if (tipUsuCod != null) {
                tipUsuCod = em.getReference(tipUsuCod.getClass(), tipUsuCod.getTipUsuCod());
                usuario.setTipUsuCod(tipUsuCod);
            }
            Empleado empDni = usuario.getEmpDni();
            if (empDni != null) {
                empDni = em.getReference(empDni.getClass(), empDni.getEmpDni());
                usuario.setEmpDni(empDni);
            }
            Collection<AperturaCaja> attachedAperturaCajaCollection = new ArrayList<AperturaCaja>();
            for (AperturaCaja aperturaCajaCollectionAperturaCajaToAttach : usuario.getAperturaCajaCollection()) {
                aperturaCajaCollectionAperturaCajaToAttach = em.getReference(aperturaCajaCollectionAperturaCajaToAttach.getClass(), aperturaCajaCollectionAperturaCajaToAttach.getAperCajCod());
                attachedAperturaCajaCollection.add(aperturaCajaCollectionAperturaCajaToAttach);
            }
            usuario.setAperturaCajaCollection(attachedAperturaCajaCollection);
            Collection<Facturacion> attachedFacturacionCollection = new ArrayList<Facturacion>();
            for (Facturacion facturacionCollectionFacturacionToAttach : usuario.getFacturacionCollection()) {
                facturacionCollectionFacturacionToAttach = em.getReference(facturacionCollectionFacturacionToAttach.getClass(), facturacionCollectionFacturacionToAttach.getFacCod());
                attachedFacturacionCollection.add(facturacionCollectionFacturacionToAttach);
            }
            usuario.setFacturacionCollection(attachedFacturacionCollection);
            em.persist(usuario);
            if (tipUsuCod != null) {
                tipUsuCod.getUsuarioCollection().add(usuario);
                tipUsuCod = em.merge(tipUsuCod);
            }
            if (empDni != null) {
                empDni.getUsuarioCollection().add(usuario);
                empDni = em.merge(empDni);
            }
            for (AperturaCaja aperturaCajaCollectionAperturaCaja : usuario.getAperturaCajaCollection()) {
                Usuario oldUsuCodOfAperturaCajaCollectionAperturaCaja = aperturaCajaCollectionAperturaCaja.getUsuCod();
                aperturaCajaCollectionAperturaCaja.setUsuCod(usuario);
                aperturaCajaCollectionAperturaCaja = em.merge(aperturaCajaCollectionAperturaCaja);
                if (oldUsuCodOfAperturaCajaCollectionAperturaCaja != null) {
                    oldUsuCodOfAperturaCajaCollectionAperturaCaja.getAperturaCajaCollection().remove(aperturaCajaCollectionAperturaCaja);
                    oldUsuCodOfAperturaCajaCollectionAperturaCaja = em.merge(oldUsuCodOfAperturaCajaCollectionAperturaCaja);
                }
            }
            for (Facturacion facturacionCollectionFacturacion : usuario.getFacturacionCollection()) {
                Usuario oldUsuCodOfFacturacionCollectionFacturacion = facturacionCollectionFacturacion.getUsuCod();
                facturacionCollectionFacturacion.setUsuCod(usuario);
                facturacionCollectionFacturacion = em.merge(facturacionCollectionFacturacion);
                if (oldUsuCodOfFacturacionCollectionFacturacion != null) {
                    oldUsuCodOfFacturacionCollectionFacturacion.getFacturacionCollection().remove(facturacionCollectionFacturacion);
                    oldUsuCodOfFacturacionCollectionFacturacion = em.merge(oldUsuCodOfFacturacionCollectionFacturacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getUsuCod());
            Tipousuario tipUsuCodOld = persistentUsuario.getTipUsuCod();
            Tipousuario tipUsuCodNew = usuario.getTipUsuCod();
            Empleado empDniOld = persistentUsuario.getEmpDni();
            Empleado empDniNew = usuario.getEmpDni();
            Collection<AperturaCaja> aperturaCajaCollectionOld = persistentUsuario.getAperturaCajaCollection();
            Collection<AperturaCaja> aperturaCajaCollectionNew = usuario.getAperturaCajaCollection();
            Collection<Facturacion> facturacionCollectionOld = persistentUsuario.getFacturacionCollection();
            Collection<Facturacion> facturacionCollectionNew = usuario.getFacturacionCollection();
            if (tipUsuCodNew != null) {
                tipUsuCodNew = em.getReference(tipUsuCodNew.getClass(), tipUsuCodNew.getTipUsuCod());
                usuario.setTipUsuCod(tipUsuCodNew);
            }
            if (empDniNew != null) {
                empDniNew = em.getReference(empDniNew.getClass(), empDniNew.getEmpDni());
                usuario.setEmpDni(empDniNew);
            }
            Collection<AperturaCaja> attachedAperturaCajaCollectionNew = new ArrayList<AperturaCaja>();
            for (AperturaCaja aperturaCajaCollectionNewAperturaCajaToAttach : aperturaCajaCollectionNew) {
                aperturaCajaCollectionNewAperturaCajaToAttach = em.getReference(aperturaCajaCollectionNewAperturaCajaToAttach.getClass(), aperturaCajaCollectionNewAperturaCajaToAttach.getAperCajCod());
                attachedAperturaCajaCollectionNew.add(aperturaCajaCollectionNewAperturaCajaToAttach);
            }
            aperturaCajaCollectionNew = attachedAperturaCajaCollectionNew;
            usuario.setAperturaCajaCollection(aperturaCajaCollectionNew);
            Collection<Facturacion> attachedFacturacionCollectionNew = new ArrayList<Facturacion>();
            for (Facturacion facturacionCollectionNewFacturacionToAttach : facturacionCollectionNew) {
                facturacionCollectionNewFacturacionToAttach = em.getReference(facturacionCollectionNewFacturacionToAttach.getClass(), facturacionCollectionNewFacturacionToAttach.getFacCod());
                attachedFacturacionCollectionNew.add(facturacionCollectionNewFacturacionToAttach);
            }
            facturacionCollectionNew = attachedFacturacionCollectionNew;
            usuario.setFacturacionCollection(facturacionCollectionNew);
            usuario = em.merge(usuario);
            if (tipUsuCodOld != null && !tipUsuCodOld.equals(tipUsuCodNew)) {
                tipUsuCodOld.getUsuarioCollection().remove(usuario);
                tipUsuCodOld = em.merge(tipUsuCodOld);
            }
            if (tipUsuCodNew != null && !tipUsuCodNew.equals(tipUsuCodOld)) {
                tipUsuCodNew.getUsuarioCollection().add(usuario);
                tipUsuCodNew = em.merge(tipUsuCodNew);
            }
            if (empDniOld != null && !empDniOld.equals(empDniNew)) {
                empDniOld.getUsuarioCollection().remove(usuario);
                empDniOld = em.merge(empDniOld);
            }
            if (empDniNew != null && !empDniNew.equals(empDniOld)) {
                empDniNew.getUsuarioCollection().add(usuario);
                empDniNew = em.merge(empDniNew);
            }
            for (AperturaCaja aperturaCajaCollectionOldAperturaCaja : aperturaCajaCollectionOld) {
                if (!aperturaCajaCollectionNew.contains(aperturaCajaCollectionOldAperturaCaja)) {
                    aperturaCajaCollectionOldAperturaCaja.setUsuCod(null);
                    aperturaCajaCollectionOldAperturaCaja = em.merge(aperturaCajaCollectionOldAperturaCaja);
                }
            }
            for (AperturaCaja aperturaCajaCollectionNewAperturaCaja : aperturaCajaCollectionNew) {
                if (!aperturaCajaCollectionOld.contains(aperturaCajaCollectionNewAperturaCaja)) {
                    Usuario oldUsuCodOfAperturaCajaCollectionNewAperturaCaja = aperturaCajaCollectionNewAperturaCaja.getUsuCod();
                    aperturaCajaCollectionNewAperturaCaja.setUsuCod(usuario);
                    aperturaCajaCollectionNewAperturaCaja = em.merge(aperturaCajaCollectionNewAperturaCaja);
                    if (oldUsuCodOfAperturaCajaCollectionNewAperturaCaja != null && !oldUsuCodOfAperturaCajaCollectionNewAperturaCaja.equals(usuario)) {
                        oldUsuCodOfAperturaCajaCollectionNewAperturaCaja.getAperturaCajaCollection().remove(aperturaCajaCollectionNewAperturaCaja);
                        oldUsuCodOfAperturaCajaCollectionNewAperturaCaja = em.merge(oldUsuCodOfAperturaCajaCollectionNewAperturaCaja);
                    }
                }
            }
            for (Facturacion facturacionCollectionOldFacturacion : facturacionCollectionOld) {
                if (!facturacionCollectionNew.contains(facturacionCollectionOldFacturacion)) {
                    facturacionCollectionOldFacturacion.setUsuCod(null);
                    facturacionCollectionOldFacturacion = em.merge(facturacionCollectionOldFacturacion);
                }
            }
            for (Facturacion facturacionCollectionNewFacturacion : facturacionCollectionNew) {
                if (!facturacionCollectionOld.contains(facturacionCollectionNewFacturacion)) {
                    Usuario oldUsuCodOfFacturacionCollectionNewFacturacion = facturacionCollectionNewFacturacion.getUsuCod();
                    facturacionCollectionNewFacturacion.setUsuCod(usuario);
                    facturacionCollectionNewFacturacion = em.merge(facturacionCollectionNewFacturacion);
                    if (oldUsuCodOfFacturacionCollectionNewFacturacion != null && !oldUsuCodOfFacturacionCollectionNewFacturacion.equals(usuario)) {
                        oldUsuCodOfFacturacionCollectionNewFacturacion.getFacturacionCollection().remove(facturacionCollectionNewFacturacion);
                        oldUsuCodOfFacturacionCollectionNewFacturacion = em.merge(oldUsuCodOfFacturacionCollectionNewFacturacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getUsuCod();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getUsuCod();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Tipousuario tipUsuCod = usuario.getTipUsuCod();
            if (tipUsuCod != null) {
                tipUsuCod.getUsuarioCollection().remove(usuario);
                tipUsuCod = em.merge(tipUsuCod);
            }
            Empleado empDni = usuario.getEmpDni();
            if (empDni != null) {
                empDni.getUsuarioCollection().remove(usuario);
                empDni = em.merge(empDni);
            }
            Collection<AperturaCaja> aperturaCajaCollection = usuario.getAperturaCajaCollection();
            for (AperturaCaja aperturaCajaCollectionAperturaCaja : aperturaCajaCollection) {
                aperturaCajaCollectionAperturaCaja.setUsuCod(null);
                aperturaCajaCollectionAperturaCaja = em.merge(aperturaCajaCollectionAperturaCaja);
            }
            Collection<Facturacion> facturacionCollection = usuario.getFacturacionCollection();
            for (Facturacion facturacionCollectionFacturacion : facturacionCollection) {
                facturacionCollectionFacturacion.setUsuCod(null);
                facturacionCollectionFacturacion = em.merge(facturacionCollectionFacturacion);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }
    public Usuario findUsuario(String nombre){
        List list = getEntityManager().createNamedQuery("Usuario.findByUsuNomUsu").setParameter("usuNomUsu", nombre).getResultList();
        if(list.isEmpty()){
            return null;
        }else{
            return (Usuario)list.get(0);
        }
    }
    public List<Usuario> findUsuarioDni(int dni){
        List list = getEntityManager().createNamedQuery("Usuario.findByDNI").setParameter("empDni", new Empleado(dni)).getResultList();
        if(list.isEmpty()){
            return null;
        }else{
            return (List<Usuario>)list;
        }
    }
    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
