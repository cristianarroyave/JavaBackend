package cursojava.spring.springboot.dto;

import java.math.BigDecimal;
import java.util.Date;

public class TareaDTO {

	private Integer codigo;
	
	private Integer proyecto;
	
	private String nombre;

	private Date fechaInicio;

	private Date fechaFin;

	private BigDecimal horasEstimadas;
	
	private BigDecimal horasRealizadas;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getProyecto() {
		return proyecto;
	}

	public void setProyecto(Integer proyecto) {
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

	@Override
	public String toString() {
		return String.format(
				"TareaDTO [codigo=%s, proyecto=%s, nombre=%s, fechaInicio=%s, fechaFin=%s, horasEstimadas=%s, horasRealizadas=%s]",
				codigo, proyecto, nombre, fechaInicio, fechaFin, horasEstimadas, horasRealizadas);
	}
	
}
