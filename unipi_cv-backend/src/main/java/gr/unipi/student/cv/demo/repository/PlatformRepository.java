package gr.unipi.student.cv.demo.repository;

import gr.unipi.student.cv.demo.model.PlatformEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<PlatformEntity, Integer> {
}
