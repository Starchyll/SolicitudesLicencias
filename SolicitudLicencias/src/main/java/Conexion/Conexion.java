/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import Exception.PersistenciaException;
import java.sql.Connection;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author mmax2
 */
public class Conexion{
    private static EntityManagerFactory emf;
    
    
    private static final String UNIDAD_PERSISTENCIA = "ConexionPU";

     /**
     * Crea y devuelve una instancia singleton del EntityManagerFactory.
     * @return El EntityManagerFactory para la unidad de persistencia.
     * @throws Exception.PersistenciaException
     */
    public static EntityManagerFactory crearConexion() throws PersistenciaException {
       
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(UNIDAD_PERSISTENCIA);
        }
        return emf;
    }

    
}
