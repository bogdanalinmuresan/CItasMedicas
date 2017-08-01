package com.bogdan.Model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
/**
 * clase que realiza el mapeo de la tabla DIAGNOSTICO en base de datos
 * @author bogdan
 *
 */
@Entity
@Table(name = "DIAGNOSTICO", catalog = "citas_medicas")
public class Diagnostico implements Serializable {

	private static final long serialVersionUID = 1L;
	

	private Integer id_cita;
	private String valoracionEspecialista;
	private String enfermedad;
	private Cita cita;
	
	public Diagnostico(){}
	
	public Diagnostico(Cita cita,String valoracionEspe, String enfermedad){
		this.cita=cita;
		this.valoracionEspecialista=valoracionEspe;
		this.enfermedad=enfermedad;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value="cita"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID_CITA", unique = true, nullable = false)
	public Integer getId_cita() {
		return id_cita;
	}

	public void setId_cita(Integer id_cita) {
		this.id_cita = id_cita;
	}

	@Column(name = "VALORACIONESPECIALSTA", nullable = false, length = 400)
	public String getValoracionEspecialista() {
		return valoracionEspecialista;
	}

	public void setValoracionEspecialista(String valoracionEspecialista) {
		this.valoracionEspecialista = valoracionEspecialista;
	}

	@Column(name = "ENFERMEDAD", nullable = false, length=100)
	public String getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(String enfermedad) {
		this.enfermedad = enfermedad;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
