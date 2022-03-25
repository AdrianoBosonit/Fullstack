package ejercicio18.Persona.repository;

import ejercicio18.Persona.domain.PersonaEntity;
import org.springframework.data.domain.Sort;
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

    public List<PersonaEntity> getData(HashMap<String, Object> conditions, Integer numPag, Integer tamPag);


}
