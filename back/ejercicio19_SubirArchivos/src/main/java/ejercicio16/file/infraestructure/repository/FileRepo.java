package ejercicio16.file.infraestructure.repository;

import ejercicio16.Persona.domain.PersonaEntity;
import ejercicio16.file.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepo  extends JpaRepository<File, Integer> {
    List<File> findByName(String name);
    Optional<File> findById(Integer id);
    List<File> findAll();
}
