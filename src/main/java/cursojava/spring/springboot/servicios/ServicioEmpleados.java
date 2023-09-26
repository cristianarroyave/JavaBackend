package cursojava.spring.springboot.servicios;

import cursojava.spring.springboot.dto.registro.RegistroDTO;

public interface ServicioEmpleados {
    public void registrarEmpleado(RegistroDTO registro) throws Exception;
}
