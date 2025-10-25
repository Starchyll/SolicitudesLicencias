/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Persistencia.Vehiculo;
import java.util.List;

/**
 *
 * @author angel
 */
public interface IVehiculoDAO {

    void agregar(Vehiculo vehiculo);

    void actualizar(Vehiculo vehiculo);

    void eliminar(String numSerie);

    Vehiculo consultar(String numSerie);

    List<Vehiculo> consultarTodos();

    List<Vehiculo> consultarPorRFC(String RFC);
}
