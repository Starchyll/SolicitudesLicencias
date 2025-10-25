package Persistencia;

import Persistencia.Persona;
import Persistencia.Placa;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-10-24T17:55:52", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Vehiculo.class)
public class Vehiculo_ { 

    public static volatile SingularAttribute<Vehiculo, String> marca;
    public static volatile SingularAttribute<Vehiculo, String> tipo;
    public static volatile SingularAttribute<Vehiculo, String> color;
    public static volatile SingularAttribute<Vehiculo, Persona> dueño;
    public static volatile SingularAttribute<Vehiculo, Long> num_serie;
    public static volatile SingularAttribute<Vehiculo, String> modelo;
    public static volatile SingularAttribute<Vehiculo, String> linea;
    public static volatile ListAttribute<Vehiculo, Placa> placa;

}