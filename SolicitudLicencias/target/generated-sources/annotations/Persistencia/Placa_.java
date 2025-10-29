package Persistencia;

import Persistencia.Tramite;
import Persistencia.Vehiculo;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-10-28T18:42:19", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Placa.class)
public class Placa_ { 

    public static volatile SingularAttribute<Placa, Boolean> es_nuevo;
    public static volatile SingularAttribute<Placa, Long> costo;
    public static volatile SingularAttribute<Placa, Tramite> tramite;
    public static volatile SingularAttribute<Placa, Date> fecha_recepcion;
    public static volatile SingularAttribute<Placa, Long> num_placa;
    public static volatile SingularAttribute<Placa, Vehiculo> vehiculo;
    public static volatile SingularAttribute<Placa, Boolean> activa;
    public static volatile SingularAttribute<Placa, Date> fecha_emicion;

}