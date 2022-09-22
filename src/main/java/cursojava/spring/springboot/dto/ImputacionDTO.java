package cursojava.spring.springboot.dto;

import java.util.Date;

public class ImputacionDTO {

	private String empleado;
	
	private Integer tarea;
	
	private Date fecha;
	
	private Integer numeroHoras;
	
	private String descripcion;

	public ImputacionDTO(String empleado, Integer tarea, Date fecha, Integer numeroHoras, String descripcion) {
		this.empleado = empleado;
		this.tarea = tarea;
		this.fecha = fecha;
		this.numeroHoras = numeroHoras;
		this.descripcion = descripcion;
	}

	public String getEmpleado(){
		return empleado;
	}
	
	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public Integer getTarea() {
		return tarea;
	}

	public void setTarea(Integer tarea) {
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
}
