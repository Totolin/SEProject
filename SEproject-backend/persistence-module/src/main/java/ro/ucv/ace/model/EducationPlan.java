package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Geo on 03.04.2016.
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
    @JoinColumn(name = "SECTION_ID", referencedColumnName = "ID", nullable = false)
    private Section section;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_NAME", referencedColumnName = "NAME", nullable = false)
    private Subject subject;
}
