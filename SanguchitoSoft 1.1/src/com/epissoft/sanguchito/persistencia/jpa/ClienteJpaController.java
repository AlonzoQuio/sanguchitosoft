/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epissoft.sanguchito.persistencia.jpa;

import com.epissoft.sanguchito.persistencia.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.epissoft.sanguchito.persistencia.TipoCliente;
import com.epissoft.sanguchito.persistencia.Facturacion;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.IllegalOrphanException;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.NonexistentEntityException;
import com.epissoft.sanguchito.persistencia.jpa.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Hisae Shizumaru
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) throws PreexistingEntityException, Exception {
        if (cliente.getFacturacionCollection() == null) {
            cliente.setFacturacionCollection(new ArrayList<Facturacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoCliente tipClieCod = cliente.getTipClieCod();
            if (tipClieCod != null) {
                tipClieCod = em.getReference(tipClieCod.getClass(), tipClieCod.getTipClieCod());
                cliente.setTipClieCod(tipClieCod);
            }
            Collection<Facturacion> attachedFacturacionCollection = new ArrayList<Facturacion>();
            for (Facturacion facturacionCollectionFacturacionToAttach : cliente.getFacturacionCollection()) {
                facturacionCollectionFacturacionToAttach = em.getReference(facturacionCollectionFacturacionToAttach.getClass(), facturacionCollectionFacturacionToAttach.getFacCod());
                attachedFacturacionCollection.add(facturacionCollectionFacturacionToAttach);
            }
            cliente.setFacturacionCollection(attachedFacturacionCollection);
            em.persist(cliente);
            if (tipClieCod != null) {
                tipClieCod.getClienteCollection().add(cliente);
                tipClieCod = em.merge(tipClieCod);
            }
            for (Facturacion facturacionCollectionFacturacion : cliente.getFacturacionCollection()) {
                Cliente oldClieDniOfFacturacionCollectionFacturacion = facturacionCollectionFacturacion.getClieDni();
                facturacionCollectionFacturacion.setClieDni(cliente);
                facturacionCollectionFacturacion = em.merge(facturacionCollectionFacturacion);
                if (oldClieDniOfFacturacionCollectionFacturacion != null) {
                    oldClieDniOfFacturacionCollectionFacturacion.getFacturacionCollection().remove(facturacionCollectionFacturacion);
                    oldClieDniOfFacturacionCollectionFacturacion = em.merge(oldClieDniOfFacturacionCollectionFacturacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCliente(cliente.getClieDni()) != null) {
                throw new PreexistingEntityException("Cliente " + cliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getClieDni());
            TipoCliente tipClieCodOld = persistentCliente.getTipClieCod();
            TipoCliente tipClieCodNew = cliente.getTipClieCod();
            Collection<Facturacion> facturacionCollectionOld = persistentCliente.getFacturacionCollection();
            Collection<Facturacion> facturacionCollectionNew = cliente.getFacturacionCollection();
            List<String> illegalOrphanMessages = null;
            for (Facturacion facturacionCollectionOldFacturacion : facturacionCollectionOld) {
                if (!facturacionCollectionNew.contains(facturacionCollectionOldFacturacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Facturacion " + facturacionCollectionOldFacturacion + " since its clieDni field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tipClieCodNew != null) {
                tipClieCodNew = em.getReference(tipClieCodNew.getClass(), tipClieCodNew.getTipClieCod());
                cliente.setTipClieCod(tipClieCodNew);
            }
            Collection<Facturacion> attachedFacturacionCollectionNew = new ArrayList<Facturacion>();
            for (Facturacion facturacionCollectionNewFacturacionToAttach : facturacionCollectionNew) {
                facturacionCollectionNewFacturacionToAttach = em.getReference(facturacionCollectionNewFacturacionToAttach.getClass(), facturacionCollectionNewFacturacionToAttach.getFacCod());
                attachedFacturacionCollectionNew.add(facturacionCollectionNewFacturacionToAttach);
            }
            facturacionCollectionNew = attachedFacturacionCollectionNew;
            cliente.setFacturacionCollection(facturacionCollectionNew);
            cliente = em.merge(cliente);
            if (tipClieCodOld != null && !tipClieCodOld.equals(tipClieCodNew)) {
                tipClieCodOld.getClienteCollection().remove(cliente);
                tipClieCodOld = em.merge(tipClieCodOld);
            }
            if (tipClieCodNew != null && !tipClieCodNew.equals(tipClieCodOld)) {
                tipClieCodNew.getClienteCollection().add(cliente);
                tipClieCodNew = em.merge(tipClieCodNew);
            }
            for (Facturacion facturacionCollectionNewFacturacion : facturacionCollectionNew) {
                if (!facturacionCollectionOld.contains(facturacionCollectionNewFacturacion)) {
                    Cliente oldClieDniOfFacturacionCollectionNewFacturacion = facturacionCollectionNewFacturacion.getClieDni();
                    facturacionCollectionNewFacturacion.setClieDni(cliente);
                    facturacionCollectionNewFacturacion = em.merge(facturacionCollectionNewFacturacion);
                    if (oldClieDniOfFacturacionCollectionNewFacturacion != null && !oldClieDniOfFacturacionCollectionNewFacturacion.equals(cliente)) {
                        oldClieDniOfFacturacionCollectionNewFacturacion.getFacturacionCollection().remove(facturacionCollectionNewFacturacion);
                        oldClieDniOfFacturacionCollectionNewFacturacion = em.merge(oldClieDniOfFacturacionCollectionNewFacturacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getClieDni();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getClieDni();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Facturacion> facturacionCollectionOrphanCheck = cliente.getFacturacionCollection();
            for (Facturacion facturacionCollectionOrphanCheckFacturacion : facturacionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Facturacion " + facturacionCollectionOrphanCheckFacturacion + " in its facturacionCollection field has a non-nullable clieDni field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            TipoCliente tipClieCod = cliente.getTipClieCod();
            if (tipClieCod != null) {
                tipClieCod.getClienteCollection().remove(cliente);
                tipClieCod = em.merge(tipClieCod);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
