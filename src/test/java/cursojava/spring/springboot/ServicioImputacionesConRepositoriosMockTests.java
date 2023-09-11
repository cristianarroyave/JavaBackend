package cursojava.spring.springboot;

import java.sql.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import cursojava.spring.springboot.dto.ImputacionDTO;
import cursojava.spring.springboot.entidades.Empleado;
import cursojava.spring.springboot.entidades.Tarea;
import cursojava.spring.springboot.repositorios.RepositorioEmpleados;
import cursojava.spring.springboot.repositorios.RepositorioImputaciones;
import cursojava.spring.springboot.repositorios.RepositorioTareas;
import cursojava.spring.springboot.servicios.ServicioImputacionesBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ServicioImputacionesConRepositoriosMockTests {

	@Mock
	private RepositorioEmpleados repoEmpleados;
	
	@Mock
	private RepositorioTareas repoTareas;
	
	@Mock
	private RepositorioImputaciones repoImputaciones;
	
	@InjectMocks
	private ServicioImputacionesBean srvImputaciones;
	
	@Test
	void probarRealizarEmpleacionConTodoOk()
	{
		ImputacionDTO imputacion = new ImputacionDTO("49532232F", 1, Date.valueOf("2022-01-01"), 2, "Programacion en JAVA");
		
		Mockito.when(repoEmpleados.findById("123456789F")).thenReturn(
				Optional.of(new Empleado()));
		
		Mockito.when(repoTareas.findById(2)).thenReturn(
				Optional.of(new Tarea()));
		Mockito.when(repoEmpleados.estaLaTareaAsignadaAEmpleado(Mockito.anyString(), Mockito.anyInt())).thenReturn(true);
		Mockito.when(repoTareas.estaFechaDentroDeRangoDeTarea(Mockito.anyInt(), Mockito.any())).thenReturn(true);
		
		assertDoesNotThrow(() -> {
			srvImputaciones.imputar(imputacion);
		});
	}
	
//	@Test
//	void probarRealizarImputacionTodoOk()
//	{
//		ImputacionDTO datos = new ImputacionDTO("49532232F", 1, Date.valueOf("2022-01-01"), 2, "Programacion en JAVA");
//		
//		Assertions.assertDoesNotThrow(() -> {
//			srvImputaciones.imputar(datos);
//		});
//		
//		List<Imputacion> imputaciones = repoImputaciones.buscarPorTodo(datos.getEmpleado(), datos.getTarea(), datos.getFecha());
//		
//		assertFalse(imputaciones.isEmpty());
//	}
//	
//	@Test
//	void probarRealizarImputacionSinEmpleado()
//	{
//		ImputacionDTO datos = new ImputacionDTO("NOEXISTE", 1, Date.valueOf("2022-01-01"), 2, "Programacion en JAVA");
//		
//		ServicioException e = assertThrows(ServicioException.class, () -> {
//			srvImputaciones.imputar(datos);
//		}, "Se ha podido realizar opreacion con el empleado que no existe");
//		
//		assertNotNull(e.getCause(), "No hay causa del error");
//		
//		assertInstanceOf(ServicioException.class, e.getCause(), "La causa no es del tipo ServicioException");
//		
//		ServicioException ex = (ServicioException)e.getCause();
//		
//		assertEquals(e.getDatos().getError(), ErroresDeServicio.IMPUTACION_NO_CREADA, "Código de error incorrecto");
//		
//	}
	
}
