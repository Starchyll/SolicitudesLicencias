package DAO;

import Persistencia.Persona;
import java.util.List;

import Exception.PersistenciaException;

public interface IPersonaDAO {
    void agregar(Persona persona) throws PersistenciaException;

    void actualizar(Persona persona);

    void eliminar(String rfc);

    Persona consultarPorRFC(String rfc);

    List<Persona> consultarPorNombreLike(String nombre);

    List<Persona> consultarPorFechaLike(String fecha);

    List<Persona> consultarTodos();
}
