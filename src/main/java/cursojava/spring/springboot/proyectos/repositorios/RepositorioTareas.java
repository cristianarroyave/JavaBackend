package cursojava.spring.springboot.proyectos.repositorios;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import cursojava.spring.springboot.proyectos.entidades.Empleado;
import cursojava.spring.springboot.proyectos.entidades.Proyecto;
import cursojava.spring.springboot.proyectos.entidades.Tarea;

//public interface RepositorioProyectos extends Repository<Proyecto, Integer>{}

//public interface RepositorioProyectos extends CrudRepository<Proyecto, Integer>{}

//public interface RepositorioProyectos extends PagingAndSortingRepository<Proyecto, Integer>{}

public interface RepositorioTareas extends JpaRepository<Tarea, Integer>{
	
	@Query("SELECT case WHEN (count(*) > 0) THEN TRUE ELSE FALSE END FROM Tarea tar WHERE tar.codigo = ?1 and tar.fechaInicio <= ?2 and ?2 <= fechaFin")
	Boolean estaFechaDentroDeRangoDeTarea(Integer codigo, Date fechaTarea);
	
	List<Tarea> findByProyecto(Integer proyecto);
	
}


