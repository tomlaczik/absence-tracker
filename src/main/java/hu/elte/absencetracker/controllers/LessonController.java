package hu.elte.absencetracker.controllers;

import hu.elte.absencetracker.entities.Absence;
import hu.elte.absencetracker.entities.Lesson;
import hu.elte.absencetracker.entities.User;
import hu.elte.absencetracker.repositories.AbsenceRepository;
import hu.elte.absencetracker.repositories.LessonRepository;
import hu.elte.absencetracker.repositories.UserRepository;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/lessons")
public class LessonController {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private AbsenceRepository absenceRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticatedUser authenticatedUser;

    @GetMapping("")
    public ResponseEntity<Iterable<Lesson>> getAll() {
        return ResponseEntity.ok(lessonRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> get(@PathVariable Integer id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        if (lesson.isPresent()) {
            return ResponseEntity.ok(lesson.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Lesson> post(@RequestBody Lesson lesson) {
        User authUser = authenticatedUser.getUser();
        if (authUser.getRole() == User.Role.ADMIN) {
            return ResponseEntity.ok(lessonRepository.save(lesson));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Lesson> update(@PathVariable Integer id, @RequestBody Lesson newLesson) {
        User authUser = authenticatedUser.getUser();
        if (authUser.getRole() == User.Role.ADMIN) {
            Optional<Lesson> oldLesson = lessonRepository.findById(id);
            if (oldLesson.isPresent()) {
                newLesson.setId(id);
                return ResponseEntity.ok(lessonRepository.save(newLesson));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Lesson> delete(@PathVariable Integer id) {
        User authUser = authenticatedUser.getUser();
        if (authUser.getRole() == User.Role.ADMIN) {
            Optional<Lesson> lesson = lessonRepository.findById(id);
            if (lesson.isPresent()) {
                lessonRepository.deleteById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/{id}/absences")
    public ResponseEntity<Iterable<Absence>> getAbsences(@PathVariable Integer id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        User authUser = authenticatedUser.getUser();
        if (lesson.isPresent()) {
            if (lesson.get().getTeacher().getId().equals(authUser.getId()) || authUser.getRole() == User.Role.ADMIN) {
                return ResponseEntity.ok(lesson.get().getAbsences());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}/students")
    public ResponseEntity<Iterable<User>> getStudents(@PathVariable Integer id) {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        User authUser = authenticatedUser.getUser();
        if (lesson.isPresent()) {
            if (lesson.get().getTeacher().getId().equals(authUser.getId()) || authUser.getRole() == User.Role.ADMIN) {
                return ResponseEntity.ok(lesson.get().getStudents());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/absences")
    public ResponseEntity<Absence> postAbsence(@PathVariable Integer id, @RequestBody Absence absence, @RequestParam("user") Integer userId) {
        Optional<Lesson> lesson = lessonRepository.findById(id);
        User authUser = authenticatedUser.getUser();
        if (lesson.isPresent()) {
            if (lesson.get().getTeacher().getId().equals(authUser.getId()) || authUser.getRole() == User.Role.ADMIN) {
                absence.setLesson(lesson.get());
                absence.setUser(userRepository.findById(userId).get());
                return ResponseEntity.ok(absenceRepository.save(absence));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
