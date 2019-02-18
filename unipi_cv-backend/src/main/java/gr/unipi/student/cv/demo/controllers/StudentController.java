package gr.unipi.student.cv.demo.controllers;

import gr.unipi.student.cv.demo.Utilities.Constants;
import gr.unipi.student.cv.demo.model.StudentEntity;
import gr.unipi.student.cv.demo.model.UserEntity;
import gr.unipi.student.cv.demo.repository.StudentRepositoryImpl;
import gr.unipi.student.cv.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class StudentController {
    @Autowired
    StudentRepositoryImpl studentRepositoryIml;
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/setProfile",method = RequestMethod.POST )
   public ResponseEntity<?> setProfile(@RequestBody StudentEntity studentEntity, Principal principal){
        UserEntity user = (UserEntity) ((Authentication) principal).getPrincipal();
        StudentEntity std = studentRepositoryIml.getProfile(user);
        std.setName(studentEntity.getName());
        std.setSurname(studentEntity.getSurname());
        std.setThesis(studentEntity.getThesis());
        std.setGrade(studentEntity.getGrade());
        std.setEmail(studentEntity.getEmail());
        std.setPhone(studentEntity.getPhone());
        std.setPlatformEntities(studentEntity.getPlatformEntities());
        std.setLanguageEntities(studentEntity.getLanguageEntities());
        std.setKeywordEntities(studentEntity.getKeywordEntities());
         StudentEntity studentEntity1 = studentRepositoryIml.setProfile(std);
         if (studentEntity1==null){
             return new ResponseEntity<String>("something went wrong", HttpStatus.NOT_FOUND);
         }else
             return new ResponseEntity<StudentEntity>(studentEntity1,HttpStatus.OK);
    }
    @RequestMapping(value = "/getProfile",method = RequestMethod.GET )
    public ResponseEntity<?> getProfile(Principal principal){
        UserEntity user = (UserEntity) ((Authentication) principal).getPrincipal();
        StudentEntity profile = studentRepositoryIml.getProfile(user);
        return new ResponseEntity<StudentEntity>(profile, HttpStatus.OK);
    }
}
