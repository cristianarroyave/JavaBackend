package cursojava.spring.springboot.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cursojava.spring.springboot.dto.ImputacionDTO;
import cursojava.spring.springboot.dto.ProyectoDTO;
import cursojava.spring.springboot.proyectos.Controlador;
import cursojava.spring.springboot.proyectos.entidades.Empleado;
import cursojava.spring.springboot.proyectos.entidades.Imputacion;
import cursojava.spring.springboot.proyectos.entidades.Proyecto;
import cursojava.spring.springboot.proyectos.entidades.Tarea;
import cursojava.spring.springboot.proyectos.repositorios.RepositorioEmpleados;
import cursojava.spring.springboot.proyectos.repositorios.RepositorioImputaciones;
import cursojava.spring.springboot.proyectos.repositorios.RepositorioTareas;

@Service
@Transactional(rollbackFor = ServicioException.class)
public class ServicioImputacionesBean implements ServicioImputaciones {
	
	@Autowired
	private RepositorioEmpleados repoEmpleados;
	
	@Autowired
	private RepositorioTareas repoTareas;
	
	@Autowired
	private RepositorioImputaciones repoImputaciones;

	private Logger logger = LoggerFactory.getLogger(Controlador.class);
	
	@Override
	public void imputar(ImputacionDTO datos) throws ServicioException
	{
		try {
			Optional<Empleado> empleado = repoEmpleados.findById(datos.getEmpleado());
			Optional<Tarea> tarea = repoTareas.findById(datos.getTarea());
			
			if(empleado.isEmpty())
			{
				throw new ServicioException(new DatosError<>(ErroresDeServicio.EMPLEADO_NO_EXISTE, "No existe el empleado con este nif", datos.getEmpleado()));
			}
			
			if(tarea.isEmpty())
			{
				throw new ServicioException(new DatosError<>(ErroresDeServicio.TAREA_NO_EXISTE, "No existe la tarea con este id", datos.getTarea()));
			}
			
			if(!repoEmpleados.estaLaTareaAsignadaAEmpleado(datos.getEmpleado(), datos.getTarea()))
			{
				throw new ServicioException(new DatosError<>(ErroresDeServicio.TAREA_NO_ASIGNADA_A_EMPLEADO, "La tarea no está asignada a empleado", datos));
			}
			
			if(!repoTareas.estaFechaDentroDeRangoDeTarea(datos.getTarea(), datos.getFecha()))
			{
				throw new ServicioException(new DatosError<>(ErroresDeServicio.TAREA_FUERA_DE_RANGO_DE_FECHAS, "La fecha de la tarea esta fuera de rango de fechas", datos));
			}
			
			Imputacion imputacion = new Imputacion(empleado.get(), tarea.get(), datos.getFecha(), datos.getNumeroHoras(), datos.getDescripcion());
			
			repoImputaciones.save(imputacion);
		} catch (Exception e) {
			logger.error("No se pudo crear la imputacion", e);
			throw new ServicioException(new DatosError<ImputacionDTO>(ErroresDeServicio.IMPUTACION_NO_CREADA, "No se pudo crear la imputacion", datos), e);
		}
	}
	
	@Override
	public List<ImputacionDTO> buscarImputacionesPorTarea(Integer Id) throws ServicioException
	{
		try {
			Optional<Tarea> tarea = repoTareas.findById(Id);
			List<Imputacion> imputaciones = repoImputaciones.findByTarea(tarea.get());
			List<ImputacionDTO> imputacionesDTO = new ArrayList<ImputacionDTO>();
			for(Imputacion imputacion : imputaciones)
			{
				imputacionesDTO.add(entityToImputacionDTO(imputacion));
			}
			return imputacionesDTO;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private ImputacionDTO entityToImputacionDTO(Imputacion imputacion)
	{
		ImputacionDTO imputacionDTO = new ImputacionDTO();
		imputacionDTO.setEmpleado(imputacion.getEmpleado().getNombre() + " " + imputacion.getEmpleado().getApellidos());
		imputacionDTO.setDescripcion(imputacion.getDescripcion());
		imputacionDTO.setNumeroHoras(imputacion.getNumeroHoras());
		imputacionDTO.setFecha(imputacion.getFecha());
		return imputacionDTO;
	}

}
