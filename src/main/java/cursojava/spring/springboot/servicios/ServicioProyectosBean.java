package cursojava.spring.springboot.servicios;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cursojava.spring.springboot.dto.ProyectoDTO;
import cursojava.spring.springboot.proyectos.entidades.Proyecto;
import cursojava.spring.springboot.proyectos.repositorios.RepositorioProyectos;

@Service
@Transactional(rollbackFor = ServicioException.class)
public class ServicioProyectosBean implements ServicioProyectos {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RepositorioProyectos repoProyectos;
	
	@Override
	public Proyecto crearProyecto(ProyectoDTO proyecto) throws ServicioException {
		try {
			if(proyecto.getFechaEstimadaCierre().before(new Date()))
			{
				throw new ServicioException(new DatosError<>(ErroresDeServicio.FECHA_FIN_ANTERIOR_A_HOY, "La fecha de fin es anterior al día de hoy", proyecto));
			}
			return repoProyectos.save(proyectoDtoToEntity(proyecto));
		} catch (ServicioException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}
	
	private Proyecto proyectoDtoToEntity(ProyectoDTO proyecto)
	{
		return modelMapper.map(proyecto, Proyecto.class);
	}

}
