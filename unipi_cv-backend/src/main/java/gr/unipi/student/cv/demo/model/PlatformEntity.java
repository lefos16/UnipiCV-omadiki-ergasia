package gr.unipi.student.cv.demo.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "platform", schema = "unipi_cv", catalog = "")
public class PlatformEntity {
    private Integer idPlatforms;
    private String name;
    @ManyToMany(mappedBy = "platformEntities")
    private Set<StudentEntity> studentEntities = new HashSet<>();

    @Transient
    public Set<StudentEntity> getStudentEntities() {
        return studentEntities;
    }

    public void setStudentEntities(Set<StudentEntity> employees) {
        this.studentEntities = employees;
    }

    @Id

    @Column(name = "idPlatforms")
    public Integer getIdPlatforms() {
        return idPlatforms;
    }

    public void setIdPlatforms(Integer idPlatforms) {
        this.idPlatforms = idPlatforms;
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
        PlatformEntity that = (PlatformEntity) o;
        return Objects.equals(idPlatforms, that.idPlatforms) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPlatforms, name);
    }
}
