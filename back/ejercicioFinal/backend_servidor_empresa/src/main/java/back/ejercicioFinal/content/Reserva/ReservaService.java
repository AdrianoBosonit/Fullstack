package back.ejercicioFinal.content.Reserva;

public interface ReservaService {
    public ReservaOutputDto add(ReservaOutputDto reservaOutputDto);
    public ReservaOutputDto removeId(String id);
}
