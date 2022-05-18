package ejercicio8.fichero_application_properties;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

public class Perfil2 implements Perfiles{
    private String perfil;
    public Perfil2(String perfil){
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
