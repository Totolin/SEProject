package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Geo on 03.04.2016.
 */
@Entity
@Table(name = "USER")
@Getter
@Setter
public class User {

    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Basic
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Basic
    @Column(name = "STATE", nullable = false)
    private String state;

    @Basic
    @Column(name = "TYPE_U", nullable = false)
    private String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null) {
            return false;
        }
        return password != null ? password.equals(user.password) : user.password == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
