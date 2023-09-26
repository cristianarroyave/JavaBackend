package cursojava.spring.springboot.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cursojava.spring.springboot.entidades.Empleado;
import org.springframework.data.repository.query.Param;

public interface RepositorioEmpleados extends JpaRepository<Empleado, String>{

	@Query("SELECT  case WHEN (count(*)> 0) THEN true ELSE false END FROM Empleado emp WHERE emp.nif = :nif OR emp.correo = :correo ")
	Boolean existePorNifYCorreo(@Param("nif") String nif, @Param("correo") String correo);

	@Query("SELECT case WHEN (count(*) > 0) THEN true ELSE false END FROM Empleado emp JOIN emp.asignaciones tar WHERE emp.nif = ?1 and tar.codigo = ?2")
	Boolean tieneLaTareaAsignada(@Param("nif") String nif, @Param("codigo") Integer codigo);
}


