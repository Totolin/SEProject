package ro.ucv.ace.dao.impl;

import ro.ucv.ace.dao.JpaDao;
import ro.ucv.ace.dao.PersonDao;
import ro.ucv.ace.exception.DaoEntityAlreadyExistsException;
import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.Person;

import java.util.List;
import java.util.Optional;

/**
 * Created by Geo on 03.04.2016.
 */
public class PersonDaoImpl extends JpaDao<Person> implements PersonDao {

    @Override
    public List<Person> getAll() {
        return getAllEntities();
    }

    @Override
    public Person getBySsn(String ssn) throws DaoEntityNotFoundException {
        Optional<Person> person = getJinqSource().streamAll(getEntityManager(), Person.class).where(p -> p.getSsn().equals(ssn)).findFirst();
        if (!person.isPresent()) {
            throw new DaoEntityNotFoundException();
        }

        return person.get();
    }

    @Override
    public void persist(Person person) throws DaoEntityAlreadyExistsException {
        try {
            Person p = getBySsn(person.getSsn());
        } catch (DaoEntityNotFoundException e) {
            persistEntity(person);
            return;
        }

        throw new DaoEntityAlreadyExistsException();
    }

    @Override
    public void delete(String ssn) throws DaoEntityNotFoundException {
        Person p = getBySsn(ssn);
        deleteEntity(p);
    }

    @Override
    public void update(Person person) throws DaoEntityNotFoundException {
        Person p = getBySsn(person.getSsn());
        updateEntity(person);
    }
}
