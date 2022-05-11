package back.ejercicioFinal.content.Correo.repository;

import back.ejercicioFinal.content.Correo.domain.CorreoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface CorreoRepo extends JpaRepository<CorreoEntity, String> {

    List<CorreoEntity> findByCiudadAndFechaReservaAndHoraReserva(String ciudad, Date fechaReserva, Float horaReserva);

    @Query("SELECT c FROM CorreoEntity c WHERE c.ciudad =?1 AND (c.fechaReserva BETWEEN ?2 AND ?3) AND (c.horaReserva BETWEEN ?4 AND ?5)")
    List<CorreoEntity> findByciudadAndFechaReservaBetweenAndHoraReservaBetween(String ciudad, Date startDate, Date endDate, Float startHora, Float endHora);

}
