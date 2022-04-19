package back.ejercicioFinal.shared;

import back.ejercicioFinal.content.Correo.CorreoEntity;
import back.ejercicioFinal.content.Correo.CorreoInputDto;

public interface MailSender{
    public CorreoEntity sendEmail(CorreoInputDto correoInputDto);
}
