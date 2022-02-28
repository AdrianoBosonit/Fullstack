package ejercicio11.ejercicio11.content.Persona.infrastructure.dto.output;

import ejercicio11.ejercicio11.content.Asignatura.domain.EstudianteAsignaturaEntity;
import ejercicio11.ejercicio11.content.Estudiante.domain.EstudianteEntity;
import ejercicio11.ejercicio11.content.Persona.domain.PersonaEntity;
import ejercicio11.ejercicio11.content.Profesor.domain.ProfesorEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
public class PersonaOutputDTOFullEstudiante extends PersonaOutputDTO {
    private String idEstudiante;
    private Integer numHoursWeek;
    private String coments;
    private String idProfesor;
    private String branch;
    private List<EstudianteAsignaturaEntity> estudios;

    public PersonaOutputDTOFullEstudiante() {

    }

    public PersonaOutputDTOFullEstudiante(PersonaEntity persona, EstudianteEntity estudiante) {
        super(persona);
        if (Optional.ofNullable(estudiante).isPresent()) {
            setIdEstudiante(estudiante.getIdEstudiante());
            if (Optional.ofNullable(estudiante.getProfesor()).isPresent()) {
                setIdProfesor(estudiante.getProfesor().getIdProfesor());
            }
            setNumHoursWeek(estudiante.getNumHoursWeek());
            setComents(estudiante.getComents());
            setBranch(estudiante.getBranch());
            setEstudios(estudiante.getEstudios());
        }
    }

}
