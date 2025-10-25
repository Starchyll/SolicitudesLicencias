package Persistencia;

import Persistencia.Persona;
import Persistencia.Tramite;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-10-24T17:55:52", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Licencia.class)
public class Licencia_ { 

    public static volatile SingularAttribute<Licencia, Integer> vigencia;
    public static volatile SingularAttribute<Licencia, Long> id_licencia;
    public static volatile SingularAttribute<Licencia, Persona> persona;
    public static volatile SingularAttribute<Licencia, Long> costo;
    public static volatile SingularAttribute<Licencia, DateTime> fecha_vencimiento;
    public static volatile SingularAttribute<Licencia, Tramite> tramite;
    public static volatile SingularAttribute<Licencia, DateTime> fecha_expedicion;
    public static volatile SingularAttribute<Licencia, Boolean> es_discapacitado;

}