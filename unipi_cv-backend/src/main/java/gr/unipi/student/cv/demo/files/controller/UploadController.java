package gr.unipi.student.cv.demo.files.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gr.unipi.student.cv.demo.model.StudentEntity;
import gr.unipi.student.cv.demo.model.UserEntity;
import gr.unipi.student.cv.demo.repository.StudentRepository;
import gr.unipi.student.cv.demo.repository.StudentRepositoryImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import gr.unipi.student.cv.demo.files.storage.StorageService;

@RestController
public class UploadController {
    @Value("${directory.avatars}")
    String directoryAvatar;
    @Value("${directory.cv}")
    String directoryCv;
    @Autowired
    StorageService storageService;
    @Autowired
    StudentRepositoryImpl studentRepositoryimp;
    @Autowired
    StudentRepository studentRepository;


   // @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
//                    required = true, dataType = "string", paramType = "header")
//    //
    @RequestMapping(value = "/file/avatar" , method = RequestMethod.POST)
    public ResponseEntity<String> handleFileUploadAvatar(@RequestParam("file") MultipartFile file , Principal principal){
        UserEntity user = (UserEntity) ((Authentication) principal).getPrincipal();
        StudentEntity studentEntity = studentRepositoryimp.getProfile(user);
        String dir ;
        String message = "";
        try{
           dir = storageService.store(file,directoryAvatar + File.separator + user.getIdUser());
           if(studentEntity.getAvatar()!=null && !studentEntity.getAvatar().equals("") ){
               storageService.deleteFile(System.getProperty("user.dir") + File.separator + studentEntity.getAvatar());
           }
           studentEntity.setAvatar(dir);
           studentRepository.save(studentEntity);
            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return new ResponseEntity<String>(message,HttpStatus.EXPECTATION_FAILED);
        }
    }
//    @ApiImplicitParam(name = "Authorization", value = "Authorization token",
//            required = true, dataType = "string", paramType = "header")
    @RequestMapping(value = "/file/cv" , method = RequestMethod.POST )
    public ResponseEntity<String> handleFileUploadCv(@RequestParam("file") MultipartFile file , Principal principal){
        UserEntity user = (UserEntity) ((Authentication) principal).getPrincipal();
        StudentEntity studentEntity = studentRepositoryimp.getProfile(user);
        String dir ;
        String message = "";
        try{
            dir = storageService.store(file,directoryCv+ File.separator + user.getIdUser());
            if( studentEntity.getCv()!=null && !studentEntity.getCv().equals("")  ){
                storageService.deleteFile(System.getProperty("user.dir") + File.separator + studentEntity.getCv());
            }
            studentEntity.setCv(dir);
            studentRepository.save(studentEntity);
            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return new ResponseEntity<String>(message,HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping(value = "/getFile" , method = RequestMethod.GET)
    public ResponseEntity<Resource> getFile(@RequestParam("file") String fileDirectory) throws MalformedURLException {
        File file = new File(System.getProperty("user.dir")  + File.separator + fileDirectory);
        if(!file.exists()){
            throw  new RuntimeException("File not found");
        }
        Resource resource = new UrlResource(file.toURI());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + file.getName() + "\"").body(resource);
    }

}
