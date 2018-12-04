package hu.elte.absencetracker.controllers;

import hu.elte.absencetracker.entities.Absence;
import hu.elte.absencetracker.entities.Lesson;
import hu.elte.absencetracker.entities.User;
import hu.elte.absencetracker.repositories.UserRepository;
import hu.elte.absencetracker.security.AuthenticatedUser;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticatedUser authenticatedUser;

    @GetMapping("")
    public ResponseEntity<Iterable<User>> getAll() {
        User authUser = authenticatedUser.getUser();
        if (authUser.getRole() == User.Role.ADMIN) {
            return ResponseEntity.ok(userRepository.findAll());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }
    
    @GetMapping("/me")
    public ResponseEntity<User> login() {
        return ResponseEntity.ok(userRepository.findById(authenticatedUser.getUser().getId()).get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        User authUser = authenticatedUser.getUser();
        if (user.isPresent()) {
            if(authUser.getId().equals(user.get().getId()) || authUser.getRole() == User.Role.ADMIN){
                return ResponseEntity.ok(user.get());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("register")
        public ResponseEntity<User> register(@RequestBody User user) {
        Optional<User> oUser = userRepository.findByUsername(user.getUsername());
        if (oUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRole(User.Role.STUDENT);
        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/{id}/activeLessons")
        public ResponseEntity<Iterable<Lesson>> getActiveLessons(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        User authUser = authenticatedUser.getUser();
        if (user.isPresent()) {
            if(authUser.getId().equals(user.get().getId()) || authUser.getRole() == User.Role.ADMIN){
                return ResponseEntity.ok(user.get().getActiveLessons());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/taughtLessons")
        public ResponseEntity<Iterable<Lesson>> getTaughtLessons(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        User authUser = authenticatedUser.getUser();
        if (user.isPresent()) {
            if(authUser.getId().equals(user.get().getId()) || authUser.getRole() == User.Role.ADMIN){
                return ResponseEntity.ok(user.get().getTaughtLessons());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/absences")
        public ResponseEntity<Iterable<Absence>> getAbsences(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        User authUser = authenticatedUser.getUser();
        if (user.isPresent()) {
            if(authUser.getId().equals(user.get().getId()) || authUser.getRole() == User.Role.ADMIN){
                return ResponseEntity.ok(user.get().getAbsences());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
