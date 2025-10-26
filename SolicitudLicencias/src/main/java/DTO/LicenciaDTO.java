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
public class LicenciaDTO {
    private Long idLicencia;
    private int vigencia;
    private DateTime fechaVencimiento;
    private String RFCPersona;

    public LicenciaDTO(Long idLicencia, int vigencia, DateTime fechaVencimiento, String RFCPersona) {
        this.idLicencia = idLicencia;
        this.vigencia = vigencia;
        this.fechaVencimiento = fechaVencimiento;
        this.RFCPersona = RFCPersona;
    }

    public LicenciaDTO() {
    }

    public Long getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(Long idLicencia) {
        this.idLicencia = idLicencia;
    }

    public int getVigencia() {
        return vigencia;
    }

    public void setVigencia(int vigencia) {
        this.vigencia = vigencia;
    }

    public DateTime getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(DateTime fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getRFCPersona() {
        return RFCPersona;
    }

    public void setRFCPersona(String RFCPersona) {
        this.RFCPersona = RFCPersona;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idLicencia);
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
        final LicenciaDTO other = (LicenciaDTO) obj;
        return Objects.equals(this.idLicencia, other.idLicencia);
    }

    @Override
    public String toString() {
        return "LicenciaDTO{" + "idLicencia=" + idLicencia + ", vigencia=" + vigencia + ", fechaVencimiento=" + fechaVencimiento + ", RFCPersona=" + RFCPersona + '}';
    }

}
