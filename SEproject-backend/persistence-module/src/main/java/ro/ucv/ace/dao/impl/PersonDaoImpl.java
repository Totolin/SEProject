package ro.ucv.ace.dao.impl;

import ro.ucv.ace.dao.PersonDao;
import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.Person;

import java.util.Optional;

/**
 * Created by Geo on 03.04.2016.
 */
public class PersonDaoImpl extends JpaDaoImpl<Person, String> implements PersonDao {

    @Override
    public Person exists(Person person) throws DaoEntityNotFoundException {
        Optional<Person> personOptional = streamAll()
                .where(p -> p.getSsn().equals(person.getSsn()))
                .findAny();

        if (personOptional.isPresent()) {
            return personOptional.get();
        }

        throw new DaoEntityNotFoundException();
    }
}
