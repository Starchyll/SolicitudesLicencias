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
@Table(name = "Tramites")
public class Tramite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tramite;

    @Column(name = "fecha_realizacion")
    private Date fecha_realizacion;

    private Long costo;

    @Enumerated(EnumType.STRING)
    private TipoTramite tipo_tramite;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "RFC")
    private Persona persona;    
    
    @OneToOne(mappedBy = "tramite", cascade = CascadeType.PERSIST)
    private Licencia licencia;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn (name = "id_placa")
    private Placa placa;

    public Tramite() {
    }

    public Tramite( Date fecha_realizacion, Long costo, Persona persona) {
        this.fecha_realizacion = fecha_realizacion;
        this.costo = costo;
        this.persona = persona;
    }

    public Long getId_tramite() {
        return id_tramite;
    }

    public Date getFecha_realizacion() {
        return fecha_realizacion;
    }

    public void setFecha_realizacion(Date fecha_realizacion) {
        this.fecha_realizacion = fecha_realizacion;
    }

    public Long getCosto() {
        return costo;
    }

    public void setCosto(Long costo) {
        this.costo = costo;
    }

    public TipoTramite getTipo_tramite() {
        return tipo_tramite;
    }

    public void setTipo_tramite(TipoTramite tipo_tramite) {
        this.tipo_tramite = tipo_tramite;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Licencia getLicencia() {
        return licencia;
    }

    public void setLicencia(Licencia licencia) {
        this.licencia = licencia;
    }

    public Placa getPlaca() {
        return placa;
    }

    public void setPlaca(Placa placa) {
        this.placa = placa;
    }
    
    
}
