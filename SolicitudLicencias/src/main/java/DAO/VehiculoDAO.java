/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Persistencia.Vehiculo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author angel
 */
public class VehiculoDAO implements IVehiculoDAO {

    private EntityManagerFactory emf;

    public VehiculoDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void agregar(Vehiculo vehiculo) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(vehiculo);
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
    public void actualizar(Vehiculo vehiculo) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(vehiculo);
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
    public void eliminar(String numSerie) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Vehiculo vehiculo = em.find(Vehiculo.class, numSerie);
            if (vehiculo != null) {
                em.remove(vehiculo);
            }
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
    public Vehiculo consultar(String numSerie) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Vehiculo.class, numSerie);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Vehiculo> consultarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            String sql = "SELECT v FROM Vehiculo v";
            TypedQuery<Vehiculo> query = em.createQuery(sql, Vehiculo.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Vehiculo> consultarPorRFC(String RFC) {
        EntityManager em = emf.createEntityManager();
        try {
            String sql = "SELECT v FROM Vehiculo v JOIN v.persona p WHERE p.RFC = :rfc";
            TypedQuery<Vehiculo> query = em.createQuery(sql, Vehiculo.class);
            query.setParameter("rfc", RFC);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
