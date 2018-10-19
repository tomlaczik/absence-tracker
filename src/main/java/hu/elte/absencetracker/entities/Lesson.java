package hu.elte.absencetracker.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Time;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @OneToMany(mappedBy = "lesson")
    private List<Absence> absences;

    /*@ManyToOne
    @JoinColumn
    @JsonIgnore
    private User teacher;*/
    
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Subject subject;
    
    /*@ManyToMany
    @JoinTable
    private List<User> students;*/
    
    @Column
    @NotNull
    private Time time;
    
    @Column
    @NotNull
    private Integer weekday;
}
