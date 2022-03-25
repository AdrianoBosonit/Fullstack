package ejercicio18.Persona.infrastructure.dto.output;

import java.util.Date;
public record PersonaOutputDTO (   Integer id,
 String usuario,
 String password,
 String name,
 String surname,
 String company_email,
 String personal_email,
 String city,
 Boolean active,
 Date created_date,
 String imagen_url,
 Date termination_date){}

