package hu.elte.absencetracker.repositories;

import hu.elte.absencetracker.entities.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Subject, Integer>{
    
}
