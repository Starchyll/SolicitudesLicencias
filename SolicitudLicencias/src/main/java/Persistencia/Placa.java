/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import javax.persistence.*;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author mmax2
 */

@Entity
public class Placa {
    @Id
    private Long num_placa;
    
    @Column()
    private DateTime fecha_recepcion;
    
    @Column()
    private Long costo;
    
    @Column()
    private boolean es_nuevo;
    
    @Column()
    private boolean activa;
    
    @Column()
    private DateTime fecha_emicion;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "num_serie")
    private Vehiculo vehiculo;
    
    @OneToOne(mappedBy = "palca", cascade = CascadeType.PERSIST)
    private Tramite tramite;

    public Placa(Long num_placa, DateTime fecha_recepcion, Long costo, boolean es_nuevo, boolean activa, DateTime fecha_emicion, Vehiculo vehiculo, Tramite tramite) {
        this.num_placa = num_placa;
        this.fecha_recepcion = fecha_recepcion;
        this.costo = costo;
        this.es_nuevo = es_nuevo;
        this.activa = activa;
        this.fecha_emicion = fecha_emicion;
        this.vehiculo = vehiculo;
        this.tramite = tramite;
    }

    public Long getNum_placa() {
        return num_placa;
    }

    public void setNum_placa(Long num_placa) {
        this.num_placa = num_placa;
    }

    public DateTime getFecha_recepcion() {
        return fecha_recepcion;
    }

    public void setFecha_recepcion(DateTime fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }

    public Long getCosto() {
        return costo;
    }

    public void setCosto(Long costo) {
        this.costo = costo;
    }

    public boolean isEs_nuevo() {
        return es_nuevo;
    }

    public void setEs_nuevo(boolean es_nuevo) {
        this.es_nuevo = es_nuevo;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public DateTime getFecha_emicion() {
        return fecha_emicion;
    }

    public void setFecha_emicion(DateTime fecha_emicion) {
        this.fecha_emicion = fecha_emicion;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

   
}
