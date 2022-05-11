package ejercicio20.Persona.repository;

import ejercicio20.Persona.domain.PersonaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface PersonaRepo extends MongoRepository<PersonaEntity, Integer> {
    @Override
    List<PersonaEntity> findAll();

    @Override
    <S extends PersonaEntity> S save(S entity);

    @Override
    Optional<PersonaEntity> findById(Integer s);

    @Override
    void deleteById(Integer s);

    List<PersonaEntity> findByUsuario(String usuario);


}
