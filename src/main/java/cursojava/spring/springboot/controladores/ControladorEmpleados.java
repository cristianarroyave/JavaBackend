package cursojava.spring.springboot.controladores;


import cursojava.spring.springboot.dto.registro.RegistroDTO;
import cursojava.spring.springboot.servicios.ServicioEmpleados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleados")
public class ControladorEmpleados {

    @Autowired
    private ServicioEmpleados servicioEmpleados;

    @PostMapping(path = "/registro")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> registro(@RequestBody RegistroDTO registro) throws Exception {
        servicioEmpleados.registrarEmpleado(registro);

        return ResponseEntity.ok().build();
    }
}
