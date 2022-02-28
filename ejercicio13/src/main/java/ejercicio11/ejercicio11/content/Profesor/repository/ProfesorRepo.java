package ejercicio11.ejercicio11.content.Profesor.repository;

import ejercicio11.ejercicio11.content.Persona.domain.PersonaEntity;
import ejercicio11.ejercicio11.content.Profesor.domain.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesorRepo extends JpaRepository<ProfesorEntity, String> {
    List<ProfesorEntity> findAll();

    ProfesorEntity saveAndFlush(ProfesorEntity entity);

    Optional<ProfesorEntity> findById(String id);

    void deleteById(String id);

    Optional<ProfesorEntity> findByPersona(PersonaEntity persona);

}
