package ro.ucv.ace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

/**
 * This is an model entity that maps the PERSON table.
 *
 * @author Georgian Vladutu
 */
@Entity
@Table(name = "PERSON")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Basic
    @Column(name = "SSN", nullable = false)
    @Pattern(regexp = "\\b[1-8]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)\\d{4}\\b")
    protected String ssn;

    @Basic
    @Column(name = "FIRST_NAME", nullable = false)
    protected String firstName;

    @Basic
    @Column(name = "LAST_NAME", nullable = false)
    protected String lastName;

    @Basic
    @Column(name = "EMAIL", nullable = false)
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    protected String email;

    @Basic
    @Column(name = "PHONE_NUMBER", nullable = false)
    protected String phoneNumber;

    @Basic
    @Column(name = "ADDRESS", nullable = false)
    protected String address;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Person person = (Person) o;

        return ssn != null ? ssn.equals(person.ssn) : person.ssn == null;

    }

    @Override
    public int hashCode() {
        return ssn != null ? ssn.hashCode() : 0;
    }
}
