/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Persistencia.Tramite;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import Enum.TipoTramite;
import java.util.Date;

/**
 *
 * @author angel
 */
public class TramiteDAO implements ITramiteDAO{

    private EntityManagerFactory emf;

    public TramiteDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    @Override
    public void agregar(Tramite tramite) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(tramite);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }finally{
            em.close();
        }
    }

    @Override
    public Tramite consultar(Long id_tramite) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Tramite.class, id_tramite);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Tramite> consultarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            String sql = "SELECT t FROM Tramite t";
            TypedQuery<Tramite> query = em.createQuery(sql, Tramite.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Tramite> consultarPorCriterio(Date fechaInicio, Date fechaFin, String tipoTramite, String nombrePersona) {
        EntityManager em = emf.createEntityManager();
        try {
            StringBuilder sql = new StringBuilder("SELECT t FROM Tramite t JOIN t.persona p WHERE t.fechaRealizacion BETWEEN :fechaInicio AND :fechaFin");
            
            if (tipoTramite != null && !tipoTramite.isEmpty()) {
                sql.append("AND t.tipoTramite = :tipoTramite");
            }
            
            if (nombrePersona != null && !nombrePersona.isEmpty()) {
                sql.append("AND t.tipoTramite = :tipoTramite");
            }
            
            TypedQuery<Tramite> query = em.createQuery(sql.toString(), Tramite.class).setParameter("fechaInicio", fechaInicio).setParameter("fechaFin", fechaFin);
            
            if (tipoTramite != null && !tipoTramite.isEmpty()) {
                TipoTramite enumValue = TipoTramite.valueOf(tipoTramite);
                query.setParameter("tipoTramite", enumValue);
            }
            
            if (nombrePersona != null && !nombrePersona.trim().isEmpty()) {
                query.setParameter("nombrePersona", "%" + nombrePersona + "%");
            }
            
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
}
