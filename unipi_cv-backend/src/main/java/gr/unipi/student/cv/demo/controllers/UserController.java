package gr.unipi.student.cv.demo.controllers;

import gr.unipi.student.cv.demo.model.LanguageEntity;
import gr.unipi.student.cv.demo.repository.StudentRepositoryImpl;
import gr.unipi.student.cv.demo.repository.UserUtils;
import gr.unipi.student.cv.demo.model.StudentEntity;
import gr.unipi.student.cv.demo.model.UserEntity;
import gr.unipi.student.cv.demo.repository.StudentRepository;
import gr.unipi.student.cv.demo.repository.UserRepositoryImpl;
import gr.unipi.student.cv.demo.security.JwtToken.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepositoryImpl userImplement;
    @Autowired
    StudentRepositoryImpl studentRepositoryImpl;

    @Autowired
    UserUtils userUtils;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/getUsers" , method = RequestMethod.GET)
    public List<UserEntity> getUsers() {
        return studentRepositoryImpl.getUsers();
    }

    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public ResponseEntity<?>  login(@RequestBody UserEntity user) {
        UserEntity userEntity= userImplement.login(user.getUsername(),user.getPassword());
        if(userEntity==null){
         return   new ResponseEntity<String>("no user found", HttpStatus.NOT_FOUND);
        }else {
            String jwtToken = jwtTokenUtil.generateToken(userEntity);
            userEntity.setToken(jwtToken);

            return new ResponseEntity<UserEntity>(userEntity, HttpStatus.OK);
        }
    }
    @RequestMapping(value = "/createUser",method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserEntity user){

        StudentEntity studentEntity = userUtils.saveUser(user);
        if(studentEntity==null){
            return new ResponseEntity<String>("username already exist",HttpStatus.UNPROCESSABLE_ENTITY);
        }
        // check null in std entity

        return new ResponseEntity<StudentEntity>(studentEntity,HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteUser/{username}",method = RequestMethod.DELETE)
    @Transactional
    public ResponseEntity<?> deleteUser(@PathVariable String username){

        userUtils.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ResponseEntity<?> search(@RequestBody StudentEntity studentEntity){
        List<StudentEntity> studentLang = null;
        List<StudentEntity> studentPlat = null;
        List<StudentEntity> studentGrade = null;
        List<StudentEntity> studentKey = null;
        List<StudentEntity> studentEntities = studentRepositoryImpl.getStudents();

        if(studentEntity.getLanguageEntities()!=null && studentEntity.getLanguageEntities().size()>0) {
            studentLang = studentRepositoryImpl.findStdByLang(studentEntity.getLanguageEntities());
            if(studentLang!=null){
                studentEntities.retainAll(studentLang);
            }
       }
       if(studentEntity.getPlatformEntities()!=null && studentEntity.getPlatformEntities().size()>0) {
           studentPlat = studentRepositoryImpl.findStdByPlat(studentEntity.getPlatformEntities());
           if(studentPlat!=null){
               studentEntities.retainAll(studentPlat);
           }
       }

        if(studentEntity.getKeywordEntities()!=null && studentEntity.getKeywordEntities().size()>0) {
            studentKey = studentRepositoryImpl.findStdByKey(studentEntity.getKeywordEntities());
            if(studentKey!=null){
                studentEntities.retainAll(studentKey);
            }
        }

       if(studentEntity.getGrade()!=null && !studentEntity.getGrade().equals("")){
            studentGrade = studentRepositoryImpl.findStdByGrade(studentEntity.getGrade());
            if (studentGrade!=null){
                studentEntities.retainAll(studentGrade);
            }
       }


       if(studentLang==null && studentGrade==null && studentPlat==null && studentKey==null){
            studentEntities=new ArrayList<>();
       }

        return new ResponseEntity<List<StudentEntity>>(studentEntities,HttpStatus.OK);
    }
}