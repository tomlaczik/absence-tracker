package hu.elte.absencetracker.repositories;

import hu.elte.absencetracker.entities.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Integer> {
    
}
