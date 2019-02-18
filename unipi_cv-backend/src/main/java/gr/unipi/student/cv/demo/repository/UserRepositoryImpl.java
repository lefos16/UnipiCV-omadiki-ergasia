package gr.unipi.student.cv.demo.repository;

import gr.unipi.student.cv.demo.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepositoryImpl {
    @Autowired
    private UserRepository userRepository;

//    public List<UserEntity> getUsers(){
//        return userRepository.findAll();
//    }
    public UserEntity login(String name,String pass){
        return userRepository.findByUsernameAndPassword(name,pass);
    }
    public UserEntity saveUser(String name,String pass, int role){
        return userRepository.save(new UserEntity(name,pass,role));
    }

    public UserEntity findUser(String username){
        return userRepository.findByUsername(username);
    }

    public UserEntity saveUser(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    public void deleteUser(String username){
        userRepository.deleteByUsername(username);
    }

}
