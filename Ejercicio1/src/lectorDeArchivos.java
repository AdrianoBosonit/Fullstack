import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Optional;
import java.util.OptionalDouble;

public class lectorDeArchivos {

    void run() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String nombre;
        Optional<String> poblacion;
        Integer edad;

        try {
            archivo = new File("personas.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;

            while ((linea = br.readLine()) != null){
                String[] linea2 = linea.split("\\:");
                nombre=Optional.ofNullable(linea2[0]).get());
                poblacion=Optional.ofNullable(linea2[1]).;



                System.out.println(nombre+"    "+poblacion.get());

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
}
