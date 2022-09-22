package cursojava.spring.springboot.servicios;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cursojava.spring.springboot.dto.ProyectoDTO;
import cursojava.spring.springboot.proyectos.entidades.Proyecto;

public interface ServicioProyectos {
	
	Proyecto crearProyecto(ProyectoDTO proyecto) throws ServicioException;
	
}
