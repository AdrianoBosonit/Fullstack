package back.ejercicioFinal.content.Token.application.services;

import back.ejercicioFinal.content.Token.domain.TokenEntity;
import back.ejercicioFinal.content.Token.repository.TokenRepo;
import back.ejercicioFinal.content.Token.application.interfaces.TokenService;
import back.ejercicioFinal.content.Usuario.domain.UsuarioEntity;
import back.ejercicioFinal.content.Usuario.repository.UsuarioRepo;
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
        UsuarioEntity user = usuarioRepo.findByUsername(username).get(0);
        user.setTokenEntity(tokenRepo.saveAndFlush(new TokenEntity(token, header)));
        user = usuarioRepo.saveAndFlush(user);
        return user.getTokenEntity();
    }


}
