package gr.unipi.student.cv.demo.repository;

import gr.unipi.student.cv.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class StudentRepositoryImpl {
    @Autowired
    private StudentRepository studentRepository;

    public StudentEntity setProfile(StudentEntity studentEntity){
        return studentRepository.save(studentEntity);
    }
    public StudentEntity getProfile(UserEntity userEntity){
        return studentRepository.findByUserEntity(userEntity);
    }
    public List<StudentEntity> findStdByLang (Set<LanguageEntity> languageEntities){
        List<StudentEntity> std = studentRepository.findAll();
        List<StudentEntity> std1 = new ArrayList<>();
        for(StudentEntity student:std){
            Set<LanguageEntity> lang = new HashSet<>(student.getLanguageEntities());
            lang.retainAll(languageEntities);
            if(lang.equals(languageEntities)){
                std1.add(student);
            }
        }
        return std1;
    }
    public List<StudentEntity> findStdByPlat (Set<PlatformEntity> platformEntities){
        List<StudentEntity> std = studentRepository.findAll();
        List<StudentEntity> std1 = new ArrayList<>();
        for(StudentEntity student:std){
            Set<PlatformEntity> plat = new HashSet<>(student.getPlatformEntities());
            plat.retainAll(platformEntities);
            if(plat.equals(platformEntities)){
                std1.add(student);
            }
        }
        return std1;
    }
    public List<StudentEntity> findStdByKey (Set<KeywordEntity> keywordEntities){
        List<StudentEntity> std = studentRepository.findAll();
        List<StudentEntity> std1 = new ArrayList<>();
        for(StudentEntity student:std){
            Set<KeywordEntity> key = new HashSet<>(student.getKeywordEntities());
            key.retainAll(keywordEntities);
            if(key.equals(keywordEntities)){
                std1.add(student);
            }
        }
        return std1;
    }
    public List<StudentEntity> findStdByGrade (String grade){
        double gradeToDouble;
        double givenGrade = Double.parseDouble(grade);
        List<StudentEntity> std = studentRepository.findAll();
        List<StudentEntity> std1 = new ArrayList<>();
        for(StudentEntity student:std){
            if(student.getGrade()!=null){
                gradeToDouble = Double.parseDouble(student.getGrade());
                if(gradeToDouble>=givenGrade){
                    std1.add(student);
                }
            }
        }
        return std1;
    }
    public List<UserEntity> getUsers(){
        List<UserEntity> users = new ArrayList<>();
        for(StudentEntity studentEntity : studentRepository.findAll()){
            users.add(studentEntity.getUserEntity());
        }
        return users;
    }

    public void deleteStudent(StudentEntity studentEntity){
        studentRepository.delete(studentEntity);
    }

    public List<StudentEntity> getStudents(){

        return studentRepository.findAll();
    }
}
