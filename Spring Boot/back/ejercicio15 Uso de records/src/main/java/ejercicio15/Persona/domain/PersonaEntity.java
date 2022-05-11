package ejercicio15.Persona.domain;

import ejercicio15.Persona.infrastructure.dto.input.PersonaInputDTO;
import ejercicio15.exceptions.UnprocesableException;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

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
        if (personaDTO.usuario() == null) {
            throw new UnprocesableException("usuario no puede ser nulo");
        }
        if (personaDTO.usuario().length() < 6) {
            throw new UnprocesableException("longitud de usuario no puede ser menor que 6");
        }
        if (personaDTO.usuario().length() > 10) {
            throw new UnprocesableException("longitud de usuario no puede ser mayor que 10");
        }
        if (personaDTO.city() == null) {
            throw new UnprocesableException("city no puede ser nulo");
        }
        if (personaDTO.password() == null) {
            throw new UnprocesableException("password no puede ser nulo");
        }
        if (personaDTO.name() == null) {
            throw new UnprocesableException("name no puede ser nulo");
        }
        if (personaDTO.company_email() == null) {
            throw new UnprocesableException("company_email no puede ser nulo");
        }
        if (personaDTO.personal_email() == null) {
            throw new UnprocesableException("personal_email no puede ser nulo");
        }
        if (personaDTO.active() == null) {
            throw new UnprocesableException("active no puede ser nulo");
        }
        if (personaDTO.created_date() == null) {
            throw new UnprocesableException("created_date no puede ser nulo");
        }
        setId(personaDTO.id());
        setPassword(personaDTO.password());
        setCity(personaDTO.city());
        setUsuario(personaDTO.usuario());
        setName(personaDTO.name());
        setSurname(personaDTO.surname());
        setActive(personaDTO.active());
        setCompany_email(personaDTO.company_email());
        setPersonal_email(personaDTO.personal_email());
        setCreated_date(personaDTO.created_date());
        setTermination_date(personaDTO.termination_date());
        setImagen_url(personaDTO.imagen_url());
    }

    public void modificar(PersonaInputDTO personaDTO) throws Exception {
        if (personaDTO.usuario().length() < 6) {
            throw new UnprocesableException("longitud de usuario no puede ser menor que 6. No se ha modificado nada");
        }
        if (personaDTO.usuario().length() > 10) {
            throw new UnprocesableException("longitud de usuario no puede ser mayor que 10. No se ha modificado nada");
        }
        setPassword(Optional.ofNullable(personaDTO.password()).orElse(password));
        setCity(Optional.ofNullable(personaDTO.city()).orElse(city));
        setUsuario(Optional.ofNullable(personaDTO.usuario()).orElse(usuario));
        setName(Optional.ofNullable(personaDTO.name()).orElse(name));
        setSurname(Optional.ofNullable(personaDTO.surname()).orElse(surname));
        setActive(Optional.ofNullable(personaDTO.active()).orElse(active));
        setCompany_email(Optional.ofNullable(personaDTO.company_email()).orElse(company_email));
        setPersonal_email(Optional.ofNullable(personaDTO.personal_email()).orElse(personal_email));
        setCreated_date(Optional.ofNullable(personaDTO.created_date()).orElse(created_date));
        setTermination_date(Optional.ofNullable(personaDTO.termination_date()).orElse(termination_date));
        setImagen_url(Optional.ofNullable(personaDTO.imagen_url()).orElse(imagen_url));
    }
}
