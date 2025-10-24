package DAO;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import Persistencia.Licencia;

public class LicenciaDAO implements ILicenciaDAO{
    private EntityManagerFactory emf;

    public LicenciaDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void agregar(Licencia licencia) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(licencia);
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
    public void actualizar(Licencia licencia) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(licencia);
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
    public void eliminar(Long id_licencia) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Licencia licencia = em.find(Licencia.class, id_licencia);
            if (licencia != null) {
                em.remove(licencia);
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
    public Licencia consultar(Long id_licencia) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Licencia.class, id_licencia);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Licencia> consultarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT l FROM Licencia l";
            TypedQuery<Licencia> query = em.createQuery(jpql, Licencia.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Licencia> consultarVigentes() {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT l FROM Licencia l WHERE l.fecha_vencimiento > :fechaActual";
            TypedQuery<Licencia> query = em.createQuery(jpql, Licencia.class);
            
            query.setParameter("fechaActual", LocalDate.now());
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
