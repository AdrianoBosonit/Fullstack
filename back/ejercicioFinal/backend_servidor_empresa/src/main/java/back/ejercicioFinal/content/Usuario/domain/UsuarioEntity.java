package back.ejercicioFinal.content.Usuario.domain;

import back.ejercicioFinal.content.Token.domain.TokenEntity;
import back.ejercicioFinal.content.Usuario.infrastructure.dto.UsuarioInputDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    @OneToOne
    @JoinColumn(name = "idToken")
    private TokenEntity tokenEntity;

    public UsuarioEntity(UsuarioInputDto usuarioInputDto) {
        this.username = usuarioInputDto.getUsername();
        this.password = usuarioInputDto.getPassword();
        this.tokenEntity = null;
    }

    public UsuarioEntity(String username, String password) {
        this.username = username;
        this.password = password;
        this.tokenEntity = null;
    }
}
