package back.ejercicioFinal.content.Token;

import back.ejercicioFinal.content.Usuario.UsuarioService;
import back.ejercicioFinal.security.DecodedToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;


@RestController
@RequestMapping("/api/v0")
public class ControladorToken {
    @Autowired
    UsuarioService usuarioService;


    @GetMapping("/token")
    public RedirectView getToken(@RequestHeader("username") String username, @RequestHeader("password") String password, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("username", username);
        redirectAttributes.addAttribute("password", password);
        return new RedirectView("/api/v0/login");
    }

    //TODO
    @GetMapping("/token/{token}")
    public ResponseEntity checkToken(@PathVariable String token) {
        try {
            DecodedToken decodedToken = DecodedToken.getDecoded(token);
            usuarioService.checkToken(decodedToken.sub,token);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }


    }

    private ResponseEntity decode(String token) throws UnsupportedEncodingException {
        DecodedToken decodedToken = DecodedToken.getDecoded(token);
        usuarioService.findUsername(decodedToken.name);
        return new ResponseEntity(HttpStatus.OK);
    }


}
