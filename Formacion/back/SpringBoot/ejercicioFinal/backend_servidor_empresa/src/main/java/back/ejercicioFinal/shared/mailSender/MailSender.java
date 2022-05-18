package back.ejercicioFinal.shared.mailSender;

import back.ejercicioFinal.content.Correo.domain.CorreoEntity;
import back.ejercicioFinal.content.Correo.infrastructure.dto.CorreoInputDto;

public interface MailSender {
    public CorreoEntity sendEmail(CorreoInputDto correoInputDto);
}
