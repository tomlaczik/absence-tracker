package hu.elte.absencetracker.controllers;

import hu.elte.absencetracker.entities.Lesson;
import hu.elte.absencetracker.entities.Subject;
import hu.elte.absencetracker.entities.User;
import hu.elte.absencetracker.repositories.LessonRepository;
import hu.elte.absencetracker.repositories.SubjectRepository;
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
@RequestMapping("/subjects")
public class SubjectController {
    
    @Autowired
    private SubjectRepository subjectRepository;
    
    @Autowired
    private LessonRepository lessonRepository;
    
    @Autowired
    private AuthenticatedUser authenticatedUser;
    
    @GetMapping("")
    public ResponseEntity<Iterable<Subject>> getAll() {
        return ResponseEntity.ok(subjectRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Subject> get(@PathVariable Integer id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if(subject.isPresent()) {
            return ResponseEntity.ok(subject.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("")
    public ResponseEntity<Subject> post(@RequestBody Subject subject) {
        User authUser = authenticatedUser.getUser();
        if(authUser.getRole() == User.Role.ADMIN){
            return ResponseEntity.ok(subjectRepository.save(subject));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Subject> update(@PathVariable Integer id, @RequestBody Subject newSubject) {
        User authUser = authenticatedUser.getUser();
        if(authUser.getRole() == User.Role.ADMIN){
            Optional<Subject> oldSubject = subjectRepository.findById(id);
            if(oldSubject.isPresent()) {
                newSubject.setId(id);
                return ResponseEntity.ok(subjectRepository.save(newSubject));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Subject> delete(@PathVariable Integer id) {
        User authUser = authenticatedUser.getUser();
        if(authUser.getRole() == User.Role.ADMIN) {
            Optional<Subject> subject = subjectRepository.findById(id);
            if(subject.isPresent()) {
                subjectRepository.deleteById(id);
                return ResponseEntity.ok().build();            
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    @GetMapping("/{id}/lessons")
    public ResponseEntity<Iterable<Lesson>> getlessons(@PathVariable Integer id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if(subject.isPresent()) {
            return ResponseEntity.ok(subject.get().getLessons());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/{id}/lessons")
    public ResponseEntity<Lesson> postLesson(@PathVariable Integer id, @RequestBody Lesson lesson) {
        User authUser = authenticatedUser.getUser();
        if(authUser.getRole() == User.Role.ADMIN) {
            Optional<Subject> subject = subjectRepository.findById(id);
            if(subject.isPresent()) {
                lesson.setSubject(subject.get());
                return ResponseEntity.ok(lessonRepository.save(lesson));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
