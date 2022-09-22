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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(schema = "MODELOPROYECTOS", name = "PROYECTOS")
public class Proyecto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
	private String nombre;
	
	@Column(name = "HORAS_ESTIMADAS")
	private BigDecimal horasEstimadas;
	
	@Column(name = "HORAS_CONSUMIDAS")
	private BigDecimal horasConsumidas;
	
	@Column(name = "FECHA_ESTIMADA_CIERRE")
	@Temporal(TemporalType.DATE)
	private Date fechaEstimadaCierre;
	
	@Column(name = "FECHA_CIERRE")
	@Temporal(TemporalType.DATE)
	private Date fechaCierre;
	
	@Column(name = "FECHA_ALTA", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAlta;
	
	@OneToMany(mappedBy = "proyecto")
	@JsonIgnore
	private Set<Tarea> tareas = new HashSet<Tarea>();
	
	public Proyecto() {}

	public Proyecto(Integer codigo, String nombre) 
	{
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public Proyecto(Integer codigo, String nombre, BigDecimal horasEstimadas, BigDecimal horasConsumidas,
			Date fechaEstimadaCierre, Date fechaCierre, Date fechaAlta) 
	{
		this.codigo = codigo;
		this.nombre = nombre;
		this.horasEstimadas = horasEstimadas;
		this.horasConsumidas = horasConsumidas;
		this.fechaEstimadaCierre = fechaEstimadaCierre;
		this.fechaCierre = fechaCierre;
		this.fechaAlta = fechaAlta;
	}

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

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Set<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(Set<Tarea> tareas) {
		this.tareas = tareas;
	}

	@Override
	public String toString() 
	{
		return String.format(
				"Proyecto [id=%s, nombre=%s, horasEstimadas=%s, horasConsumidas=%s, fechaEstimadaCierre=%s, fechaCierre=%s, fechaAlta=%s]",
				codigo, nombre, horasEstimadas, horasConsumidas, fechaEstimadaCierre, fechaCierre, fechaAlta);
	}	
	
}
