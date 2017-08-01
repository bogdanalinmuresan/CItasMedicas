package com.bogdan.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

/**
 * Ver cardinalidad entre relaciones : https://www.mkyong.com/hibernate/hibernate-one-to-many-relationship-example-annotation/
 * @author bogdan
 *
 */
@Entity
@Table(name = "MEDICO", catalog = "citas_medicas")
public class Medico implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEDICO_ID", unique = true, nullable = false)
	private Integer medico_id;
	
	@Column(name = "NUMCOLEGIADO", unique = true, nullable = false)
	private String numcolegiado;
	
	@Column(name = "NOMBRE", nullable = false)
	private String nombre;
	
	@Column(name = "APELLIDOS", nullable = false)
	private String apellidos;
	
	@Column(name = "USUARIO", nullable = false)
	private String usuario;
	
	@Column(name = "CLAVE", nullable = false)
	private String clave;
	
	@OneToMany(targetEntity=MedicoPaciente.class, mappedBy= "pk.medico", cascade=CascadeType.ALL)
	private List<MedicoPaciente> listaPacientes= new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "medico")
	private Set<Cita> listaCitasMedico = new HashSet<Cita>(0);
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.medico")
//	private Set<MedicoPaciente> listaMedicos = new HashSet<MedicoPaciente>(0);
//	

	public Medico(){}
	
	public Medico(String nrColegiado, String nom, String Ape, String usua, String clave){
		this.setNumcolegiado(nrColegiado);
		this.setNombre(nom);
		this.setApellidos(Ape);
		this.setUsuario(usua);
		this.setClave(clave);
		
	}
	
	
	public Integer getMedico_id() {
		return medico_id;
	}

	public void setMedico_id(Integer medico_id) {
		this.medico_id = medico_id;
	}

	public String getNumcolegiado() {
		return numcolegiado;
	}

	public void setNumcolegiado(String numcolegiado) {
		this.numcolegiado = numcolegiado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

    public List<MedicoPaciente> getListaPacientes() {
		return listaPacientes;
	}

	public void setListaPacientes(List<MedicoPaciente> listaPacientes) {
		this.listaPacientes = listaPacientes;
	}
	
	public void addPaciente(Paciente p){
		MedicoPaciente pacientemedico=new MedicoPaciente();
		pacientemedico.setMedico(this);
		pacientemedico.setPaciente(p);
		listaPacientes.add(pacientemedico);
		p.getListaMedico().add(pacientemedico);
	}
	
	public void removePaciente(Paciente p){
		MedicoPaciente pacientemedico=new MedicoPaciente();
		pacientemedico.setMedico(this);
		pacientemedico.setPaciente(p);
		p.getListaMedico().remove(pacientemedico);
		listaPacientes.remove(pacientemedico);
		pacientemedico.setMedico(null);
		pacientemedico.setPaciente(null);
	}
	
	/**
	 * metodo que que asigna una cita a un medico
	 * @param c la cita
	 */
	public void addCita(Cita c){
		listaCitasMedico.add(c);
	}
	

	public Set<Cita> getListaCitasMedico() {
		return listaCitasMedico;
	}

	public void setListaCitasMedico(Set<Cita> listaCitasMedico) {
		this.listaCitasMedico = listaCitasMedico;
	}

	@Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Medico medico = (Medico) o;
        return Objects.equals( medico_id, medico.medico_id );
    }

    @Override
    public int hashCode() {
        return Objects.hash( medico_id );
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
//	@OneToOne(fetch = FetchType.LAZY)
//	@PrimaryKeyJoinColumn
//	public Usuario getUsuario() {
//		return usuario;
//	}
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.medico")
//	public Set<MedicoPaciente> getListaMedicos() {
//		return listaMedicos;
//	}
//
//	public void setListaMedicos(Set<MedicoPaciente> mp) {
//		this.listaMedicos = mp;
//	}


	
	
	
}
