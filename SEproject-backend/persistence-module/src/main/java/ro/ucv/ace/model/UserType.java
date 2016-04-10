package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * This is an model entity that maps the TYPE table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "TYPE_TABLE", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")})
@Getter
@Setter
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Basic
    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    public UserType() {
    }

    public UserType(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserType)) {
            return false;
        }

        UserType userType = (UserType) o;

        if (id != userType.id) {
            return false;
        }
        return !(name != null ? !name.equals(userType.name) : userType.name != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}