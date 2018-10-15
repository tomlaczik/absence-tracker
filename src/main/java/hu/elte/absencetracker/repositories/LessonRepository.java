package hu.elte.absencetracker.repositories;

import hu.elte.absencetracker.entities.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Integer> {
    
}
