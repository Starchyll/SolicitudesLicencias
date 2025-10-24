package Persistencia;

import Enum.TipoTramite;
import Persistencia.Licencia;
import Persistencia.Persona;
import Persistencia.Placa;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-10-24T15:42:33", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Tramite.class)
public class Tramite_ { 

    public static volatile SingularAttribute<Tramite, Long> id_tramite;
    public static volatile SingularAttribute<Tramite, Persona> persona;
    public static volatile SingularAttribute<Tramite, Licencia> licencia;
    public static volatile SingularAttribute<Tramite, Long> costo;
    public static volatile SingularAttribute<Tramite, DateTime> fecha_realizacion;
    public static volatile SingularAttribute<Tramite, TipoTramite> tipo_tramite;
    public static volatile SingularAttribute<Tramite, Placa> placa;

}