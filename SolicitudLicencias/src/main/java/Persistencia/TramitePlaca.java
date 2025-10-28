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
public class TramitePlaca extends Tramite {
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn (name = "id_placa")
    private Placa placa;

    public TramitePlaca() {
    }

    public TramitePlaca(Placa placa) {
        this.placa = placa;
    }

    public TramitePlaca(Placa placa, Date fecha_realizacion, Long costo, Persona persona) {
        this.placa = placa;
    }

    
    
    
    
}
