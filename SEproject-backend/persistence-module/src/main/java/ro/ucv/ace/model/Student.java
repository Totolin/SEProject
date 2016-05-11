package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * This is an model entity that maps the STUDENT table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "STUDENT")
@PrimaryKeyJoinColumn(name = "ID")
@Getter
@Setter
public class Student extends Person {

    @ManyToOne
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID", nullable = true)
    private Group group;

    @Basic
    @Column(name = "SUBGROUP", nullable = false)
    private String subgroup;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<StudentSubject> studentSubjects;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Person person = (Person) o;

        return ssn != null ? ssn.equals(person.ssn) : person.ssn == null;

    }

    @Override
    public int hashCode() {
        return ssn != null ? ssn.hashCode() : 0;
    }
}
