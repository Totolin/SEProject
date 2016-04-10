package ro.ucv.ace.dao;

import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.Person;

import java.util.List;

/**
 * Created by Geo on 03.04.2016.
 */
public interface PersonDao {

    List<Person> getAll();

    Person getBySsn(String ssn) throws DaoEntityNotFoundException;

    void persist(Person person);

    void delete(String ssn);

    void update(Person person);
}
