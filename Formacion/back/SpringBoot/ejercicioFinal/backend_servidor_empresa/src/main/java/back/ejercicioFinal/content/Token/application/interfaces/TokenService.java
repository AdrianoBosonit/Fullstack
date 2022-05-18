package back.ejercicioFinal.content.Token.application.interfaces;

import back.ejercicioFinal.content.Token.domain.TokenEntity;

public interface TokenService {
    public TokenEntity add(String header, String token, String username, String password);
}
