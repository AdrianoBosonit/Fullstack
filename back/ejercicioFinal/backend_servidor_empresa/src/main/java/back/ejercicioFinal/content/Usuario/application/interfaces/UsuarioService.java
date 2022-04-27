package back.ejercicioFinal.content.Usuario.application.interfaces;

import back.ejercicioFinal.content.Usuario.infrastructure.dto.UsuarioInputDto;
import back.ejercicioFinal.content.Usuario.infrastructure.dto.UsuarioOutputDto;

public interface UsuarioService {
    public UsuarioOutputDto addUser(UsuarioInputDto usuarioInputDto) throws Exception;

    public UsuarioOutputDto findUsername(String username);

    public void checkToken(String username, String content) throws Exception;
}
