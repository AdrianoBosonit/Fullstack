package ejercicio16.file.application;

import ejercicio16.exceptions.NotFoundException;
import ejercicio16.file.domain.File;
import ejercicio16.file.infraestructure.repository.FileRepo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FileServiceImpl implements FileService {
    private Path root = Paths.get("uploads");
    @Autowired
    FileRepo fileRepo;

    @Override
    public void init(String root) {
        try {
            if (this.root != null && root != null) {
                this.root = Paths.get(root);
            }
            Files.createDirectory(this.root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file, String extension) {
        try {
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            if (extension != null && !ext.equals(extension))
                throw new RuntimeException("Different extension");

            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
            fileRepo.saveAndFlush(new File(file, root, extension));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public List<File> loadAll() {
        return fileRepo.findAll();
    }


    @Override
    public void changeLocation(String path) {
        try {
            Files.move(root, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
            deleteAll();
            init(path);
        } catch (Exception e) {
            root = Paths.get(path);
        }

    }

    @Override
    public File getFileId(Integer id) {
        return fileRepo.findById(id).orElseThrow(() -> new NotFoundException("ID no encontrado"));
    }

    @Override
    public File getFileName(String name) {
        List<File> lista = fileRepo.findByName(name);
        if (lista.isEmpty()) {
            throw new NotFoundException("Nombre no encontrado");
        }
        return lista.get(0);
    }
}
