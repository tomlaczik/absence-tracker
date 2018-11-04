package hu.elte.absencetracker.controllers;

import hu.elte.absencetracker.entities.Lesson;
import hu.elte.absencetracker.entities.Subject;
import hu.elte.absencetracker.entities.User;
import hu.elte.absencetracker.repositories.LessonRepository;
import hu.elte.absencetracker.repositories.SubjectRepository;
import hu.elte.absencetracker.security.AuthenticatedUser;
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
        if(authUser.getRole().equals("ADMIN")){
            return ResponseEntity.ok(subjectRepository.save(subject));
        } else{
            return ResponseEntity.badRequest().build();
        }
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Subject> update(@PathVariable Integer id, @RequestBody Subject newSubject) {
        Optional<Subject> oldSubject = subjectRepository.findById(id);
        User authUser = authenticatedUser.getUser();
        if(oldSubject.isPresent()) {
            if(authUser.getRole().equals("ADMIN")){
                newSubject.setId(id);
                return ResponseEntity.ok(subjectRepository.save(newSubject));
            } else{
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Subject> delete(@PathVariable Integer id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        User authUser = authenticatedUser.getUser();
        if(subject.isPresent()) {
            if(authUser.getRole().equals("ADMIN")){
                subjectRepository.deleteById(id);
                return ResponseEntity.ok().build();
            } else{
                 return ResponseEntity.badRequest().build();
            }
            
        } else {
            return ResponseEntity.notFound().build();
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
        Optional<Subject> subject = subjectRepository.findById(id);
        User authUser = authenticatedUser.getUser();
        if(subject.isPresent()) {
            if(authUser.getRole().equals("ADMIN")){
                 lesson.setSubject(subject.get());
                return ResponseEntity.ok(lessonRepository.save(lesson));
            } else{
                return ResponseEntity.badRequest().build();
            }
            
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
