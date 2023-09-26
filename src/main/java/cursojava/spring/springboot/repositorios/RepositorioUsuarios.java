package cursojava.spring.springboot.repositorios;


import cursojava.spring.springboot.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioUsuarios extends JpaRepository<Usuario, Integer> {
    @Query("SELECT u FROM Usuario u WHERE u.usuario = :usuario")
    Optional<Usuario> findByUsuario(String usuario);
}
