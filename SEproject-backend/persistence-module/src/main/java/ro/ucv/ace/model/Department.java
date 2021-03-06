package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * This is an model entity that maps the DEPARTMENT table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "DEPARTMENT")
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME", nullable = false)
    @Basic
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIRECTOR_ID", referencedColumnName = "ID", nullable = true)
    private Professor director;

    @OneToMany(mappedBy = "department")
    private List<Professor> professors;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Department that = (Department) o;

        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
