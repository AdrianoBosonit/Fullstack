package ejercicio16.file.domain;

import ejercicio16.exceptions.StorageException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Entity
@Table(name = "File")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @NotNull
    @Column(name = "name")
    private String name;

    @NotBlank
    @NotNull
    @Column(name = "url")
    private String url;

    @NotNull
    @NotBlank
    @Column(name = "category")
    private String category;

    @Column(name = "upload_date")
    private Date uploadDate;

    public File(MultipartFile file, Path rootLocation, String extension) {
        name = file.getOriginalFilename();
        uploadDate = new Date();
        category = extension;
        url = rootLocation.toFile().getName();
    }
}
