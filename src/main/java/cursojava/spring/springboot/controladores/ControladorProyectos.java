package cursojava.spring.springboot.controladores;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cursojava.spring.springboot.dto.ImputacionDTO;
import cursojava.spring.springboot.dto.ProyectoDTO;
import cursojava.spring.springboot.dto.TareaDTO;
import cursojava.spring.springboot.entidades.Proyecto;
import cursojava.spring.springboot.entidades.Tarea;
import cursojava.spring.springboot.repositorios.RepositorioEmpleados;
import cursojava.spring.springboot.repositorios.RepositorioProyectos;
import cursojava.spring.springboot.repositorios.RepositorioTareas;
import cursojava.spring.springboot.servicios.ServicioException;
import cursojava.spring.springboot.servicios.ServicioImputaciones;
import cursojava.spring.springboot.servicios.ServicioProyectos;
import cursojava.spring.springboot.servicios.ServicioTareas;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/proyectos")
public class ControladorProyectos {

	private Logger logger = LoggerFactory.getLogger(ControladorProyectos.class);
	
	@Autowired
	private RepositorioProyectos repoProyectos;

	@Autowired
	private ServicioProyectos srvProyecto;

	
	@GetMapping
	public ResponseEntity<?> buscarProyectos()
	{
		try {
			List<Proyecto> findAll = repoProyectos.findAll();
			return ResponseEntity.ok(findAll);
		} catch (Exception e) {
			logger.error("Error al encontrar los proyectos", e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> altaProyecto(@RequestBody ProyectoDTO proyecto)
	{
		try {
			Proyecto proyectoCreado = srvProyecto.crearProyecto(proyecto);
			return new ResponseEntity<Proyecto>(proyectoCreado, HttpStatus.CREATED);
		} catch (ServicioException e) {
			logger.error("Error al dar de alta el proyecto", e);
			return ResponseEntity.badRequest().body(e.getDatos());
		}
	}
}
