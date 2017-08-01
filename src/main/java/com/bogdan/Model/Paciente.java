package com.bogdan.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "PACIENTE", catalog = "citas_medicas")
public class Paciente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PACIENTE_ID", unique = true, nullable = false)
	private Integer paciente_id;
	
	@Column(name = "NSS", unique = true, nullable = false)
	private String nss;
	
	@Column(name = "NUMTARJETA", unique = true, nullable = false)
	private String numtarjeta;
	
	@Column(name = "TELEFONO", nullable = false)
	private String telefono;
	
	@Column(name = "DIRECCION", nullable = false)
	private String direccion;
	
	@Column(name = "NOMBRE", nullable = false)
	private String nombre;
	
	@Column(name = "APELLIDOS", nullable = false)
	private String apellidos;
	
	@Column(name = "USUARIO", nullable = false)
	private String usuario;
	
	@Column(name = "CLAVE", nullable = false)
	private String clave;
	
	@OneToMany(targetEntity=MedicoPaciente.class, mappedBy="pk.paciente" ,cascade = CascadeType.ALL)
	private List<MedicoPaciente> listaMedico=new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paciente")
	private Set<Cita> listaCitasPaciente = new HashSet<Cita>(0);
	
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.paciente")
//	private Set<MedicoPaciente> listaMedicos = new HashSet<MedicoPaciente>(0);
	
	/**
	 * 
	 */
	public Paciente(){}
	
	/**
	 * 
	 * @param nss
	 * @param nrtar
	 * @param tel
	 * @param dir
	 * @param nomb
	 * @param ape
	 * @param usua
	 * @param c
	 */
	public Paciente(String nss, String nrtar, String tel, String dir, String nomb, String ape, String usua,String c){
		this.setNss(nss);
		this.setNumtarjeta(nrtar);
		this.setTelefono(tel);
		this.setDireccion(dir);
		this.setNombre(nomb);
		this.setApellidos(ape);
		this.setUsuario(usua);
		this.setClave(c);
	}

	
	public Integer getPaciente_id() {
		return paciente_id;
	}

	public void setPaciente_id(Integer paciente_id) {
		this.paciente_id = paciente_id;
	}

	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	public String getNumtarjeta() {
		return numtarjeta;
	}

	public void setNumtarjeta(String numtarjeta) {
		this.numtarjeta = numtarjeta;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	public List<MedicoPaciente> getListaMedico() {
		return listaMedico;
	}

	public void setListaMedico(List<MedicoPaciente> listaMedico) {
		this.listaMedico = listaMedico;
	}

	public void addMedico(Medico m){
		MedicoPaciente pacientemedico=new MedicoPaciente(this, m);
		listaMedico.add(pacientemedico);
		m.getListaPacientes().add(pacientemedico);
	}
	
	/**
	 * metodo que que asigna una cita a una paciente
	 * @param c la cita
	 */
	public void addCita(Cita c){
		listaCitasPaciente.add(c);
	}
	
	public void removeMedico(Medico m){
		MedicoPaciente pacientemedico=new MedicoPaciente();
		pacientemedico.setMedico(m);
		pacientemedico.setPaciente(this);
		m.getListaPacientes().remove(pacientemedico);
		listaMedico.remove(pacientemedico);
		pacientemedico.setMedico(null);
		pacientemedico.setPaciente(null);
	}
	
	
	
//	public Paciente(String nss, String tel, String dir, HashSet<MedicoPaciente> mp){
//		this.setNss(nss);
//		this.setTelefono(tel);
//		this.setDireccion(dir);
//		this.setListaMedicos(mp);
//	}
    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Paciente paciente = (Paciente) o;
        return Objects.equals( paciente_id, paciente.paciente_id );
    }

    @Override
    public int hashCode() {
        return Objects.hash( paciente_id);
    }
	
	
}
	