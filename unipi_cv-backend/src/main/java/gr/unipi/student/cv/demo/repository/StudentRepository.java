package gr.unipi.student.cv.demo.repository;

import gr.unipi.student.cv.demo.model.LanguageEntity;
import gr.unipi.student.cv.demo.model.StudentEntity;
import gr.unipi.student.cv.demo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer>{
    StudentEntity findByUserEntity(UserEntity userEntity);

}
