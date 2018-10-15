package hu.elte.absencetracker.controllers;

import hu.elte.absencetracker.entities.Absence;
import hu.elte.absencetracker.repositories.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
