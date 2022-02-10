import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;


public class lectorDeArchivos {
    List<Persona> personas;
    void run() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        Optional<String> nombre;
        Optional<String> poblacion;
        Optional<Integer> edad;
        personas=new ArrayList();


        try {
            archivo = new File("personas.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
            String[] linea2;
            while ((linea = br.readLine()) != null){
                linea2 = linea.split("\\:");
                nombre=Optional.of(linea2[0]);
                poblacion=Optional.empty();
                edad=Optional.empty();
                if(linea2.length==2){
                    if(linea2[1].chars().allMatch((Character::isDigit))){
                        edad=Optional.of(Integer.parseInt(linea2[1]));

                    }else{
                        poblacion=Optional.of(linea2[1]);
                    }
                }
                if(linea2.length==3){
                    poblacion=Optional.of(linea2[1]);
                    edad=Optional.of(Integer.parseInt(linea2[2]));
                }


                personas.add(new Persona(nombre.orElse("desconocido"),poblacion.orElse("desconocido"),edad.orElse(-1)));


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    void representarFichero(){
        personas.stream().filter(persona -> persona.getEdad()<25).forEach(p -> p.toString());
    }
}
