package back.ejercicioFinal.content.Usuario.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioInputDto {
    private String username;
    private String password;
}
