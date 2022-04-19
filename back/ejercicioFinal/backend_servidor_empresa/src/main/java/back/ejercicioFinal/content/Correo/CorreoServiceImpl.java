package back.ejercicioFinal.content.Correo;

import back.ejercicioFinal.content.Reserva.ReservaEntity;
import back.ejercicioFinal.content.Reserva.ReservaOutputDto;
import back.ejercicioFinal.content.Reserva.ReservaRepo;
import back.ejercicioFinal.exception.BadRequestException;
import back.ejercicioFinal.shared.MailSenderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorreoServiceImpl implements CorreoService {

    @Autowired
    CorreoRepo correoRepo;

    @Autowired
    ReservaRepo reservaRepo;

    @Autowired
    MailSenderImpl mailSender;

    @Override
    public ReservaOutputDto putCorreo(CorreoInputDto correoInputDto) {
        List<CorreoEntity> listaCorreo = correoRepo.findByCiudadAndFechaReservaAndHoraReserva(correoInputDto.getCiudad(), correoInputDto.getFechaReserva(), correoInputDto.getHoraReserva());
        if (listaCorreo.isEmpty())
            throw new BadRequestException("Correo no encontrado");
        List<ReservaEntity> listaReserva = reservaRepo.findByCorreo(listaCorreo.get(0));
        if (listaReserva.isEmpty())
            throw new BadRequestException("ID de reserva no encontrado");
        mailSender.sendEmail(correoInputDto);
        return new ReservaOutputDto(listaReserva.get(0));
    }

    @Override
    public List<CorreoOutputDto> getCorreos(String ciudadDestino, Date fechaInferior, Date fechaSuperior, Float horaInferior, Float horaSuperior) {
        return correoRepo.findByciudadAndFechaReservaBetweenAndHoraReservaBetween(ciudadDestino, fechaInferior, fechaSuperior, horaInferior, horaSuperior).stream().map(p -> new CorreoOutputDto(p)).collect(Collectors.toList());
    }


}
