package cursojava.spring.springboot.servicios;

import java.util.List;

import cursojava.spring.springboot.dto.AsignacionDTO;
import cursojava.spring.springboot.dto.TareaDTO;
import cursojava.spring.springboot.entidades.Tarea;
import cursojava.spring.springboot.excepciones.ServicioException;

public interface ServicioTareas {

	List<Tarea> getTareasDeProyecto(Integer id) throws ServicioException;
	
	Tarea altaTareaDeProyecto(TareaDTO tarea, Integer proyecto) throws ServicioException;

	void asignarTarea(AsignacionDTO asignacionDTO) throws ServicioException;

}