/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Persistencia.Placa;
import Persistencia.Vehiculo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author angel
 */
public class PlacaDAO implements IPlacaDAO {

    private EntityManagerFactory emf;

    public PlacaDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void agregar(Placa placa) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(placa);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Placa placa) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(placa);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Placa consultar(String numPlaca) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Placa.class, numPlaca);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Placa> consultarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            String sql = "SELECT p FROM Placa p";
            TypedQuery<Placa> query = em.createQuery(sql, Placa.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Placa consultarPlacaActiva(Vehiculo vehiculo) {
        EntityManager em = emf.createEntityManager();
        try {
            String sql = "SELECT p FROM Placa p WHERE p.vehiculo = :vehiculo AND p.activa = true";
            TypedQuery<Placa> query = em.createQuery(sql, Placa.class);
            query.setParameter("vehiculo", vehiculo);

            return query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Placa> consultarHistorial(Vehiculo vehiculo) {
        EntityManager em = emf.createEntityManager();
        try {
            String sql = "SELECT p FROM Placa p WHERE p.vehiculo = :vehiculo ORDER BY p.fechaEmision DESC";
            TypedQuery<Placa> query = em.createQuery(sql, Placa.class);
            query.setParameter("vehiculo", vehiculo);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
