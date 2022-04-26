package back.ejercicioFinal.content.Reserva;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface ReservaRepo extends JpaRepository<ReservaEntity, String> {
    ReservaEntity findByCiudadAndEmailAndFechaReservaAndHoraReserva(String ciudad, String email, Date fechaReserva, Float horaReserva);
    Optional<ReservaEntity> findById(String id);
}
