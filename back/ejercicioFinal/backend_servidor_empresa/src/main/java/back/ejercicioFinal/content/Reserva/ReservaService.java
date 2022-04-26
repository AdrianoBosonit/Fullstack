package back.ejercicioFinal.content.Reserva;

import java.util.List;

public interface ReservaService {
    public ReservaOutputDto addAndSend(ReservaInputDto reservaInputDto) throws Exception;

    public ReservaOutputDto removeId(String id);

    void removeBus(String id);

    public List<ReservaEntity> findAll();

    ReservaEntity add(ReservaEntity reservaEntity);
}
