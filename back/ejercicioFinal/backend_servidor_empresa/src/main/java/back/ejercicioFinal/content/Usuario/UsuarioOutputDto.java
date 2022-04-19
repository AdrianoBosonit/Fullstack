package back.ejercicioFinal.content.Usuario;

import lombok.Data;

@Data
public class UsuarioOutputDto {
    private String username;
    private String password;

    public UsuarioOutputDto(UsuarioEntity usuarioEntity) {
        this.username = usuarioEntity.getUsername();
        this.password = usuarioEntity.getPassword();
    }
}
