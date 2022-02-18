package ejercicio11.ejercicio11.Persona.infrastructure.dto.output;

import ejercicio11.ejercicio11.Persona.domain.PersonaEntity;
import lombok.Data;

import java.util.Date;

@Data
public class PersonaOutputDTO {
    private Integer id;
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

    public PersonaOutputDTO() {

    }

    public PersonaOutputDTO(PersonaEntity persona) {
        setId(persona.getId());
        setPassword(persona.getPassword());
        setCity(persona.getCity());
        setUsuario(persona.getUsuario());
        setName(persona.getName());
        setSurname(persona.getSurname());
        setActive(persona.getActive());
        setCompany_email(persona.getCompany_email());
        setPersonal_email(persona.getPersonal_email());
        setCreated_date(persona.getCreated_date());
        setTermination_date(persona.getTermination_date());
        setImagen_url(persona.getImagen_url());

    }

}
