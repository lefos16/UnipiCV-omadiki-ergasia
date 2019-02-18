package gr.unipi.student.cv.demo.repository;

import gr.unipi.student.cv.demo.model.KeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformKeywordRepository extends JpaRepository<KeywordEntity, Integer> {
}
