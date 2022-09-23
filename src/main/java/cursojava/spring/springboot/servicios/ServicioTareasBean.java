package cursojava.spring.springboot.servicios;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cursojava.spring.springboot.dto.ProyectoDTO;
import cursojava.spring.springboot.dto.TareaDTO;
import cursojava.spring.springboot.proyectos.entidades.Proyecto;
import cursojava.spring.springboot.proyectos.entidades.Tarea;
import cursojava.spring.springboot.proyectos.repositorios.RepositorioProyectos;
import cursojava.spring.springboot.proyectos.repositorios.RepositorioTareas;

@Service
@Transactional(rollbackFor = ServicioException.class)
public class ServicioTareasBean implements ServicioTareas {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RepositorioProyectos repoProyectos;
	
	@Autowired
	private RepositorioTareas repoTareas;
	
	@Override
	public List<Tarea> getTareasDeProyecto(Integer id) throws ServicioException
	{
		try {
			Optional<Proyecto> proyecto = repoProyectos.findById(id);
			if(proyecto.isEmpty())
			{
				throw new ServicioException(new DatosError<>(ErroresDeServicio.PROYECTO_NO_EXISTE, "El proyecto no existe", proyecto));
			}
			return List.copyOf(proyecto.get().getTareas());
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
			if(proyecto.isEmpty())
			{
				throw new ServicioException(new DatosError<>(ErroresDeServicio.PROYECTO_NO_EXISTE, "El proyecto no existe", proyecto));
			}
			Tarea tarea = tareaDtoToEntity(tareaDto);
			tarea.setProyecto(proyecto.get());
			return repoTareas.save(tarea);
		} catch (ServicioException e ) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private Tarea tareaDtoToEntity(TareaDTO tarea)
	{
		return modelMapper.map(tarea, Tarea.class);
	}
}
