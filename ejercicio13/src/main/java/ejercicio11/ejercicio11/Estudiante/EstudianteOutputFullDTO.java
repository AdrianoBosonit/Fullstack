package ejercicio11.ejercicio11.Estudiante;

import ejercicio11.ejercicio11.Persona.domain.PersonaEntity;
import lombok.Data;

@Data
public class EstudianteOutputFullDTO {
    private String idEstudiante;
    private PersonaEntity persona;
    private Integer numHoursWeek;
    private String coments;
    //ProfesorEntity profesor;
    private String branch;

    public EstudianteOutputFullDTO() {
    }

    public EstudianteOutputFullDTO(EstudianteEntity estudiante) {
        setIdEstudiante(estudiante.getIdEstudiante());
        setPersona(estudiante.getPersona());
        setNumHoursWeek(estudiante.getNumHoursWeek());
        setComents(estudiante.getComents());
        setBranch(estudiante.getBranch());
    }

    public void setEstudiante(EstudianteEntity estudiante){
        setIdEstudiante(estudiante.getIdEstudiante());
        setPersona(estudiante.getPersona());
        setNumHoursWeek(estudiante.getNumHoursWeek());
        setComents(estudiante.getComents());
        setBranch(estudiante.getBranch());
    }
}
