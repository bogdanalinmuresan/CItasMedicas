package com.bogdan.Model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * Tutorial utilizado: http://www.mkyong.com/hibernate/hibernate-many-to-many-example-join-table-extra-column-annotation/
 * @author bogdan
 *
 */

@Entity
@Table(name ="MEDICO_PACIENTE", catalog = "citas_medicas")
@AssociationOverrides({
	@AssociationOverride(name = "pk.paciente",
		joinColumns = @JoinColumn(name = "PACIENTE_ID")),
	@AssociationOverride(name = "pk.medico",
		joinColumns = @JoinColumn(name = "MEDICO_ID")) })
public class MedicoPaciente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private MedicoPacienteId pk=new MedicoPacienteId();
	
	public MedicoPaciente(){}
	
	public MedicoPaciente(Paciente p, Medico m){
		this.pk.setPaciente(p);
		this.pk.setMedico(m);
	}
	

	public MedicoPacienteId getPk() {
		return pk;
	}


	public void setPk(MedicoPacienteId pk) {
		this.pk = pk;
	}


	@Transient
	public Paciente getPaciente() {
		return getPk().getPaciente();
	}

	public void setPaciente(Paciente paciente) {
		getPk().setPaciente(paciente);
	}

	@Transient
	public Medico getMedico() {
		return getPk().getMedico();
	}

	public void setMedico(Medico medico) {
		getPk().setMedico(medico);
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		MedicoPaciente that = (MedicoPaciente) o;

		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
	
	
	
	
}
