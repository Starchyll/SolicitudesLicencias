/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author mmax2
 */
@Entity
@Table(name="Persona")
public class Persona implements Serializable {

    
    private static final long serialVersionUID = 1L;
    
    @Id
    private String RFC;
    
    @Column(name="telefono")
    private String telefono;
    
    @Column(name="fecha_nacimiento")
    private DateTime fecha_nacimiento;
    
    @Column(name="nombre")
    private String nombre;
    
    @OneToOne(mappedBy = "persona",cascade = CascadeType.PERSIST)
    private Licencia licencia;
    
    @OneToMany(mappedBy = "dueño", cascade = CascadeType.PERSIST)
    private List<Vehiculo> lista_vehiculos;
    
    @OneToMany(mappedBy = "persona", cascade = CascadeType.PERSIST)
    private List<Tramite> lista_tramites;

    public Persona(String RFC, String telefono, DateTime fecha_nacimiento, String nombre, Licencia licencia, List<Vehiculo> lista_vehiculos, List<Tramite> lista_tramites) {
        this.RFC = RFC;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nombre = nombre;
        this.licencia = licencia;
        this.lista_vehiculos = lista_vehiculos;
        this.lista_tramites = lista_tramites;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public DateTime getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(DateTime fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Licencia getLicencia() {
        return licencia;
    }

    public void setLicencia(Licencia licencia) {
        this.licencia = licencia;
    }

    public List<Vehiculo> getLista_vehiculos() {
        return lista_vehiculos;
    }

    public void setLista_vehiculos(List<Vehiculo> lista_vehiculos) {
        this.lista_vehiculos = lista_vehiculos;
    }

    public List<Tramite> getLista_tramites() {
        return lista_tramites;
    }

    public void setLista_tramites(List<Tramite> lista_tramites) {
        this.lista_tramites = lista_tramites;
    }

}
