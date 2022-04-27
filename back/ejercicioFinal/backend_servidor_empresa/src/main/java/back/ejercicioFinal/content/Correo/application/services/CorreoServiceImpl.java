package back.ejercicioFinal.content.Correo.application.services;

import back.ejercicioFinal.content.Correo.domain.CorreoEntity;
import back.ejercicioFinal.content.Correo.infrastructure.dto.CorreoInputDto;
import back.ejercicioFinal.content.Correo.infrastructure.dto.CorreoOutputDto;
import back.ejercicioFinal.content.Correo.repository.CorreoRepo;
import back.ejercicioFinal.content.Correo.application.interfaces.CorreoService;
import back.ejercicioFinal.content.Reserva.domain.ReservaEntity;
import back.ejercicioFinal.content.Reserva.infrastructure.dto.ReservaOutputDto;
import back.ejercicioFinal.content.Reserva.repository.ReservaRepo;
import back.ejercicioFinal.exception.BadRequestException;
import back.ejercicioFinal.shared.mailSender.MailSenderImpl;
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
        ReservaEntity reservaEntity = reservaRepo.findByCiudadAndEmailAndFechaReservaAndHoraReserva(correoInputDto.getCiudad(), correoInputDto.getEmail(), correoInputDto.getFechaReserva(), correoInputDto.getHoraReserva());
        if (reservaEntity.equals(null))
            throw new BadRequestException("ID de reserva no encontrado");
        mailSender.sendEmail(correoInputDto);
        return new ReservaOutputDto(reservaEntity);
    }

    @Override
    public List<CorreoOutputDto> getCorreos(String ciudadDestino, Date fechaInferior, Date fechaSuperior, Float horaInferior, Float horaSuperior) {
        return correoRepo.findByciudadAndFechaReservaBetweenAndHoraReservaBetween(ciudadDestino, fechaInferior, fechaSuperior, horaInferior, horaSuperior).stream().map(p -> new CorreoOutputDto(p)).collect(Collectors.toList());
    }


}
