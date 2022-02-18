package ejercicio11.ejercicio11;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepo extends CrudRepository<PersonaEntity, Integer> {
    List<PersonaEntity> findAll();

    PersonaEntity saveAndFlush(PersonaEntity entity);

    Optional<PersonaEntity> findById(Integer id);

    void deleteById(Integer id);
}
