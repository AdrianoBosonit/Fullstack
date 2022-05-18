package back.ejercicioFinal.content.Reserva.repository;

import back.ejercicioFinal.content.Reserva.domain.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepo extends JpaRepository<ReservaEntity, String> {
    ReservaEntity findByCiudadAndEmailAndFechaReservaAndHoraReserva(String ciudad, String email, Date fechaReserva, Float horaReserva);

    Optional<ReservaEntity> findById(String id);

    @Query(value = "SELECT * FROM reserva r WHERE r.fecha_reserva<=:fechaSuperior AND r.fecha_reserva>=:fechaInferior" +
            " AND r.hora_reserva<=:horaSuperior AND r.hora_reserva>=:horaInferior AND r.ciudad = :ciudadDestino"
            , nativeQuery = true)
    List<ReservaEntity> findReservaConRangos(@Param("ciudadDestino") String ciudadDestino, @Param("fechaSuperior") Date fechaSuperior, @Param("fechaInferior") Date fechaInferior
            , @Param("horaInferior") Float horaInferior, @Param("horaSuperior") Float horaSuperior);

}
