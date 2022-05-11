package ejercicio11.ejercicio11.content.Estudiante.infrastructure.dto.output;

import ejercicio11.ejercicio11.content.Estudiante.domain.EstudianteEntity;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Data
@Component
public class EstudianteOutputFullDTO extends EstudianteOutputSimpleDTO {
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


    public EstudianteOutputFullDTO() {
    }

    public EstudianteOutputFullDTO(EstudianteEntity estudiante) {
        super(estudiante);
        if (Optional.ofNullable(estudiante.getPersona()).isPresent()) {
            setPassword(estudiante.getPersona().getPassword());
            setCity(estudiante.getPersona().getCity());
            setUsuario(estudiante.getPersona().getUsuario());
            setName(estudiante.getPersona().getName());
            setSurname(estudiante.getPersona().getSurname());
            setActive(estudiante.getPersona().getActive());
            setCompany_email(estudiante.getPersona().getCompany_email());
            setPersonal_email(estudiante.getPersona().getPersonal_email());
            setCreated_date(estudiante.getPersona().getCreated_date());
            setTermination_date(estudiante.getPersona().getTermination_date());
            setImagen_url(estudiante.getPersona().getImagen_url());
        }
    }

}
