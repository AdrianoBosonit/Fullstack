package ejercicio7.ciclo_de_vida_de_beans;

import org.springframework.boot.CommandLineRunner;

public class MiClase3 implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < args.length; i++) {
            System.out.println("Soy la tercera Clase y este es mi parametro: " + args[i]);
        }
    }

}
