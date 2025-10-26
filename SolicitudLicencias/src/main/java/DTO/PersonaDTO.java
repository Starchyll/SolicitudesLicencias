/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author angel
 */
public class PersonaDTO {

    private String RFC;
    private String nombreCompleto;
    private DateTime fechaNacimiento;
    private String telefono;
    private boolean esDiscapacitado;

    public PersonaDTO(String RFC, String nombreCompleto, DateTime fechaNacimiento, String telefono, boolean esDiscapacitado) {
        this.RFC = RFC;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.esDiscapacitado = esDiscapacitado;
    }

    public PersonaDTO() {
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public DateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(DateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isEsDiscapacitado() {
        return esDiscapacitado;
    }

    public void setEsDiscapacitado(boolean esDiscapacitado) {
        this.esDiscapacitado = esDiscapacitado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.RFC);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PersonaDTO other = (PersonaDTO) obj;
        return Objects.equals(this.RFC, other.RFC);
    }

    @Override
    public String toString() {
        return "PersonaDTO{" + "RFC=" + RFC + ", nombreCompleto=" + nombreCompleto + ", fechaNacimiento=" + fechaNacimiento + ", telefono=" + telefono + ", esDiscapacitado=" + esDiscapacitado + '}';
    }

}
