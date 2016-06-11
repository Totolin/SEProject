package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * This is an model entity that maps the SCHEDULE table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "SCHEDULE")
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID", referencedColumnName = "ID", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "PROFESSOR_ID", referencedColumnName = "ID", nullable = false)
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID", referencedColumnName = "ID", nullable = false)
    private Group group;

    @Column(name = "DAY", nullable = false)
    private Integer day;

    @Column(name = "HOUR", nullable = false)
    private Integer hour;

    @Basic
    @Column(name = "ROOM", nullable = false)
    private String room;
}
