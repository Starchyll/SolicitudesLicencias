package DAO;

import Persistencia.Placa;
import Persistencia.Vehiculo;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author angel
 */
public interface IPlacaDAO {

    void agregar(Placa placa);

    void actualizar(Placa placa);

    Placa consultar(String numPlaca);

    List<Placa> consultarTodos();

    Placa consultarPlacaActiva(Vehiculo vehiculo);

    List<Placa> consultarHistorial(Vehiculo vehiculo);
}
