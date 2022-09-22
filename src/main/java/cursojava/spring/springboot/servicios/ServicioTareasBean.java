package cursojava.spring.springboot.servicios;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cursojava.spring.springboot.proyectos.entidades.Proyecto;
import cursojava.spring.springboot.proyectos.entidades.Tarea;
import cursojava.spring.springboot.proyectos.repositorios.RepositorioProyectos;

@Service
@Transactional(rollbackFor = ServicioException.class)
public class ServicioTareasBean implements ServicioTareas {
	
	@Autowired
	private RepositorioProyectos repoProyectos;
	
	@Override
	public List<Tarea> getTareasDeProyecto(Integer id)
	{
		Optional<Proyecto> proyecto = repoProyectos.findById(id);
		
		return List.copyOf(proyecto.get().getTareas());
	}
}
