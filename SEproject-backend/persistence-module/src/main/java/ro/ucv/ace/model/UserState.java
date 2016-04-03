package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * This is an model entity that maps the STATE table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "STATE", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")})
@Getter
@Setter
public class UserState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Basic
    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    public UserState() {

    }

    public UserState(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserState)) {
            return false;
        }

        UserState userState = (UserState) o;

        if (id != userState.id) {
            return false;
        }
        return !(name != null ? !name.equals(userState.name) : userState.name != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
