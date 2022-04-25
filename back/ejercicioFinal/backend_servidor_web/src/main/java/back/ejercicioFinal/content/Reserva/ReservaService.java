package back.ejercicioFinal.content.Reserva;

import java.util.List;

public interface ReservaService {
    public ReservaOutputDto addAndSend(ReservaInputDto reservaInputDto) throws Exception;

    public List<ReservaEntity> findAll();

    ReservaEntity add(ReservaEntity reservaEntity);
}
