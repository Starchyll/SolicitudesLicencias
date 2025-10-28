/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author mmax2
 */
@Entity
@Table(name="Vehiculo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Tipo_Vehiculo",discriminatorType = DiscriminatorType.STRING)
public class Vehiculo {

    @Id
    private Long num_serie;

    @Column(name="marca")
    private String marca;

    @Column(name="tipo")
    private String tipo;

    @Column(name="modelo")
    private String modelo;

    @Column(name="color")
    private String color;

    @Column(name="linea")
    private String linea;

    @OneToMany( mappedBy = "vehiculo",cascade = CascadeType.PERSIST)
    private List<Placa> placa;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn (name = "RFC")
    private Persona dueno;

    public Vehiculo() {
    }

    public Vehiculo(Long num_serie, String marca, String tipo, String modelo, String color, String linea, Persona dueno) {
        this.num_serie = num_serie;
        this.marca = marca;
        this.tipo = tipo;
        this.modelo = modelo;
        this.color = color;
        this.linea = linea;
        this.dueno = dueno;
    }
    
    

    public Vehiculo(Long num_serie, String marca, String tipo, String modelo, String color, String linea, List<Placa> placa, Persona dueno) {
        this.num_serie = num_serie;
        this.marca = marca;
        this.tipo = tipo;
        this.modelo = modelo;
        this.color = color;
        this.linea = linea;
        this.placa = placa;
        this.dueno = dueno;
    }

    public Long getNum_serie() {
        return num_serie;
    }

    public void setNum_serie(Long num_serie) {
        this.num_serie = num_serie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public List<Placa> getPlaca() {
        return placa;
    }

    public void setPlaca(List<Placa> placa) {
        this.placa = placa;
    }

    public Persona getDueno() {
        return dueno;
    }

    public void setDueon(Persona dueon) {
        this.dueno = dueno;
    }

    
}
