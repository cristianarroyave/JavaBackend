package cursojava.spring.springboot.proyectos.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import cursojava.spring.springboot.proyectos.entidades.Proyecto;

public interface RepositorioProyectos extends JpaRepository<Proyecto, Integer>{}


