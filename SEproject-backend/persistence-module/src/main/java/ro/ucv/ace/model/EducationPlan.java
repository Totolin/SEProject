package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * This is an model entity that maps the EDUCATION_PLAN table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "EDUCATION_PLAN")
@Getter
@Setter
public class EducationPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID", nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "PROFESSOR_ID", referencedColumnName = "ID", nullable = false)
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "ID", nullable = false)
    private Subject subject;

    @Basic
    @Column(name = "EVALUATION_METHOD", nullable = false)
    private String evaluationMethod;
}
