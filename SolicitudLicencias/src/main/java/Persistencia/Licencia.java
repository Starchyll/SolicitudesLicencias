/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.io.Serializable;
import javax.persistence.*;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author mmax2
 */
@Entity
public class Licencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_licencia;
    
    @Column()
    private Integer vigencia;
    
    @Column()
    private long costo;
    
    @Column()
    private DateTime fecha_vencimiento;
    
    @Column()
    private DateTime fecha_expedicion;
    
    @Column()
    private boolean es_discapacitado;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn (name = "RFC")
    private Persona persona;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_tramite")
    private Tramite tramite;

    public Licencia(Integer vigencia, long costo, DateTime fecha_vencimiento, DateTime fecha_expedicion, boolean es_discapacitado, Persona persona, Tramite tramite) {
        this.vigencia = vigencia;
        this.costo = costo;
        this.fecha_vencimiento = fecha_vencimiento;
        this.fecha_expedicion = fecha_expedicion;
        this.es_discapacitado = es_discapacitado;
        this.persona = persona;
        this.tramite = tramite;
    }

    public Long getId_licencia() {
        return id_licencia;
    }

    public void setId_licencia(Long id_licencia) {
        this.id_licencia = id_licencia;
    }

    public Integer getVigencia() {
        return vigencia;
    }

    public void setVigencia(Integer vigencia) {
        this.vigencia = vigencia;
    }

    public long getCosto() {
        return costo;
    }

    public void setCosto(long costo) {
        this.costo = costo;
    }

    public DateTime getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(DateTime fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public DateTime getFecha_expedicion() {
        return fecha_expedicion;
    }

    public void setFecha_expedicion(DateTime fecha_expedicion) {
        this.fecha_expedicion = fecha_expedicion;
    }

    public boolean isEs_discapacitado() {
        return es_discapacitado;
    }

    public void setEs_discapacitado(boolean es_discapacitado) {
        this.es_discapacitado = es_discapacitado;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }
}
