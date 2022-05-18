package ejercicio11.ejercicio11.content.Persona.infrastructure.dto.input;

import ejercicio11.ejercicio11.content.Estudiante.domain.EstudianteEntity;
import ejercicio11.ejercicio11.content.Profesor.domain.ProfesorEntity;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Data
public class PersonaInputDTO {
    private Integer idPersona;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private Date created_date;
    private String imagen_url;
    private Date termination_date;
    private String idProfesor;
    private String idEstudiante;


}
