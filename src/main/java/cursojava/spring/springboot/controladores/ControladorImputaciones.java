package cursojava.spring.springboot.controladores;

import cursojava.spring.springboot.dto.ImputacionDTO;
import cursojava.spring.springboot.excepciones.ServicioException;
import cursojava.spring.springboot.servicios.ServicioImputaciones;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/imputaciones")
public class ControladorImputaciones {

    private Logger logger = LoggerFactory.getLogger(ControladorTareas.class);

    @Autowired
    private ServicioImputaciones srvImputacion;

    @GetMapping(
            path = "/{codigoTarea}"
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
