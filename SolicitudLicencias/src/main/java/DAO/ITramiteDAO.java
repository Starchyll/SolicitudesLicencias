/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Persistencia.Tramite;
import java.util.List;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author angel
 */
public interface ITramiteDAO {

   /*
    * Agrega un nuevo registro de trámite (Licencia o placa)
    */
    void agregar(Tramite tramite);

   /*
    * Consulta tramite por ID
    */
    Tramite consultar(Long id_tramite);

   /*
    * Consulta todos los trámites existentes
    */
    List<Tramite> consultarTodos();

    /*
     * Consulta trámites por criterio
     * Permite filtrar por periodo de tiempo, tipo de trámite y por nombre de la persona
     */
    List<Tramite> consultarPorCriterio(DateTime fechaInicio, DateTime fechaFin, String tipoTramite, String nombrePersona);

}
