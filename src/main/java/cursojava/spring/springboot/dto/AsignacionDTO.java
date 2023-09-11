package cursojava.spring.springboot.dto;

import cursojava.spring.springboot.validaciones.Nif;

public class AsignacionDTO {
    private Integer idTarea;
    @Nif
    private String dniEmpleado;

    public Integer getIdTarea() {
        return idTarea;
    }

    public String getDniEmpleado() {
        return dniEmpleado;
    }

}
