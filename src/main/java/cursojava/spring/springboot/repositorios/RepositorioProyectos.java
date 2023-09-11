package cursojava.spring.springboot.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import cursojava.spring.springboot.entidades.Proyecto;

public interface RepositorioProyectos extends JpaRepository<Proyecto, Integer>{}


