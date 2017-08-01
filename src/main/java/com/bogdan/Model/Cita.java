package com.bogdan.Model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
@Entity
@Table(name = "CITA", catalog = "citas_medicas")
public class Cita implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_CITA", unique = true, nullable = false)
	@GeneratedValue(strategy = IDENTITY)
	private Integer id_cita;

	@Column(name="FECHAHORA")
	@Temporal(TemporalType.DATE)
	private Date fechahora;
	
	@Column(name="MOTIVOCITA")
	private String motivoCita;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy= "cita", cascade = CascadeType.ALL)
	private Diagnostico diagnostico;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PACIENTE_ID", nullable = false)
	private Paciente paciente;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name= "MEDICO_ID", nullable = false)
	private Medico medico;
	

	public Cita(){}
	
	public Cita(String motivo, Diagnostico diag){
		this.motivoCita=motivo;
		this.diagnostico=diag;
		this.fechahora=new Date();
	}
	
	public Cita(String motivo){
		this.motivoCita=motivo;
		this.fechahora=new Date();
	}

	public Integer getId_cita() {
		return id_cita;
	}

	public void setId_cita(Integer id_cita) {
		this.id_cita = id_cita;
	}

	public Date getFechahora() {
		return fechahora;
	}

	public void setFechahora(Date fechahora) {
		this.fechahora = fechahora;
	}

	public String getMotivoCita() {
		return motivoCita;
	}

	public void setMotivoCita(String motivoCita) {
		this.motivoCita = motivoCita;
	}

	

	public Diagnostico getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

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
	
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	
	
	
	

}
