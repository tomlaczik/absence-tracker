package hu.elte.absencetracker.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    @NotNull
    private String name;
    
    /*
    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;
    */
    
    @ManyToMany(mappedBy = "students")
    private List<Lesson> activeLessons;
    
    @OneToMany(mappedBy = "teacher")
    private List<Lesson> taughtLessons;
    
    @OneToMany(mappedBy = "user")
    private List<Absence> absences;
      
}
