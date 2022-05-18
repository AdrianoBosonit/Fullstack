package ejercicio11.ejercicio11.content.Asignatura.repository;

import ejercicio11.ejercicio11.content.Asignatura.domain.EstudianteAsignaturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteAsignaturaRepo extends JpaRepository<EstudianteAsignaturaEntity, String> {
    List<EstudianteAsignaturaEntity> findAll();

    EstudianteAsignaturaEntity saveAndFlush(EstudianteAsignaturaEntity entity);

    Optional<EstudianteAsignaturaEntity> findById(String id);

    void deleteById(String id);

}
