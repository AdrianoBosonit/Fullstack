package back.ejercicioFinal.content.Reserva;

import back.ejercicioFinal.content.Correo.CorreoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepo extends JpaRepository<ReservaEntity, String> {
    List<ReservaEntity> findByCorreo(CorreoEntity correo);
}
