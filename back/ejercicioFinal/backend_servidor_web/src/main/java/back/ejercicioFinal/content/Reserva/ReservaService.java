package back.ejercicioFinal.content.Reserva;

import java.util.List;

public interface ReservaService {
    public ReservaOutputDto addAndSend(ReservaInputDto reservaInputDto) throws Exception;

    public List<ReservaEntity> findAll();

    ReservaOutputDto removeId(String id);

    ReservaEntity add(ReservaEntity reservaEntity);

    void updateReservaFromEmpresa(ReservaEntity reservaEntity);
}
