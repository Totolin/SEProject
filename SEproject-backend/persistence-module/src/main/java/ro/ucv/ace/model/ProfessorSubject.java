package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Geo on 03.04.2016.
 */
@Entity
@Table(name = "PROFESSOR_SUBJECT")
@Getter
@Setter
public class ProfessorSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "PROFESSOR_SSN", referencedColumnName = "SSN", nullable = false)
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_NAME", referencedColumnName = "NAME", nullable = false)
    private Subject subject;

    @Basic
    @Column(name = "EVALUATION_METHOD", nullable = false)
    private String evaluationMethod;
}
