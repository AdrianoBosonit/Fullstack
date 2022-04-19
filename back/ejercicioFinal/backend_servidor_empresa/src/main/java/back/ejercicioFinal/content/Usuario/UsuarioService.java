package back.ejercicioFinal.content.Usuario;

public interface UsuarioService {
    public UsuarioOutputDto addUser(UsuarioInputDto usuarioInputDto) throws Exception;
    public UsuarioOutputDto findUsername(String username);
    public void checkToken(String username,String content) throws Exception;
}
