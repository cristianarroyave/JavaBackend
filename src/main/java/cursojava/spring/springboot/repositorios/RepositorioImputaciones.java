package cursojava.spring.springboot.repositorios;

import java.util.Date;
import java.util.List;

import cursojava.spring.springboot.entidades.Imputacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cursojava.spring.springboot.entidades.Tarea;

//public interface RepositorioProyectos extends Repository<Proyecto, Integer>{}

//public interface RepositorioProyectos extends CrudRepository<Proyecto, Integer>{}

//public interface RepositorioProyectos extends PagingAndSortingRepository<Proyecto, Integer>{}

public interface RepositorioImputaciones extends JpaRepository<Imputacion, Integer>{
	
	@Query("select imp from Imputacion imp WHERE imp.empleado.nif = ?1 AND imp.tarea.codigo = ?2 AND imp.fecha = ?3")
	List<Imputacion> buscarPorTodo(String nifEmpleado, Integer codigoTarea, Date fecha);
	
	List<Imputacion> findByTarea(Tarea tarea);
	
}


