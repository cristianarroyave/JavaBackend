package cursojava.spring.springboot.proyectos.entidades;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(schema = "MODELOPROYECTOS", name = "TAREAS")
public class Tarea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumns(
		{
			@JoinColumn(name = "PROYECTO", referencedColumnName = "CODIGO")
		}
	)
	private Proyecto proyecto;
	
	private String nombre;
	
	@Column(name = "FECHA_INICIO")
	private Date fechaInicio;
	
	@Column(name = "FECHA_FIN")
	private Date fechaFin;
	
	@Column(name = "HORAS_ESTIMADAS")
	private BigDecimal horasEstimadas;
	
	@Column(name = "HORAS_REALIZADAS")
	private BigDecimal horasRealizadas;
	
	@Column(name = "FECHA_ALTA", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAlta;
	
	@ManyToMany(mappedBy = "asignaciones")
	@JsonIgnore
	private Set<Empleado> empleados = new HashSet<Empleado>();
	
	@OneToMany(mappedBy = "tarea")
	@JsonIgnore
	private Set<Imputacion> imputaciones = new HashSet<Imputacion>();
	
	public Tarea() {}

	public Tarea(Integer codigo, Proyecto proyecto, String nombre) {
		this.codigo = codigo;
		this.proyecto = proyecto;
		this.nombre = nombre;
	}

	public Tarea(Integer codigo, Proyecto proyecto, String nombre, Date fechaInicio, Date fechaFin,
			BigDecimal horasEstimadas, BigDecimal horasRealizadas, Date fechaAlta) {
		this.codigo = codigo;
		this.proyecto = proyecto;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.horasEstimadas = horasEstimadas;
		this.horasRealizadas = horasRealizadas;
		this.fechaAlta = fechaAlta;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public BigDecimal getHorasEstimadas() {
		return horasEstimadas;
	}

	public void setHorasEstimadas(BigDecimal horasEstimadas) {
		this.horasEstimadas = horasEstimadas;
	}

	public BigDecimal getHorasRealizadas() {
		return horasRealizadas;
	}

	public void setHorasRealizadas(BigDecimal horasRealizadas) {
		this.horasRealizadas = horasRealizadas;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Override
	public String toString() {
		return String.format(
				"Tarea [id=%s, proyecto=%s, nombre=%s, fechaInicio=%s, fechaFin=%s, horasEstimadas=%s, horasRealizadas=%s, fechaAlta=%s]",
				codigo, proyecto, nombre, fechaInicio, fechaFin, horasEstimadas, horasRealizadas, fechaAlta);
	}
	
}
