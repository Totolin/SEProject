package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * This is an model entity that maps the STUDENT_SUBJECT table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "STUDENT_SUBJECT")
@Getter
@Setter
public class StudentSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "ID", nullable = false)
    private Subject subject;

    @Column(name = "GRADE", nullable = true)
    private Integer grade;

    public StudentSubject() {
    }

    public StudentSubject(Integer studentId, Integer subjectId) {
        this.student = new Student();
        this.student.setId(studentId);

        this.subject = new Subject();
        this.subject.setId(subjectId);

        this.grade = null;
    }
}
