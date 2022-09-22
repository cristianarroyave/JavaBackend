package cursojava.spring.springboot.servicios;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cursojava.spring.springboot.dto.ImputacionDTO;

public interface ServicioImputaciones {

	void imputar(ImputacionDTO datos) throws ServicioException;

}