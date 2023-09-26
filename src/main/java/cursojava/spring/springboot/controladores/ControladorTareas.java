package cursojava.spring.springboot.controladores;

import cursojava.spring.springboot.dto.AsignacionDTO;
import cursojava.spring.springboot.dto.TareaDTO;
import cursojava.spring.springboot.entidades.Tarea;
import cursojava.spring.springboot.excepciones.ServicioException;
import cursojava.spring.springboot.servicios.ServicioTareas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tareas")
public class ControladorTareas {

    private Logger logger = LoggerFactory.getLogger(ControladorTareas.class);

    @Autowired
    private ServicioTareas srvTareas;

    @GetMapping(
            path = "/{codigoProyecto}"
    )
    public ResponseEntity<?> getTareasDeProyecto(@PathVariable(name = "codigoProyecto") Integer codigoProyecto)
    {
        try {
            List<Tarea> tareas = srvTareas.getTareasDeProyecto(codigoProyecto);
            return ResponseEntity.ok(tareas);
        } catch (ServicioException e) {
            logger.error("Error al obtener las tareas", e);
            return ResponseEntity.badRequest().body(e.getDatos());
        }
    }

    @PostMapping(
            path = "/{codigoProyecto}"
    )
    public ResponseEntity<?> altaTareaDeProyecto(@PathVariable(name = "codigoProyecto") Integer codigoProyecto, @RequestBody TareaDTO tareaDto)
    {
        try {
            Tarea tarea = srvTareas.altaTareaDeProyecto(tareaDto, codigoProyecto);
            return new ResponseEntity<Tarea>(tarea, HttpStatus.OK);
        } catch (ServicioException e) {
            logger.error("Error al crear una tarea", e);
            return ResponseEntity.badRequest().body(e.getDatos());
        }
    }

    @PostMapping(
            path = "/asignar"
    )
    public ResponseEntity<?> asignarTarea(@RequestBody AsignacionDTO asignacionDTO){
        try {
            srvTareas.asignarTarea(asignacionDTO);
            return ResponseEntity.ok().build();
        } catch (ServicioException e) {
            logger.error("Error al asignar una tarea", e);
            return ResponseEntity.badRequest().body(e.getDatos());
        }
    }
}
