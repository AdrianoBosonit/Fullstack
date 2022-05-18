package ejercicio21.Persona.domain;

import ejercicio21.Persona.infrastructure.dto.input.PersonaInputDTO;
import ejercicio21.exceptions.UnprocesableException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

@Data
@NoArgsConstructor
@Entity
@Table(name = "persona")
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Integer id;

    @Column
    private String username;

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

    @NotNull
    @Column
    private Boolean admin;



    public PersonaEntity(ResultSet rs) throws SQLException {
        setId(rs.getInt("id"));
        setPassword(  rs.getString("password"));
        setCity(rs.getString("city"));
        setUsername(rs.getString("usuario"));
        setName(rs.getString("name"));
        setSurname(rs.getString("surname"));
        setActive(rs.getBoolean("active"));
        setCompany_email(rs.getString("company_email"));
        setPersonal_email(rs.getString("personal_email"));
        setCreated_date(rs.getDate("created_date"));
        setTermination_date(rs.getDate("termination_date"));
        setImagen_url(rs.getString("imagen_url"));
    }

    public PersonaEntity(PersonaInputDTO personaDTO) throws Exception {
        if (personaDTO.getUsuario() == null) {
            throw new UnprocesableException("usuario no puede ser nulo");
        }
        if (personaDTO.getUsuario().length() < 6) {
            throw new UnprocesableException("longitud de usuario no puede ser menor que 6");
        }
        if (personaDTO.getUsuario().length() > 10) {
            throw new UnprocesableException("longitud de usuario no puede ser mayor que 10");
        }
        if (personaDTO.getCity() == null) {
            throw new UnprocesableException("city no puede ser nulo");
        }
        if (personaDTO.getPassword() == null) {
            throw new UnprocesableException("password no puede ser nulo");
        }
        if (personaDTO.getName() == null) {
            throw new UnprocesableException("name no puede ser nulo");
        }
        if (personaDTO.getCompany_email() == null) {
            throw new UnprocesableException("company_email no puede ser nulo");
        }
        if (personaDTO.getPersonal_email() == null) {
            throw new UnprocesableException("personal_email no puede ser nulo");
        }
        if (personaDTO.getActive() == null) {
            throw new UnprocesableException("active no puede ser nulo");
        }
        if (personaDTO.getCreated_date() == null) {
            throw new UnprocesableException("created_date no puede ser nulo");
        }
        setPassword(personaDTO.getPassword());
        setCity(personaDTO.getCity());
        setUsername(personaDTO.getUsuario());
        setName(personaDTO.getName());
        setSurname(personaDTO.getSurname());
        setActive(personaDTO.getActive());
        setCompany_email(personaDTO.getCompany_email());
        setPersonal_email(personaDTO.getPersonal_email());
        setCreated_date(personaDTO.getCreated_date());
        setTermination_date(personaDTO.getTermination_date());
        setImagen_url(personaDTO.getImagen_url());
        setAdmin(personaDTO.getAdmin());
    }

    public void modificar(PersonaInputDTO personaDTO) throws Exception {
        if (personaDTO.getUsuario().length() < 6) {
            throw new UnprocesableException("longitud de usuario no puede ser menor que 6. No se ha modificado nada");
        }
        if (personaDTO.getUsuario().length() > 10) {
            throw new UnprocesableException("longitud de usuario no puede ser mayor que 10. No se ha modificado nada");
        }
        setPassword(Optional.ofNullable(personaDTO.getPassword()).orElse(password));
        setCity(Optional.ofNullable(personaDTO.getCity()).orElse(city));
        setUsername(Optional.ofNullable(personaDTO.getUsuario()).orElse(username));
        setName(Optional.ofNullable(personaDTO.getName()).orElse(name));
        setSurname(Optional.ofNullable(personaDTO.getSurname()).orElse(surname));
        setActive(Optional.ofNullable(personaDTO.getActive()).orElse(active));
        setCompany_email(Optional.ofNullable(personaDTO.getCompany_email()).orElse(company_email));
        setPersonal_email(Optional.ofNullable(personaDTO.getPersonal_email()).orElse(personal_email));
        setCreated_date(Optional.ofNullable(personaDTO.getCreated_date()).orElse(created_date));
        setTermination_date(Optional.ofNullable(personaDTO.getTermination_date()).orElse(termination_date));
        setImagen_url(Optional.ofNullable(personaDTO.getImagen_url()).orElse(imagen_url));
    }


}
