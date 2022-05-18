package ejercicio16.Persona.repository;

import ejercicio16.Persona.domain.PersonaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepo extends JpaRepository<PersonaEntity, Integer> {
    List<PersonaEntity> findAll();

    PersonaEntity saveAndFlush(PersonaEntity entity);

    Optional<PersonaEntity> findById(Integer id);

    void deleteById(Integer id);

    List<PersonaEntity> findByUsuario(String usuario);

    public List<PersonaEntity> getData(HashMap<String, Object> conditions,Integer numPag,Integer tamPag);
}
