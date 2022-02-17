package ejercicio11.ejercicio11;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Persona")
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Integer id;

    @Column
    private String usuario;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String company_email;

    @Column
    private String personal_email;

    @Column
    private String city;

    @Column
    private Boolean active;

    @Column
    private Date created_date;

    @Column
    private String imagen_url;

    @Column
    private Date termination_date;


    public PersonaEntity() {

    }

    public PersonaEntity(PersonaInputDTO personaDTO) throws Exception {
            if (personaDTO.getUsuario() == null) {
                throw new Exception("usuario no puede ser nulo");
            }
            if (personaDTO.getUsuario().length() < 6) {
                throw new Exception("longitud de usuario no puede ser menor que 6");
            }
            if (personaDTO.getUsuario().length() > 10) {
                throw new Exception("longitud de usuario no puede ser mayor que 10");
            }
            if (personaDTO.getCity() == null) {
                throw new Exception("city no puede ser nulo");
            }
            if (personaDTO.getPassword() == null) {
                throw new Exception("password no puede ser nulo");
            }
            if (personaDTO.getName() == null) {
                throw new Exception("name no puede ser nulo");
            }
            if (personaDTO.getCompany_email()== null) {
                throw new Exception("company_email no puede ser nulo");
            }
            if (personaDTO.getPersonal_email() == null) {
                throw new Exception("personal_email no puede ser nulo");
            }
            if (personaDTO.getActive() == null) {
                throw new Exception("active no puede ser nulo");
            }
            if (personaDTO.getCreated_date() == null) {
                throw new Exception("created_date no puede ser nulo");
            }
            setId(personaDTO.getId());
            setPassword(personaDTO.getPassword());
            setCity(personaDTO.getCity());
            setUsuario(personaDTO.getUsuario());
            setName(personaDTO.getName());
            setSurname(personaDTO.getSurname());
            setActive(personaDTO.getActive());
            setCompany_email(personaDTO.getCompany_email());
            setPersonal_email(personaDTO.getPersonal_email());
            setCreated_date(personaDTO.getCreated_date());
            setTermination_date(personaDTO.getTermination_date());
            setImagen_url(personaDTO.getImagen_url());
    }
}
