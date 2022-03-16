package ejercicio15.Persona.repository;

import ejercicio15.Persona.domain.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepo extends JpaRepository<PersonaEntity, Integer> {
    List<PersonaEntity> findAll();

    PersonaEntity saveAndFlush(PersonaEntity entity);

    Optional<PersonaEntity> findById(Integer id);

    void deleteById(Integer id);

    List<PersonaEntity> findByUsuario(String usuario);
}
