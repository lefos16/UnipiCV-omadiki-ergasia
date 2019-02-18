package gr.unipi.student.cv.demo.repository;

import gr.unipi.student.cv.demo.model.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<LanguageEntity,Integer> {
}
