package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import Exception.PersistenciaException;
import Persistencia.Persona;
import Persistencia.Tramite;

public class PersonaDAO implements IPersonaDAO{
    private EntityManagerFactory emf;

    public PersonaDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void agregar(Persona persona) throws PersistenciaException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(persona); 
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                throw new PersistenciaException("error con la conexion");
            }
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Persona persona) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(persona); 
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
                
            }
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(String rfc) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Persona persona = em.find(Persona.class, rfc);
            if (persona != null) {
                em.remove(persona);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }

    @Override
    public List<Tramite> consultarPorRFC(String rfc) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT t FROM Tramite t WHERE t.persona.RFC = :rfc";

            TypedQuery<Tramite> query = em.createQuery(jpql, Tramite.class);

            query.setParameter("rfc", rfc);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Tramite> consultarPorNombreLike(String nombre) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT t FROM Tramite t WHERE t.persona.nombre LIKE :nombre";
            
            TypedQuery<Tramite> query = em.createQuery(jpql, Tramite.class);
            
            query.setParameter("nombre", nombre);
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Tramite> consultarPorFechaLike(String patronFecha) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT t FROM Tramite t WHERE str(t.fecha_realizacion) LIKE :patron";
            
            TypedQuery<Tramite> query = em.createQuery(jpql, Tramite.class);
            
            query.setParameter("patron", patronFecha);
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Persona> consultarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT p FROM Persona p";
            TypedQuery<Persona> query = em.createQuery(jpql, Persona.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Tramite> consultarHistorialTramites(String rfc) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT t FROM Tramite t WHERE t.persona.RFC = :rfc";
            
            TypedQuery<Tramite> query = em.createQuery(jpql, Tramite.class);
            query.setParameter("rfc", rfc);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}