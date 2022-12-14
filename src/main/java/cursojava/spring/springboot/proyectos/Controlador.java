package cursojava.spring.springboot.proyectos;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cursojava.spring.springboot.dto.ImputacionDTO;
import cursojava.spring.springboot.dto.ProyectoDTO;
import cursojava.spring.springboot.dto.TareaDTO;
import cursojava.spring.springboot.proyectos.entidades.Imputacion;
import cursojava.spring.springboot.proyectos.entidades.Proyecto;
import cursojava.spring.springboot.proyectos.entidades.Tarea;
import cursojava.spring.springboot.proyectos.repositorios.RepositorioEmpleados;
import cursojava.spring.springboot.proyectos.repositorios.RepositorioProyectos;
import cursojava.spring.springboot.proyectos.repositorios.RepositorioTareas;
import cursojava.spring.springboot.servicios.ServicioException;
import cursojava.spring.springboot.servicios.ServicioImputaciones;
import cursojava.spring.springboot.servicios.ServicioProyectos;
import cursojava.spring.springboot.servicios.ServicioTareas;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class Controlador {
	
	@Value("${cursojava.urlbase}")
	private String urlbase;
	
	private Logger logger = LoggerFactory.getLogger(Controlador.class);
	
	@Autowired
	private RepositorioProyectos repoProyectos;
	
	@Autowired
	private RepositorioEmpleados repoEmpleados;
	
	@Autowired
	private RepositorioTareas repoTareas;
	
	@Autowired
	private ServicioImputaciones srvImputacion;
	
	@Autowired
	private ServicioProyectos srvProyecto;
	
	@Autowired
	private ServicioTareas srvTareas;

	
	@GetMapping(
			path = "/proyectos"
			)
	public ResponseEntity<?> buscarProyectos()
	{
		try {
			List<Proyecto> findAll = repoProyectos.findAll();
			return ResponseEntity.ok(findAll);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PostMapping(
			path = "/proyectos"
			)
	public ResponseEntity<?> altaProyecto(@RequestBody ProyectoDTO proyecto)
	{
		try {
			Proyecto proyectoCreado = srvProyecto.crearProyecto(proyecto);
			return new ResponseEntity<Proyecto>(proyectoCreado, HttpStatus.CREATED);
		} catch (ServicioException e) {
			return ResponseEntity.badRequest().body(e.getDatos());
		}
	}
	
	@GetMapping(
			path = "/tareas/{codigoProyecto}"
			)
	public ResponseEntity<?> getTareasDeProyecto(@PathVariable(name = "codigoProyecto") Integer codigoProyecto)
	{
		try {
			List<Tarea> tareas = srvTareas.getTareasDeProyecto(codigoProyecto);
			return ResponseEntity.ok(tareas);
		} catch (ServicioException e) {
			return ResponseEntity.badRequest().body(e.getDatos());
		}
	}
	
	@PostMapping(
			path = "/tareas/{codigoProyecto}"
			)
	public ResponseEntity<?> altaTareaDeProyecto(@PathVariable(name = "codigoProyecto") Integer codigoProyecto, @RequestBody TareaDTO tareaDto)
	{
		try {
			Tarea tarea = srvTareas.altaTareaDeProyecto(tareaDto, codigoProyecto);
			return new ResponseEntity<Tarea>(tarea, HttpStatus.OK);
		} catch (ServicioException e) {
			return ResponseEntity.badRequest().body(e.getDatos());
		}
	}
	
	@GetMapping(
			path = "/imputaciones/{codigoTarea}"
			)
	public ResponseEntity<?> getImputacionesDeTarea(@PathVariable(name = "codigoTarea") Integer codigoTarea)
	{
		try {
			List<ImputacionDTO> imputaciones = srvImputacion.buscarImputacionesPorTarea(codigoTarea);
			return ResponseEntity.ok(imputaciones);
		} catch (ServicioException e) {
			return ResponseEntity.badRequest().body(e.getDatos());
		}
	}
}
