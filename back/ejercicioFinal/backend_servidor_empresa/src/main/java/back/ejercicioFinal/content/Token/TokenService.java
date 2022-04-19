package back.ejercicioFinal.content.Token;

public interface TokenService {
    public TokenEntity add(String header,String token, String username,String password);
}
