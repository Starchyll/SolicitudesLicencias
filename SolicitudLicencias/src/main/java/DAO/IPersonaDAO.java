package DAO;

import Persistencia.Persona;
import Persistencia.Tramite;

import java.util.List;

import Exception.PersistenciaException;

public interface IPersonaDAO {
    void agregar(Persona persona) throws PersistenciaException;

    void actualizar(Persona persona);

    void eliminar(String rfc);

    List<Tramite> consultarPorRFC(String rfc);

    List<Tramite> consultarPorNombreLike(String nombre);

    List<Tramite> consultarPorFechaLike(String fecha);

    List<Persona> consultarTodos();

    List<Tramite> consultarHistorialTramites(String rfc);
}
