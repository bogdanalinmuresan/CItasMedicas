package com.bogdan.Model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class MedicoPacienteId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Paciente paciente;
	@ManyToOne
	private Medico medico;
	
	
	//constructor vacio siempre hay que ponerlo
	public MedicoPacienteId(){}
	
	
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	// The component type used as identifier must implement equals() and hashCode().
	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicoPacienteId that = (MedicoPacienteId) o;

        if (paciente != null ? !paciente.equals(that.paciente) : that.paciente != null) return false;
        if (medico != null ? !medico.equals(that.medico) : that.medico != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result=1;
        result = (paciente != null ? paciente.hashCode() : 0);
        result = 31 * result + (medico != null ? medico.hashCode() : 0);
        return result;
    }
	

}
