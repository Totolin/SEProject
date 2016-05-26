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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Basic
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CREDITS", nullable = false)
    private int credits;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.REMOVE)
    private List<ProfessorSubject> professorSubjects;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.REMOVE)
    private List<StudentSubject> studentSubjects;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.REMOVE)
    private List<EducationPlan> educationPlans;

    @OneToMany(mappedBy = "subject")
    private List<Schedule> schedules;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Subject subject = (Subject) o;

        if (credits != subject.credits) {
            return false;
        }
        return name != null ? name.equals(subject.name) : subject.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + credits;
        return result;
    }
}
