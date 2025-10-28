/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import Exception.PersistenciaException;
import java.sql.Connection;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mmax2
 */
public interface IConexion {
    EntityManagerFactory crearConexion() throws PersistenciaException;
}
