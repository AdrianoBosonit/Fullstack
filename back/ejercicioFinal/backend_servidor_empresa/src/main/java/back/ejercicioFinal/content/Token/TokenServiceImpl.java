package back.ejercicioFinal.content.Token;

import back.ejercicioFinal.content.Usuario.UsuarioEntity;
import back.ejercicioFinal.content.Usuario.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    TokenRepo tokenRepo;

    @Autowired
    UsuarioRepo usuarioRepo;

    @Override
    public TokenEntity add(String header, String token, String username, String password) {
        UsuarioEntity user=usuarioRepo.findByUsername(username).get(0);
        user.setTokenEntity(tokenRepo.saveAndFlush(new TokenEntity( token, header)));
        user=usuarioRepo.saveAndFlush(user);
        System.out.println(user);
        return user.getTokenEntity();
    }


}
