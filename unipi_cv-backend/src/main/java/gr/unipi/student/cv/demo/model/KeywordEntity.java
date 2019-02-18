package gr.unipi.student.cv.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "platform_keyword", schema = "unipi_cv", catalog = "")
public class KeywordEntity {
    private Integer idKeyword;
    private String name;
    @ManyToMany(mappedBy = "KeywordEntities")
    private Set<StudentEntity> studentEntities = new HashSet<>();

    @Transient
    public Set<StudentEntity> getStudentEntities() {
        return studentEntities;
    }

    public void setStudentEntities(Set<StudentEntity> employees) {
        this.studentEntities = employees;
    }

    @Id
    @Column(name = "idPlatform_Keyword")
    @GeneratedValue
    public Integer getIdKeyword() {
        return idKeyword;
    }

    public void setIdKeyword(Integer idPlatformKeyword) {
        this.idKeyword = idPlatformKeyword;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeywordEntity that = (KeywordEntity) o;
        return Objects.equals(idKeyword, that.idKeyword) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKeyword, name);
    }
}
