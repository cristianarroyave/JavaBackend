package cursojava.spring.springboot.servicios.impl;

import cursojava.spring.springboot.dto.registro.RegistroDTO;
import cursojava.spring.springboot.entidades.Empleado;
import cursojava.spring.springboot.entidades.Usuario;
import cursojava.spring.springboot.repositorios.RepositorioEmpleados;
import cursojava.spring.springboot.repositorios.RepositorioUsuarios;
import cursojava.spring.springboot.servicios.ServicioUsuarios;
import cursojava.spring.springboot.util.EncriptacionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioUsuariosBean implements ServicioUsuarios {

    @Autowired
    private EncriptacionUtil encriptacionUtil;

    @Autowired
    private RepositorioUsuarios repositorioUsuarios;


    @Override
    public void crearUsuario(Empleado empleado, RegistroDTO registro) {
        Usuario usuario = new Usuario();
        usuario.setUsuario(registro.getUsuario());
        usuario.setPassword(encriptacionUtil.hashConSalt(registro.getPassword()));
        usuario.setActivo(true);
        usuario.setUsuarioEmpleado(empleado);

        repositorioUsuarios.save(usuario);
    }
}
