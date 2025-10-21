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
public class Vehiculo {

    @Id
    private Long num_serie;

    @Column()
    private String marca;

    @Column()
    @Enumerated
    private String tipo;

    @Column()
    private String modelo;

    @Column()
    private String color;

    @Column()
    private String linea;

    @OneToMany( mappedBy = "vehiculo",cascade = CascadeType.PERSIST)
    private List<Placa> placa;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn (name = "RFC")
    private Persona dueño;

    public Vehiculo(Long num_serie, String marca, String tipo, String modelo, String color, String linea, List<Placa> placa, Persona dueño) {
        this.num_serie = num_serie;
        this.marca = marca;
        this.tipo = tipo;
        this.modelo = modelo;
        this.color = color;
        this.linea = linea;
        this.placa = placa;
        this.dueño = dueño;
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

    public Persona getDueño() {
        return dueño;
    }

    public void setDueño(Persona dueño) {
        this.dueño = dueño;
    }

    
}
