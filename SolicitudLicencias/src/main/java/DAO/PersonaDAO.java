package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import Exception.PersistenciaException;
import Persistencia.Persona;

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
    public Persona consultarPorRFC(String rfc) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Persona.class, rfc);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Persona> consultarPorNombreLike(String nombre) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT p FROM Persona p WHERE p.nombre LIKE :nombre";
            
            TypedQuery<Persona> query = em.createQuery(jpql, Persona.class);

            query.setParameter("nombre", nombre);
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Persona> consultarPorFechaLike(String patronFecha) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT p FROM Persona p WHERE str(p.fecha_nacimiento) LIKE :patron";
            
            TypedQuery<Persona> query = em.createQuery(jpql, Persona.class);
            
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
}