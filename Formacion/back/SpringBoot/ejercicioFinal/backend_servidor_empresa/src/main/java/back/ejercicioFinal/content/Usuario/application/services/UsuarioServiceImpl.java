package back.ejercicioFinal.content.Usuario.application.services;

import back.ejercicioFinal.content.Token.domain.TokenEntity;
import back.ejercicioFinal.content.Token.repository.TokenRepo;
import back.ejercicioFinal.content.Usuario.domain.UsuarioEntity;
import back.ejercicioFinal.content.Usuario.infrastructure.dto.UsuarioInputDto;
import back.ejercicioFinal.content.Usuario.infrastructure.dto.UsuarioOutputDto;
import back.ejercicioFinal.content.Usuario.repository.UsuarioRepo;
import back.ejercicioFinal.content.Usuario.application.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UsuarioRepo usuarioRepo;

    @Autowired
    TokenRepo tokenRepo;

    @Override
    public UsuarioOutputDto addUser(UsuarioInputDto usuarioInputDto) throws Exception {
        try {
            usuarioInputDto.setPassword(passwordEncoder.encode(usuarioInputDto.getPassword()));
            return new UsuarioOutputDto(usuarioRepo.saveAndFlush(new UsuarioEntity(usuarioInputDto)));
        } catch (Exception e) {
            throw new Exception("Usuario no creado");
        }
    }

    @Override
    public UsuarioOutputDto findUsername(String username) {
        return new UsuarioOutputDto(usuarioRepo.findByUsername(username).get(0));
    }

    @Override
    public void checkToken(String username, String content) throws Exception {
        TokenEntity tokenEntity = tokenRepo.findByContent(content).get(0);
        UsuarioEntity usuarioEntity = usuarioRepo.findByUsername(username).get(0);
        if (!tokenEntity.equals(usuarioEntity.getTokenEntity()))
            throw new Exception("No existe");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UsuarioEntity> listaUsuario = usuarioRepo.findByUsername(username);
        UsuarioEntity usuario;
        if (listaUsuario.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        } else {
            usuario = listaUsuario.get(0);
        }

        Collection<SimpleGrantedAuthority> auothorities = new ArrayList<>();
        auothorities.add(new SimpleGrantedAuthority("ADMIN"));
        return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), auothorities);
    }
}
