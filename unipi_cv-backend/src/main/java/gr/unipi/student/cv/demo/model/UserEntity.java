package gr.unipi.student.cv.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "unipi_cv", catalog = "")
public class UserEntity {
    private Integer idUser;
    private String username;
    private String password;
    private Integer role;
    private String token;

    @OneToOne(mappedBy = "userEntity",cascade = CascadeType.ALL)
    private StudentEntity studentEntity;

    public enum Role{
        ADMIN(1),STUDENT(2);
        private final int value;
        private Role(int value){
            this.value = value;
        }
        public int toInteger(){
            return value;
        }


    }
    public UserEntity(){

    }

    public UserEntity(String username,String password, int role){
        this.username=username;
        this.password=password;
        this.role = role;
    }
    @Id
    @Column(name = "idUser")
    @GeneratedValue
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "role")
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Transient
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

//    public StudentEntity getStudentEntity() {
//        return studentEntity;
//    }
//
//    public void setStudentEntity(StudentEntity studentEntity) {
//        this.studentEntity = studentEntity;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(idUser, that.idUser) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, username, password, role);
    }
}
