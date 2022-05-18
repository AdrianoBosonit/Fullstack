package back.ejercicioFinal.content.Token.repository;

import back.ejercicioFinal.content.Token.domain.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepo extends JpaRepository<TokenEntity, Long> {
    List<TokenEntity> findByContent(String content);
}
