package back.ejercicioFinal.content.Reserva;

import back.ejercicioFinal.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaServiceImpl implements ReservaService {
    @Autowired
    ReservaRepo reservaRepo;

    @Override
    public ReservaOutputDto add(ReservaInputDto reservaInputDto) {
        try {
            return new ReservaOutputDto(reservaRepo.saveAndFlush(new ReservaEntity(reservaInputDto)));
        } catch (Exception e) {
            System.out.println(e);
            throw new BadRequestException("Reserva invalida");
        }

    }
}
