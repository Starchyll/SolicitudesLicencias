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
public class TramiteDTO {

    private Long idTramite;
    private DateTime fechaRealizacion;
    private String tipoTramite;
    private String nombrePersona;
    private double costo;
    private String rfcSolicitante;
    private Long idLicencia;
    private String numPlaca;

    public TramiteDTO(Long idTramite, DateTime fechaRealizacion, String tipoTramite, String nombrePersona, double costo, String rfcSolicitante, Long idLicencia, String numPlaca) {
        this.idTramite = idTramite;
        this.fechaRealizacion = fechaRealizacion;
        this.tipoTramite = tipoTramite;
        this.nombrePersona = nombrePersona;
        this.costo = costo;
        this.rfcSolicitante = rfcSolicitante;
        this.idLicencia = idLicencia;
        this.numPlaca = numPlaca;
    }

    public TramiteDTO() {
    }

    public Long getIdTramite() {
        return idTramite;
    }

    public void setIdTramite(Long idTramite) {
        this.idTramite = idTramite;
    }

    public DateTime getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(DateTime fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getRfcSolicitante() {
        return rfcSolicitante;
    }

    public void setRfcSolicitante(String rfcSolicitante) {
        this.rfcSolicitante = rfcSolicitante;
    }

    public Long getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(Long idLicencia) {
        this.idLicencia = idLicencia;
    }

    public String getNumPlaca() {
        return numPlaca;
    }

    public void setNumPlaca(String numPlaca) {
        this.numPlaca = numPlaca;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.idTramite);
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
        final TramiteDTO other = (TramiteDTO) obj;
        return Objects.equals(this.idTramite, other.idTramite);
    }

    @Override
    public String toString() {
        return "TramiteDTO{" + "idTramite=" + idTramite + ", fechaRealizacion=" + fechaRealizacion + ", tipoTramite=" + tipoTramite + ", nombrePersona=" + nombrePersona + ", costo=" + costo + ", rfcSolicitante=" + rfcSolicitante + ", idLicencia=" + idLicencia + ", numPlaca=" + numPlaca + '}';
    }

}
