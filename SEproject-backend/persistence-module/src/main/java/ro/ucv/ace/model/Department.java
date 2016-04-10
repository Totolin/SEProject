package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Geo on 03.04.2016.
 */
@Entity
@Table(name = "DEPARTMENT")
@Getter
@Setter
public class Department {

    @Id
    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIRECTOR_SSN", referencedColumnName = "SSN", nullable = false)
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
