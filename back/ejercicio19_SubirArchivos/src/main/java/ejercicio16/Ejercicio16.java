package ejercicio16;

import ejercicio16.file.application.FileService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class Ejercicio16 implements CommandLineRunner {

    @Resource
    FileService storageService;

    public static void main(String[] args) {
        SpringApplication.run(Ejercicio16.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
        storageService.deleteAll();
        String argumento = null;
        try {
            argumento = arg[0];
        } catch (Exception e) {

        }
        storageService.init(argumento);

    }


}
