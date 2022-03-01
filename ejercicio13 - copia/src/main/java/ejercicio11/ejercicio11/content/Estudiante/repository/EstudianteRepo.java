package ejercicio11.ejercicio11.content.Estudiante.repository;

import ejercicio11.ejercicio11.content.Estudiante.domain.EstudianteEntity;
import ejercicio11.ejercicio11.content.Persona.domain.PersonaEntity;
import ejercicio11.ejercicio11.content.Profesor.domain.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepo  extends JpaRepository<EstudianteEntity, String> {
    List<EstudianteEntity> findAll();

    EstudianteEntity saveAndFlush(EstudianteEntity entity);

    Optional<EstudianteEntity> findById(String id);

    void deleteById(String id);

    Optional<EstudianteEntity> findByPersona(PersonaEntity persona);

}
