package gr.unipi.student.cv.demo.repository;

import gr.unipi.student.cv.demo.model.StudentEntity;
import gr.unipi.student.cv.demo.model.UserEntity;
import gr.unipi.student.cv.demo.repository.UserRepository;

public interface UserUtils {

     StudentEntity saveUser(UserEntity userEntity);
     void deleteUser(String username);

}
