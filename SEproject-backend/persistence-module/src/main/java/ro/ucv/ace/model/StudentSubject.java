package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Geo on 03.04.2016.
 */
@Entity
@Table(name = "STUDENT_SUBJECT")
@Getter
@Setter
public class StudentSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "STUDENT_SSN", referencedColumnName = "SSN", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_NAME", referencedColumnName = "NAME", nullable = false)
    private Subject subject;

    @Column(name = "GRADE", nullable = true)
    private int grade;

}
