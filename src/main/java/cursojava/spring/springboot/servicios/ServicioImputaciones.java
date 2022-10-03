package cursojava.spring.springboot.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cursojava.spring.springboot.dto.ImputacionDTO;
import cursojava.spring.springboot.proyectos.entidades.Imputacion;

public interface ServicioImputaciones {

	void imputar(ImputacionDTO datos) throws ServicioException;
	
	List<ImputacionDTO> buscarImputacionesPorTarea(Integer Id) throws ServicioException;

}