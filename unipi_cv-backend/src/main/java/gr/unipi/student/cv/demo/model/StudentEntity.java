package gr.unipi.student.cv.demo.model;

import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "student", schema = "unipi_cv", catalog = "")
@NaturalIdCache
public class StudentEntity {
    private Integer idStudent;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String grade;
    private String thesis;
    private String avatar;
    private String cv;
    private UserEntity userEntity;
    private Set<KeywordEntity> keywordEntities = new HashSet<>();
    private Set<LanguageEntity> languageEntities = new HashSet<>();
    private Set<PlatformEntity> platformEntities = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUser", unique = true, nullable = true, insertable = true, updatable = true)
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Student_has_Language",
            joinColumns = {@JoinColumn(name = "idStudent")},
            inverseJoinColumns = {@JoinColumn(name = "idLanguages")}
    )
    public Set<LanguageEntity> getLanguageEntities() {
        return languageEntities;
    }

    public void setLanguageEntities(Set<LanguageEntity> languageEntities) {
        this.languageEntities = languageEntities;
    }


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Student_has_Platform",
            joinColumns = {@JoinColumn(name = "idStudent")},
            inverseJoinColumns = {@JoinColumn(name = "idPlatforms")}
    )
    public Set<PlatformEntity> getPlatformEntities() {
        return platformEntities;
    }

    public void setPlatformEntities(Set<PlatformEntity> platformEntities) {
        this.platformEntities = platformEntities;
    }


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Student_has_Platform_Keyword",
            joinColumns = {@JoinColumn(name = "idStudent")},
            inverseJoinColumns = {@JoinColumn(name = "idPlatformKeyword")}
    )
    public Set<KeywordEntity> getKeywordEntities() {
        return keywordEntities;
    }

    public void setKeywordEntities(Set<KeywordEntity> keywordEntities) {
        this.keywordEntities = keywordEntities;
    }

    @Id
    @Column(name = "idStudent")
    @GeneratedValue
    public Integer getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Integer idStudent) {
        this.idStudent = idStudent;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "grade")
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "thesis")
    public String getThesis() {
        return thesis;
    }

    public void setThesis(String thesis) {
        this.thesis = thesis;
    }

    @Basic
    @Column(name = "avatar")
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    @Basic
    @Column(name = "cv")
    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return Objects.equals(idStudent, that.idStudent) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(grade, that.grade) &&
                Objects.equals(thesis, that.thesis) &&
                Objects.equals(avatar, that.avatar) &&
                Objects.equals(cv, that.cv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStudent, name, surname, phone, email, grade, thesis, avatar, cv);
    }
}
