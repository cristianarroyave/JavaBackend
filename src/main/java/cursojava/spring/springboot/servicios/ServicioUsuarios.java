package cursojava.spring.springboot.servicios;

import cursojava.spring.springboot.dto.registro.RegistroDTO;
import cursojava.spring.springboot.entidades.Empleado;

public interface ServicioUsuarios {
    public void crearUsuario(Empleado empleado, RegistroDTO registro);
}
