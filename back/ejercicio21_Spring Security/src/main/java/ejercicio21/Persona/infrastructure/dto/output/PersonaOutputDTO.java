package ejercicio21.Persona.infrastructure.dto.output;

import ejercicio21.Persona.domain.PersonaEntity;
import lombok.Data;

import java.util.Date;

@Data
public class PersonaOutputDTO {
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
    private Boolean admin;

    public PersonaOutputDTO() {

    }

    public PersonaOutputDTO(PersonaEntity persona) {
        setIdPersona(persona.getId());
        setPassword(persona.getPassword());
        setCity(persona.getCity());
        setUsuario(persona.getUsername());
        setName(persona.getName());
        setSurname(persona.getSurname());
        setActive(persona.getActive());
        setCompany_email(persona.getCompany_email());
        setPersonal_email(persona.getPersonal_email());
        setCreated_date(persona.getCreated_date());
        setTermination_date(persona.getTermination_date());
        setImagen_url(persona.getImagen_url());
        setAdmin(persona.getAdmin());
    }

}