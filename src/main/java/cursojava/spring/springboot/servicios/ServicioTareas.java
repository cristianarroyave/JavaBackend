package cursojava.spring.springboot.servicios;

import java.util.List;

import cursojava.spring.springboot.proyectos.entidades.Tarea;

public interface ServicioTareas {

	List<Tarea> getTareasDeProyecto(Integer id);

}