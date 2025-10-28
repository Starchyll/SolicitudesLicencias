/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Enum.TipoTramite;
import java.util.Date;
import javax.persistence.*;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author mmax2
 */
@Entity
public class TramiteLicencia extends Tramite{
    
    @OneToOne(mappedBy = "tramite", cascade = CascadeType.PERSIST)
    private Licencia licencia;

    public TramiteLicencia() {
    }

    public TramiteLicencia(Licencia licencia) {
        this.licencia = licencia;
    }

    public TramiteLicencia(Licencia licencia, Date fecha_realizacion, Long costo, Persona persona) {
        super(fecha_realizacion, costo, persona);
        this.licencia = licencia;
    }

    
    
    
    
}
