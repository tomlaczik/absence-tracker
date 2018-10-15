package hu.elte.absencetracker.controllers;

import hu.elte.absencetracker.entities.Absence;
import hu.elte.absencetracker.repositories.AbsenceRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/absences")
public class AbsenceController {
    
    @Autowired
    private AbsenceRepository absenceRepository;
    
    @GetMapping("")
    public ResponseEntity<Iterable<Absence>> getAll() {
        return ResponseEntity.ok(absenceRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Absence> get(@PathVariable Integer id) {
        Optional<Absence> absence = absenceRepository.findById(id);
        if(absence.isPresent()) {
            return ResponseEntity.ok(absence.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("")
    public ResponseEntity<Absence> post(@RequestBody Absence absence) {
        return ResponseEntity.ok(absenceRepository.save(absence));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Absence> update(@PathVariable Integer id, @RequestBody Absence newAbsence) {
        Optional<Absence> oldAbsence = absenceRepository.findById(id);
        if(oldAbsence.isPresent()) {
            newAbsence.setId(id);
            return ResponseEntity.ok(absenceRepository.save(newAbsence));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Absence> delete(@PathVariable Integer id) {
        Optional<Absence> absence = absenceRepository.findById(id);
        if(absence.isPresent()) {
            absenceRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
