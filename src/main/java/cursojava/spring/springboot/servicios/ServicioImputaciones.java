package cursojava.spring.springboot.servicios;

import java.util.List;

import cursojava.spring.springboot.dto.ImputacionDTO;
import cursojava.spring.springboot.excepciones.ServicioException;

public interface ServicioImputaciones {

	void imputar(ImputacionDTO datos) throws ServicioException;
	
	List<ImputacionDTO> buscarImputacionesPorTarea(Integer Id) throws ServicioException;

}