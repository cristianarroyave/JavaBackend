package cursojava.spring.springboot.servicios.impl;

import cursojava.spring.springboot.dto.registro.RegistroDTO;
import cursojava.spring.springboot.entidades.Empleado;
import cursojava.spring.springboot.excepciones.DatosError;
import cursojava.spring.springboot.excepciones.ErroresDeServicio;
import cursojava.spring.springboot.excepciones.ServicioException;
import cursojava.spring.springboot.repositorios.RepositorioEmpleados;
import cursojava.spring.springboot.servicios.ServicioEmpleados;
import cursojava.spring.springboot.servicios.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ServicioEmpleadosBean implements ServicioEmpleados {

    @Autowired
    private RepositorioEmpleados repositorioEmpleados;

    @Autowired
    private ServicioUsuarios servicioUsuarios;

    @Override
    public void registrarEmpleado(RegistroDTO registro) throws Exception {
        if(repositorioEmpleados.existePorNifYCorreo(registro.getNif(), registro.getCorreo()))
            throw new ServicioException(
                    new DatosError<>(ErroresDeServicio.NIF_O_CORREO_EXISTENTE,
                            "Ya existe un empleado con este nif o correo",
                            registro.getCorreo().concat(" ".concat(registro.getNif()))));


        Empleado empleado = registroToEmpleado(registro);

        empleado = repositorioEmpleados.save(empleado);

        if(registro.registroConUsername()) {
            servicioUsuarios.crearUsuario(empleado, registro);
        }

    }

    private Empleado registroToEmpleado(RegistroDTO registro) {
        Empleado empleado = new Empleado();
        empleado.setNif(registro.getNif());
        empleado.setNombre(registro.getNombre());
        empleado.setApellidos(registro.getApellidos());
        empleado.setCorreo(registro.getCorreo());

        return empleado;
    }
}
