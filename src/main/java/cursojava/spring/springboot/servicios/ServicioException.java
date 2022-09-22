package cursojava.spring.springboot.servicios;

public class ServicioException extends Exception {
	
	private DatosError<?> datos;

	public ServicioException(DatosError<?> datos) {
		this.datos = datos;
	}

	public ServicioException(DatosError<?> datos, Throwable cause) {
		super(cause);
		this.datos = datos;
	}

	public DatosError<?> getDatos() {
		return datos;
	}	
}
