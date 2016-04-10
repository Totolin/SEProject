package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Geo on 03.04.2016.
 */
@Entity
@Table(name = "SCHEDULE")
@Getter
@Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_NAME", referencedColumnName = "NAME", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "PROFESSOR_SSN", referencedColumnName = "SSN", nullable = false)
    private Professor professor;

    @Basic
    @Column(name = "DATE", nullable = false)
    private LocalDateTime date;

    @Basic
    @Column(name = "ROOM", nullable = false)
    private String room;
}
