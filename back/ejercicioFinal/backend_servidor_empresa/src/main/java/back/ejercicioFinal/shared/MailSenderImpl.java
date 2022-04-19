package back.ejercicioFinal.shared;

import back.ejercicioFinal.content.Correo.CorreoEntity;
import back.ejercicioFinal.content.Correo.CorreoInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderImpl implements MailSender{
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public CorreoEntity sendEmail(CorreoInputDto correoInputDto) {
        SimpleMailMessage email = new SimpleMailMessage();
        CorreoEntity correoEntity = new CorreoEntity(correoInputDto);
        email.setTo(correoEntity.getEmail());
        email.setSubject("Confirmacion de reserva de viaje");
        email.setText(correoEntity.getMensaje());
        mailSender.send(email);
        return new CorreoEntity(correoInputDto);
    }
}
