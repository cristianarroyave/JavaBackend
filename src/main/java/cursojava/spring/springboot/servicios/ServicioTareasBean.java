package cursojava.spring.springboot.servicios;

import java.util.*;

import cursojava.spring.springboot.dto.AsignacionDTO;
import cursojava.spring.springboot.entidades.Empleado;
import cursojava.spring.springboot.repositorios.RepositorioEmpleados;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cursojava.spring.springboot.dto.TareaDTO;
import cursojava.spring.springboot.entidades.Proyecto;
import cursojava.spring.springboot.entidades.Tarea;
import cursojava.spring.springboot.repositorios.RepositorioProyectos;
import cursojava.spring.springboot.repositorios.RepositorioTareas;

@Service
@Transactional(rollbackFor = ServicioException.class)
public class ServicioTareasBean implements ServicioTareas {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RepositorioProyectos repoProyectos;

	@Autowired
	private RepositorioEmpleados repoEmpleados;
	
	@Autowired
	private RepositorioTareas repoTareas;
	
	@Override
	public List<Tarea> getTareasDeProyecto(Integer id) throws ServicioException
	{
		try {
			Optional<Proyecto> proyecto = repoProyectos.findById(id);
			if(!proyecto.isPresent())
			{
				throw new ServicioException(new DatosError<>(ErroresDeServicio.PROYECTO_NO_EXISTE, "El proyecto no existe", proyecto));
			}
			return new ArrayList<>(proyecto.get().getTareas());
		} catch (ServicioException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Tarea altaTareaDeProyecto(TareaDTO tareaDto, Integer codigoProyecto) throws ServicioException {
		try {
			Optional<Proyecto> proyecto = repoProyectos.findById(codigoProyecto);
			if(!proyecto.isPresent())
			{
				throw new ServicioException(new DatosError<>(ErroresDeServicio.PROYECTO_NO_EXISTE, "El proyecto no existe", proyecto));
			}
			Tarea tarea = tareaDtoToEntity(tareaDto);
			if(tarea.getFechaFin().before(tarea.getFechaInicio()))
			{
				throw new ServicioException(new DatosError<>(ErroresDeServicio.FECHA_FIN_ANTERIOR_A_FECHA_INICIO, "La fecha de fin es inferior a la fecha de inicio", proyecto));
			}
			if(tarea.getFechaFin().before(new Date()))
			{
				throw new ServicioException(new DatosError<>(ErroresDeServicio.FECHA_FIN_ANTERIOR_A_HOY, "La fecha de fin es anterior al dia de hoy", tarea));
			}
			tarea.setProyecto(proyecto.get());
			return repoTareas.save(tarea);
		} catch (ServicioException e ) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void asignarTarea(AsignacionDTO asignacionDTO) throws ServicioException {
		Optional<Empleado> optionalEmpleado = repoEmpleados.findById(asignacionDTO.getDniEmpleado());

		if(!optionalEmpleado.isPresent()) {
			throw new ServicioException(new DatosError<>(ErroresDeServicio.EMPLEADO_NO_EXISTE, "El empleado al que se le asigna la tarea no existe"));
		}

		Optional<Tarea> optionalTarea = repoTareas.findById(asignacionDTO.getIdTarea());

		if(!optionalTarea.isPresent()) {
			throw new ServicioException(new DatosError<>(ErroresDeServicio.TAREA_NO_EXISTE, "La tarea que se quiere asignar no existe"));
		}

		Empleado empleado = optionalEmpleado.get();

		Tarea tarea = optionalTarea.get();

		empleado.getAsignaciones().add(tarea);

		tarea.getEmpleados().add(empleado);

		repoEmpleados.save(empleado);
	}

	private Tarea tareaDtoToEntity(TareaDTO tarea)
	{
		return modelMapper.map(tarea, Tarea.class);
	}
}
