package cursojava.spring.springboot.controladores;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cursojava.spring.springboot.dto.ProyectoDTO;
import cursojava.spring.springboot.entidades.Proyecto;
import cursojava.spring.springboot.repositorios.RepositorioProyectos;
import cursojava.spring.springboot.excepciones.ServicioException;
import cursojava.spring.springboot.servicios.ServicioProyectos;

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
