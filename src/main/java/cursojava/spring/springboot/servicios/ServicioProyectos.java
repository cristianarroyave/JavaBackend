package cursojava.spring.springboot.servicios;

import cursojava.spring.springboot.dto.ProyectoDTO;
import cursojava.spring.springboot.entidades.Proyecto;

public interface ServicioProyectos {
	
	Proyecto crearProyecto(ProyectoDTO proyecto) throws ServicioException;
	
}
