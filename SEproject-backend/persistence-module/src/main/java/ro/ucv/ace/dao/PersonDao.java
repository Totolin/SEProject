package ro.ucv.ace.dao;

import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.Person;

/**
 * This interfaces provides methods for working with Person entity explicitly (and PERSON database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface PersonDao extends JpaDao<Person, Integer> {

    /**
     * Returns the person whose SSN is the same as the method parameter.
     *
     * @param ssn SSN of the person
     * @return Person
     * @throws DaoEntityNotFoundException if the person is not found
     */
    Person findBySsn(String ssn) throws DaoEntityNotFoundException;
}
