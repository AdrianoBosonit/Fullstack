package back.ejercicioFinal.content.Reserva;

import back.ejercicioFinal.content.Autobus.AutobusEntity;
import back.ejercicioFinal.content.Autobus.AutobusRepo;
import back.ejercicioFinal.content.Correo.CorreoInputDto;
import back.ejercicioFinal.content.Correo.CorreoRepo;
import back.ejercicioFinal.exception.BadRequestException;
import back.ejercicioFinal.shared.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    ReservaRepo reservaRepo;

    @Autowired
    AutobusRepo autobusRepo;

    @Autowired
    CorreoRepo correoRepo;

    @Autowired
    MailSender mailSender;

    @Override
    public ReservaOutputDto add(ReservaOutputDto reservaOutputDto) {
        try {
            List<AutobusEntity> listaAutobuses = autobusRepo.findByCiudadDestinoAndFechaSalidaAndHoraSalida(reservaOutputDto.getCiudadDestino(), reservaOutputDto.getFechaReserva(), reservaOutputDto.getHoraReserva());
            AutobusEntity autobusEntity;
            if (listaAutobuses.isEmpty()) {
                autobusEntity = new AutobusEntity(reservaOutputDto);
                autobusRepo.saveAndFlush(autobusEntity);
            } else {
                autobusEntity = listaAutobuses.get(0);
            }
            CorreoInputDto correoInputDto=new CorreoInputDto(reservaOutputDto);
            ReservaEntity reservaEntity = new ReservaEntity(reservaOutputDto, autobusEntity,correoRepo.saveAndFlush(mailSender.sendEmail(correoInputDto)));
            reservaRepo.saveAndFlush(reservaEntity);
            autobusEntity.addReserva(reservaEntity);
            autobusRepo.saveAndFlush(autobusEntity);
            return reservaOutputDto;

        } catch (Exception e) {
            System.out.println(e);
            throw new BadRequestException("Reserva invalida");
        }

    }

    @Override
    public ReservaOutputDto removeId(String id) {
        AutobusEntity autobusEntity = autobusRepo.findById(id).orElseThrow(() -> new BadRequestException("ID de autobus no encontrado"));
        autobusRepo.deleteById(id);
        return null;
    }


}
