package ro.ucv.ace.dao;

import ro.ucv.ace.exception.DaoEntityAlreadyExistsException;
import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.Person;

import java.util.List;

/**
 * Created by Geo on 03.04.2016.
 */
public interface PersonDao {

    List<Person> findAll();

    Person findOne(String ssn) throws DaoEntityNotFoundException;

    void save(Person person) throws DaoEntityAlreadyExistsException;

    void delete(String ssn) throws DaoEntityNotFoundException;

    void update(String ssn, Person person) throws DaoEntityNotFoundException;
}
