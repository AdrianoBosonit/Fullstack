package back.ejercicioFinal.content.Reserva;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ReservaRepo extends JpaRepository<ReservaEntity, Integer> {
    ReservaEntity findByCiudadAndEmailAndFechaReservaAndHoraReserva(String ciudad, String email, Date fechaReserva, Float horaReserva);
}
