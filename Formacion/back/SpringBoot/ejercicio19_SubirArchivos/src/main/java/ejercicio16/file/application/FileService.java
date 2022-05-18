package ejercicio16.file.application;

import ejercicio16.file.domain.File;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface FileService {
    public void init(String root);

    public void save(MultipartFile file, String extension);

    public Resource load(String filename);

    public void deleteAll();

    public List<File> loadAll();

    public void changeLocation(String path);

    public File getFileId(Integer id);

    public File getFileName(String name);
}
