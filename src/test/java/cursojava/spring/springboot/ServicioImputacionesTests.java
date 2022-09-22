package cursojava.spring.springboot;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import cursojava.spring.springboot.dto.ImputacionDTO;
import cursojava.spring.springboot.proyectos.entidades.Imputacion;
import cursojava.spring.springboot.proyectos.repositorios.RepositorioImputaciones;
import cursojava.spring.springboot.servicios.ErroresDeServicio;
import cursojava.spring.springboot.servicios.ServicioException;
import cursojava.spring.springboot.servicios.ServicioImputaciones;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@Rollback
public class ServicioImputacionesTests {

	@Autowired
	private ServicioImputaciones srvImputaciones;
	
	@Autowired
	private RepositorioImputaciones repoImputaciones;
	
	@Test
	void probarRealizarEmpleacionConTodoOk()
	{
		ImputacionDTO imputacion = new ImputacionDTO("49532232F", 1, Date.valueOf("2022-01-01"), 2, "Programacion en JAVA");
		
		Assertions.assertDoesNotThrow(() -> {
			srvImputaciones.imputar(imputacion);
		}, "No se ha podido realizar la imputacion");
	}
	
	@Test
	void probarRealizarImputacionTodoOk()
	{
		ImputacionDTO datos = new ImputacionDTO("49532232F", 1, Date.valueOf("2022-01-01"), 2, "Programacion en JAVA");
		
		Assertions.assertDoesNotThrow(() -> {
			srvImputaciones.imputar(datos);
		});
		
		List<Imputacion> imputaciones = repoImputaciones.buscarPorTodo(datos.getEmpleado(), datos.getTarea(), datos.getFecha());
		
		assertFalse(imputaciones.isEmpty());
	}
	
	@Test
	void probarRealizarImputacionSinEmpleado()
	{
		ImputacionDTO datos = new ImputacionDTO("NOEXISTE", 1, Date.valueOf("2022-01-01"), 2, "Programacion en JAVA");
		
		ServicioException e = assertThrows(ServicioException.class, () -> {
			srvImputaciones.imputar(datos);
		}, "Se ha podido realizar opreacion con el empleado que no existe");
		
		assertNotNull(e.getCause(), "No hay causa del error");
		
		assertInstanceOf(ServicioException.class, e.getCause(), "La causa no es del tipo ServicioException");
		
		ServicioException ex = (ServicioException)e.getCause();
		
		assertEquals(e.getDatos().getError(), ErroresDeServicio.IMPUTACION_NO_CREADA, "Código de error incorrecto");
		
	}
	
}
