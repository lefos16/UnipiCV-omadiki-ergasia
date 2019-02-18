//package gr.unipi.student.cv.demo.Utilities;
//
//import org.springframework.util.StringUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.StandardCopyOption;
//
//public class FileUtils {
//    public void store(MultipartFile file) {
//        String filename = StringUtils.cleanPath(file.getOriginalFilename());
//        try {
//            if (file.isEmpty()) {
//                throw new RuntimeException("Failed to store empty file " + filename);
//            }
//
//            // This is a security check
//            if (filename.contains("..")) {
//                throw new RuntimeException("Cannot store file with relative path outside current directory " + filename);
//            }
//
//            try (InputStream inputStream = file.getInputStream()) {
//                Files.copy(inputStream, this.uploadLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
//           }
//        } catch (IOException e) {
//           throw new RuntimeException("Failed to store file " + filename, e);
//        }
//    }
//}
