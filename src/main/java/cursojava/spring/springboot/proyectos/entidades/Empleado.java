package cursojava.spring.springboot.proyectos.entidades;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.springframework.data.jpa.repository.Query;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cursojava.spring.springboot.validaciones.Nif;

@Entity
@Table(schema = "MODELOPROYECTOS", name = "EMPLEADOS")
@NamedQuery(
		query = "SELECT emp FROM Empleado emp WHERE emp.fechaAlta BETWEEN :fechaInicio AND :fechaFin",
		name = "Empleado.consultarEntreFechas")
public class Empleado {

	@Id
	@Nif
	private String nif;
	
	private String nombre;
	
	private String apellidos;
	
	private String correo;
	
	@Column(name = "FECHA_ALTA", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAlta;
	
	@JsonIgnore
	@ManyToMany()
	@JoinTable( schema = "MODELOPROYECTOS", 
				name = "ASIGNACIONES", 
				joinColumns = {@JoinColumn(name = "EMPLEADO", referencedColumnName = "NIF")}, 
				inverseJoinColumns = { @JoinColumn(name = "TAREA", referencedColumnName = "CODIGO")}
	)
	private Set<Tarea> asignaciones = new HashSet<Tarea>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "empleado")
	private Set<Imputacion> imputaciones = new HashSet<Imputacion>();

	public Empleado() {}

	public Empleado(String nif, String nombre, String apellidos, String correo, Date fechaAlta) {
		this.nif = nif;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.correo = correo;
		this.fechaAlta = fechaAlta;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Set<Tarea> getAsignaciones() {
		return asignaciones;
	}

	public void setAsignaciones(Set<Tarea> asignaciones) {
		this.asignaciones = asignaciones;
	}

	public Set<Imputacion> getImputaciones() {
		return imputaciones;
	}

	public void setImputaciones(Set<Imputacion> imputaciones) {
		this.imputaciones = imputaciones;
	}

	@Override
	public String toString() {
		return String.format("Empleado [nif=%s, nombre=%s, apellidos=%s, correo=%s, fechaAlta=%s]", nif, nombre,
				apellidos, correo, fechaAlta);
	}
}
