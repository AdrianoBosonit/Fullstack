package ejercicio16.file.infraestructure.controller;

import org.apache.commons.io.FilenameUtils;
import ejercicio16.file.application.FileService;
import ejercicio16.file.domain.File;
import ejercicio16.file.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("http://localhost:8081")
@Controller
public class FileController {

    @Autowired
    FileService storageService;


    @PostMapping()
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        String message = "";
        try {
            storageService.save(file, null);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            redirectAttributes.addFlashAttribute("message", message);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            redirectAttributes.addFlashAttribute("message", message);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PostMapping("/{type}")
    public ResponseEntity<ResponseMessage> handleFileUploadType(@PathVariable String type,
                                                                @RequestParam("file") MultipartFile file,
                                                                RedirectAttributes redirectAttributes) {
        String message = "";
        try {
            storageService.save(file, type);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            redirectAttributes.addFlashAttribute("message", message);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            redirectAttributes.addFlashAttribute("message", message);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<File>> getListFiles() {
        List<File> fileInfos = storageService.loadAll();
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/setpath")
    public void changeLocation(@RequestParam String path) {
        storageService.changeLocation(path);
    }

    @GetMapping("fileId/{id}")
    public ResponseEntity<File> getFileId(@PathVariable String id) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + storageService.getFileId(Integer.parseInt(id)).getName() + "\"").body(storageService.getFileId(Integer.parseInt(id)));
    }

    @GetMapping("file/nombre/{nombre}")
    public ResponseEntity<File> getFileName(@PathVariable String nombre) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + storageService.getFileName(nombre).getName() + "\"").body(storageService.getFileName(nombre));
    }

}
