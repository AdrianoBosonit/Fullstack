package back.ejercicioFinal;

import back.ejercicioFinal.content.Usuario.UsuarioInputDto;
import back.ejercicioFinal.content.Usuario.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class backend_servidor_empresa {

    public static void main(String[] args) {
        SpringApplication.run(backend_servidor_empresa.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UsuarioService usuarioService) {
        return args -> {
            usuarioService.addUser(new UsuarioInputDto("usuario", "password"));
        };

    }

}
