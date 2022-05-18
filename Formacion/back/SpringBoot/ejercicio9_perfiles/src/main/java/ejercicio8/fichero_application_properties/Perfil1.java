package ejercicio8.fichero_application_properties;

import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
public class Perfil1 implements Perfiles {
    private String perfil;
    public Perfil1(String perfil){
        this.perfil=perfil;
    }
    public String getPerfil(){
        return perfil;
    }
    @Override
    public void miFuncion() {
        System.out.println("Soy el perfil: "+perfil); ;
    }
}
