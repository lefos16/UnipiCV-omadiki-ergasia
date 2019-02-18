package gr.unipi.student.cv.demo.repository;

import gr.unipi.student.cv.demo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
  UserEntity findByUsernameAndPassword(String name , String pass);
  UserEntity findByUsername(String name );

  void deleteByUsername(String username);
}
