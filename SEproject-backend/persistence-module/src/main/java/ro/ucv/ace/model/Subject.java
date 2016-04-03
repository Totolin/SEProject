package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Geo on 03.04.2016.
 */
@Entity
@Table(name = "SUBJECT")
@Getter
@Setter
public class Subject {

    @Id
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CREDITS", nullable = false)
    private int credits;

    @OneToMany(mappedBy = "subject")
    private List<ProfessorSubject> professorSubjects;

    @OneToMany(mappedBy = "subject")
    private List<StudentSubject> studentSubjects;

}
