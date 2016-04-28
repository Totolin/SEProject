package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Geo on 03.04.2016.
 */
@Entity
@Table(name = "GROUP_T")
@Getter
@Setter
public class Group {

    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "YEAR", nullable = false)
    private int year;

    @Basic
    @Column(name = "SPECIALIZATION", nullable = false)
    private String specialization;

    @OneToMany(mappedBy = "group")
    private List<Student> students;

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
