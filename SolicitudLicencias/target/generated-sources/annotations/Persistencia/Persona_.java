package Persistencia;

import Persistencia.Licencia;
import Persistencia.Tramite;
import Persistencia.Vehiculo;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-10-24T17:55:52", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, Licencia> licencia;
    public static volatile ListAttribute<Persona, Tramite> lista_tramites;
    public static volatile SingularAttribute<Persona, DateTime> fecha_nacimiento;
    public static volatile SingularAttribute<Persona, String> telefono;
    public static volatile SingularAttribute<Persona, String> nombre;
    public static volatile SingularAttribute<Persona, String> RFC;
    public static volatile ListAttribute<Persona, Vehiculo> lista_vehiculos;

}