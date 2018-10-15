package hu.elte.absencetracker.repositories;

import hu.elte.absencetracker.entities.Absence;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceRepository extends CrudRepository<Absence, Integer> {
    
}
