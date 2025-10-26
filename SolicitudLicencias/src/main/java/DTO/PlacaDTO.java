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
public class PlacaDTO {
    private String numPlaca;
    private String numSerieVehiculo;
    private DateTime fechaEmision;
    private DateTime fechaRecepcion;
    private boolean activa;

    public PlacaDTO(String numPlaca, String numSerieVehiculo, DateTime fechaEmision, DateTime fechaRecepcion, boolean activa) {
        this.numPlaca = numPlaca;
        this.numSerieVehiculo = numSerieVehiculo;
        this.fechaEmision = fechaEmision;
        this.fechaRecepcion = fechaRecepcion;
        this.activa = activa;
    }

    public PlacaDTO() {
    }

    public String getNumPlaca() {
        return numPlaca;
    }

    public void setNumPlaca(String numPlaca) {
        this.numPlaca = numPlaca;
    }

    public String getNumSerieVehiculo() {
        return numSerieVehiculo;
    }

    public void setNumSerieVehiculo(String numSerieVehiculo) {
        this.numSerieVehiculo = numSerieVehiculo;
    }

    public DateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(DateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public DateTime getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(DateTime fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.numPlaca);
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
        final PlacaDTO other = (PlacaDTO) obj;
        return Objects.equals(this.numPlaca, other.numPlaca);
    }

    @Override
    public String toString() {
        return "PlacaDTO{" + "numPlaca=" + numPlaca + ", numSerieVehiculo=" + numSerieVehiculo + ", fechaEmision=" + fechaEmision + ", fechaRecepcion=" + fechaRecepcion + ", activa=" + activa + '}';
    }
    
}
