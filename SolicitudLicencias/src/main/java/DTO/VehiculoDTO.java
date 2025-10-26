/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author angel
 */
public class VehiculoDTO {

    private String numSerie;
    private String marca;
    private String linea;
    private String color;
    private int modelo;
    private String tipoVehiculo;
    private String RFCDueno;

    public VehiculoDTO(String numSerie, String marca, String linea, String color, int modelo, String tipoVehiculo, String RFCDueno) {
        this.numSerie = numSerie;
        this.marca = marca;
        this.linea = linea;
        this.color = color;
        this.modelo = modelo;
        this.tipoVehiculo = tipoVehiculo;
        this.RFCDueno = RFCDueno;
    }

    public VehiculoDTO() {
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getRFCDueno() {
        return RFCDueno;
    }

    public void setRFCDueno(String RFCDueno) {
        this.RFCDueno = RFCDueno;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.numSerie);
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
        final VehiculoDTO other = (VehiculoDTO) obj;
        return Objects.equals(this.numSerie, other.numSerie);
    }
    
    @Override
    public String toString() {
        return "VehiculoDTO{" + "numSerie=" + numSerie + ", marca=" + marca + ", linea=" + linea + ", color=" + color + ", modelo=" + modelo + ", tipoVehiculo=" + tipoVehiculo + ", RFCDueno=" + RFCDueno + '}';
    }
    
    
}
