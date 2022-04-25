package back.ejercicioFinal.content.Autobus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AutobusRepo extends JpaRepository<AutobusEntity, String> {

    List<AutobusEntity> findByCiudadDestinoAndFechaSalidaAndHoraSalida(String ciudadDestino, Date fechaSalida, Float horaSalida);
}
