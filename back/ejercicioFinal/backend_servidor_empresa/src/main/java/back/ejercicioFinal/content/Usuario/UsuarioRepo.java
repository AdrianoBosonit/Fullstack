package back.ejercicioFinal.content.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepo extends JpaRepository<UsuarioEntity, Long> {
    List<UsuarioEntity> findByUsername(String username);

    List<UsuarioEntity> findByUsernameAndPassword(String username, String password);


}
