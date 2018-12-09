package hu.elte.absencetracker.controllers;

import hu.elte.absencetracker.entities.Absence;
import hu.elte.absencetracker.entities.User;
import hu.elte.absencetracker.repositories.AbsenceRepository;
import hu.elte.absencetracker.security.AuthenticatedUser;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/absences")
public class AbsenceController {

    @Autowired
    private AbsenceRepository absenceRepository;

    @Autowired
    private AuthenticatedUser authenticatedUser;

    @GetMapping("")
    public ResponseEntity<Iterable<Absence>> getAll() {
        User authUser = authenticatedUser.getUser();
        if (authUser.getRole() == User.Role.ADMIN) {
            return ResponseEntity.ok(absenceRepository.findAll());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Absence> get(@PathVariable Integer id) {
        User authUser = authenticatedUser.getUser();
        if (authUser.getRole() == User.Role.ADMIN) {
            Optional<Absence> absence = absenceRepository.findById(id);
            if (absence.isPresent()) {
                return ResponseEntity.ok(absence.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Absence> post(@RequestBody Absence absence) {
        User authUser = authenticatedUser.getUser();
        if (authUser.getRole() == User.Role.ADMIN) {
            return ResponseEntity.ok(absenceRepository.save(absence));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Absence> update(@PathVariable Integer id, @RequestBody Absence newAbsence) {
        User authUser = authenticatedUser.getUser();
        if (authUser.getRole() == User.Role.ADMIN) {
            Optional<Absence> oldAbsence = absenceRepository.findById(id);
            if (oldAbsence.isPresent()) {
                newAbsence.setId(id);
                return ResponseEntity.ok(absenceRepository.save(newAbsence));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Absence> delete(@PathVariable Integer id) {
        User authUser = authenticatedUser.getUser();
        if (authUser.getRole() == User.Role.TEACHER) {
            Optional<Absence> absence = absenceRepository.findById(id);
            if (absence.isPresent()) {
                absenceRepository.deleteById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
