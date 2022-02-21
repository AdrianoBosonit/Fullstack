package ejercicio11.ejercicio11.Estudiante;

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

}
