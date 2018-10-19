package hu.elte.absencetracker.controllers;

import hu.elte.absencetracker.entities.Absence;
import hu.elte.absencetracker.entities.Lesson;
import hu.elte.absencetracker.entities.User;
import hu.elte.absencetracker.repositories.UserRepository;
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
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<User>> getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("")
    public ResponseEntity<User> post(@RequestBody User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody User newUser) {
        Optional<User> oldUser = userRepository.findById(id);
        if(oldUser.isPresent()) {
            newUser.setId(id);
            return ResponseEntity.ok(userRepository.save(newUser));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}/activeLessons")
    public ResponseEntity<Iterable<Lesson>> getActiveLessons(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return ResponseEntity.ok(user.get().getActiveLessons());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}/taughtLessons")
    public ResponseEntity<Iterable<Lesson>> getTaughtLessons(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return ResponseEntity.ok(user.get().getTaughtLessons());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}/absences")
    public ResponseEntity<Iterable<Absence>> getAbsences(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return ResponseEntity.ok(user.get().getAbsences());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
}
