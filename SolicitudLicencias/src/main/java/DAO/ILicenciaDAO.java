package DAO;

import java.util.List;

import Persistencia.Licencia;

public interface ILicenciaDAO {
    void agregar(Licencia licencia);

    void actualizar(Licencia licencia);

    void eliminar(Long id_licencia);

    Licencia consultar(Long id_licencia);

    List<Licencia> consultarTodos();

    List<Licencia> consultarVigentes();
}
