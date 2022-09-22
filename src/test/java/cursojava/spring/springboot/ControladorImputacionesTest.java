package cursojava.spring.springboot;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cursojava.spring.springboot.dto.ImputacionDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ControladorImputacionesTest {

	@LocalServerPort
	private int puerto;
	
	@Autowired
	private TestRestTemplate rt;
	
	private static String url;
	
	@BeforeEach
	void inicializar()
	{
		url = String.format("http://localhost:%d/imputar", puerto);
	}
	
	@Test
	void test() {
		
		ImputacionDTO imputacion = new ImputacionDTO("49532232F", 1, Date.valueOf("2022-01-01"), 2, "Programacion en JAVA");
		
		ResponseEntity<Void> response = rt.postForEntity(url, imputacion, Void.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode(), "No es el codigo que se esperaba de respuesta");
	}

}
