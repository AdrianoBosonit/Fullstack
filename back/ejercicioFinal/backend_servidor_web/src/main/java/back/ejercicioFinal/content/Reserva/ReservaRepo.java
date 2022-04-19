package back.ejercicioFinal.content.Reserva;

import back.ejercicioFinal.content.Reserva.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepo extends JpaRepository<ReservaEntity, Integer> {
}
