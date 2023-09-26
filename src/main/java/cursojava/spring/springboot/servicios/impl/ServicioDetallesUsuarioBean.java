package cursojava.spring.springboot.servicios.impl;

import cursojava.spring.springboot.entidades.Usuario;
import cursojava.spring.springboot.excepciones.DatosError;
import cursojava.spring.springboot.repositorios.RepositorioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ServicioDetallesUsuarioBean implements UserDetailsService {

    @Autowired
    private RepositorioUsuarios repositorioUsuarios;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuarioOptional = repositorioUsuarios.findByUsuario(username);

        if(!usuarioOptional.isPresent()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        } else if (!usuarioOptional.get().getActivo()) {
            throw new UsernameNotFoundException("Usuario no activo");
        }

        return new User(usuarioOptional.get().getUsuario(), usuarioOptional.get().getPassword(), new ArrayList<>());
    }
}
