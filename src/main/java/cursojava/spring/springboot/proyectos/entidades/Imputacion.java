package cursojava.spring.springboot.proyectos.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(schema = "MODELOPROYECTOS", name = "IMPUTACIONES")
public class Imputacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
	@ManyToOne
	@JoinColumns(
		{
			@JoinColumn(name = "EMPLEADO", referencedColumnName = "NIF")
		}
	)
	private Empleado empleado;
	
	@ManyToOne
	@JoinColumns(
		{
			@JoinColumn(name = "TAREA", referencedColumnName = "CODIGO")
		}
	)
	@JsonIgnore
	private Tarea tarea;
	
	private Date fecha;
	
	@Column(name = "NUMERO_HORAS")
	private Integer numeroHoras;
	
	private String descripcion;

	public Imputacion() {}

	public Imputacion(Integer codigo, Empleado empleado) {
		this.codigo = codigo;
		this.empleado = empleado;
	}

	public Imputacion(Empleado empleado, Tarea tarea, Date fecha, Integer numeroHoras,
			String descripcion) {
		this.empleado = empleado;
		this.tarea = tarea;
		this.fecha = fecha;
		this.numeroHoras = numeroHoras;
		this.descripcion = descripcion;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Tarea getTarea() {
		return tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getNumeroHoras() {
		return numeroHoras;
	}

	public void setNumeroHoras(Integer numeroHoras) {
		this.numeroHoras = numeroHoras;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return String.format("Imputacion [codigo=%s, empleado=%s, tarea=%s, fecha=%s, numeroHoras=%s, descripcion=%s]",
				codigo, empleado, tarea, fecha, numeroHoras, descripcion);
	}	
}
