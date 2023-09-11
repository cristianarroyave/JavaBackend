package cursojava.spring.springboot.repositorios;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cursojava.spring.springboot.entidades.Empleado;

//public interface RepositorioProyectos extends Repository<Proyecto, Integer>{}

//public interface RepositorioProyectos extends CrudRepository<Proyecto, Integer>{}

//public interface RepositorioProyectos extends PagingAndSortingRepository<Proyecto, Integer>{}

public interface RepositorioEmpleados extends JpaRepository<Empleado, String>{
	//Consultas 
	
	// 1. Mediante metodos que emplean la nomenclatura de Spring Data.

	public List<Empleado> findByNombreAndApellidos(String nombre, String apellidos);
	
	public List<Empleado> findByNombreLikeOrderByNifDesc(String nombre);
	
	public Empleado findByCorreo(String correo);
	
	public List<Empleado> findByCorreoStartingWith(String correo, Sort orden);
	
	// 2. Mediante consultas JPAQL
	
	@Query("SELECT emp FROM Empleado emp WHERE emp.nombre LIKE ?1 OR emp.apellidos LIKE ?2")
	public List<Empleado> consultarPorNombre(String nombre, String apellidos);
	
	@Query(name = "Empleado.consultarEntreFechas")
	public List<Empleado> consultarEmpleadoPorAltaEntreFechas(@Param("fechaInicio") Date inicio,@Param("fechaFin") Date fin);
	
	// 3. Mediante SQL nativo
	@Query(nativeQuery = true, value = "SELECT * FROM MODELOPROYECTOS.EMPLEADOS WHERE DATE(FECHA_ALTA) = CURRENT_DATE")
	public List<Empleado> buscarAltasDelDia();
	
	@Query("SELECT case WHEN (count(*) > 0) THEN true ELSE false END FROM Empleado emp JOIN emp.asignaciones tar WHERE emp.nif = ?1 and tar.codigo = ?2")
	Boolean estaLaTareaAsignadaAEmpleado(String nif, Integer codigo);
}


