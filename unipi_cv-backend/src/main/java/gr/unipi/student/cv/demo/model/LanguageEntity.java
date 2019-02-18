package gr.unipi.student.cv.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "language", schema = "unipi_cv", catalog = "")
public class LanguageEntity {
    private Integer idLanguages;
    private String name;
    @ManyToMany(mappedBy = "languageEntities")
    private Set<StudentEntity> studentEntities = new HashSet<>();
    @Transient
    public Set<StudentEntity> getStudentEntities() {
        return studentEntities;
    }

    public void setStudentEntities(Set<StudentEntity> employees) {
        this.studentEntities = employees;
    }

    @Id
    @Column(name = "idLanguages")
    public Integer getIdLanguages() {
        return idLanguages;
    }

    public void setIdLanguages(Integer idLanguages) {
        this.idLanguages = idLanguages;
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
        LanguageEntity that = (LanguageEntity) o;
        return Objects.equals(idLanguages, that.idLanguages) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLanguages, name);
    }
}
