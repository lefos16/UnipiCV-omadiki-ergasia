package gr.unipi.student.cv.demo.files.storage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final String rootLocation = System.getProperty("java.io.tmpdir");

    public String store(MultipartFile file, String directory) throws IOException {
        String dir;
        dir = System.getProperty("user.dir") + File.separator + directory + File.separator  + file.getOriginalFilename();
        File dirUser = new File(System.getProperty("user.dir") + File.separator + directory);
        if (! dirUser.exists()){
            dirUser.mkdir();
        }
        File convFile = new File(dir);
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return directory + File.separator  + file.getOriginalFilename();
    }
    public void deleteFile(String directory) throws IOException {
        Files.delete(Paths.get(directory));
    }
}
