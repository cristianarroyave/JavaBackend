package cursojava.spring.springboot.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ProyectoDTO {

	private Integer codigo;
	
	private String nombre;
	
	private BigDecimal horasEstimadas;
	
	private BigDecimal horasConsumidas;
	
	private Date fechaEstimadaCierre;
	
	private Date fechaCierre;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getHorasEstimadas() {
		return horasEstimadas;
	}

	public void setHorasEstimadas(BigDecimal horasEstimadas) {
		this.horasEstimadas = horasEstimadas;
	}

	public BigDecimal getHorasConsumidas() {
		return horasConsumidas;
	}

	public void setHorasConsumidas(BigDecimal horasConsumidas) {
		this.horasConsumidas = horasConsumidas;
	}

	public Date getFechaEstimadaCierre() {
		return fechaEstimadaCierre;
	}

	public void setFechaEstimadaCierre(Date fechaEstimadaCierre) {
		this.fechaEstimadaCierre = fechaEstimadaCierre;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	@Override
	public String toString() {
		return String.format(
				"ProyectoDTO [codigo=%s, nombre=%s, horasEstimadas=%s, horasConsumidas=%s, fechaEstimadaCierre=%s, fechaCierre=%s]",
				codigo, nombre, horasEstimadas, horasConsumidas, fechaEstimadaCierre, fechaCierre);
	}
}
