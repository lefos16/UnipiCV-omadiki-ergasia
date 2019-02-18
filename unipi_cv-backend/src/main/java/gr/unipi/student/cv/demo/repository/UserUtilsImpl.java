package gr.unipi.student.cv.demo.repository;

import gr.unipi.student.cv.demo.model.StudentEntity;
import gr.unipi.student.cv.demo.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUtilsImpl implements  UserUtils{

    @Autowired
    private UserRepositoryImpl userRepository;
    @Autowired
    private StudentRepositoryImpl studentRepository;
    @Override
    public StudentEntity saveUser(UserEntity userEntity) {


        if(userEntity.getIdUser() == null) {
            if(userRepository.findUser(userEntity.getUsername())!=null){
                return null;
            }
            userEntity.setRole(UserEntity.Role.STUDENT.toInteger());
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setUserEntity(userEntity);
            StudentEntity savedStudent = studentRepository.setProfile(studentEntity);
            return savedStudent;

        }
        else{

          UserEntity userEntity1 =  userRepository.findUser(userEntity.getUsername());
          if(userEntity1!=null && userEntity.getIdUser()!=userEntity1.getIdUser()){
              return null;
          }
            userRepository.saveUser(userEntity);
            return studentRepository.getProfile(userEntity);
        }

    }

    @Override
    public void deleteUser(String username) {

    StudentEntity studentEntity = studentRepository.getProfile(userRepository.findUser(username));
    studentRepository.deleteStudent(studentEntity);

    }
}
