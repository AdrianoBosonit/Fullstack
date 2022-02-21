package ejercicio11.ejercicio11.Estudiante;

import ejercicio11.ejercicio11.Persona.domain.PersonaEntity;
import lombok.Data;

@Data
public class EstudianteOutputSimpleDTO {
    private String idEstudiante;
    private Integer idPersona;
    private String nombre;
    private String apellido;
    private Integer numHoursWeek;
    private String coments;
    //ProfesorEntity profesor;
    private String branch;

    public EstudianteOutputSimpleDTO() {
    }

    public EstudianteOutputSimpleDTO(EstudianteEntity estudiante) {
        setIdEstudiante(estudiante.getIdEstudiante());
        setNombre(estudiante.getPersona().getName());
        setApellido(estudiante.getPersona().getSurname());
        setIdPersona(estudiante.getPersona().getIdPersona());
        setNumHoursWeek(estudiante.getNumHoursWeek());
        setComents(estudiante.getComents());
        setBranch(estudiante.getBranch());
    }

    public void setEstudiante(EstudianteEntity estudiante){
        setIdEstudiante(estudiante.getIdEstudiante());
        setNombre(estudiante.getPersona().getName());
        setApellido(estudiante.getPersona().getSurname());
        setIdPersona(estudiante.getPersona().getIdPersona());
        setNumHoursWeek(estudiante.getNumHoursWeek());
        setComents(estudiante.getComents());
        setBranch(estudiante.getBranch());
    }
}
