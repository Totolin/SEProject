package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * This is an model entity that maps the GROUP_T table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "GROUP_T")
@Getter
@Setter
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Basic
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "YEAR", nullable = false)
    private int year;

    @Basic
    @Column(name = "SPECIALIZATION", nullable = false)
    private String specialization;

    @OneToMany(mappedBy = "group")
    private List<Student> students;

    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
    private List<EducationPlan> educationPlans;

    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
    private List<Schedule> schedules;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Group group = (Group) o;

        return id != null ? id.equals(group.id) : group.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
