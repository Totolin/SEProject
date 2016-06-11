package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * This is an model entity that maps the PROFESSOR table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "PROFESSOR")
@PrimaryKeyJoinColumn(name = "ID")
@Getter
@Setter
public class Professor extends Person {

    @Basic
    @Column(name = "POSITION", nullable = false)
    private String position;

    @Basic
    @Column(name = "OFFICE", nullable = false)
    private String office;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "ID", nullable = true)
    private Department department;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.REMOVE)
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.REMOVE)
    private List<EducationPlan> educationPlans;

}
