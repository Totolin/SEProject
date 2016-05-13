package ro.ucv.ace.dao.impl;

import org.springframework.stereotype.Repository;
import ro.ucv.ace.dao.PersonDao;
import ro.ucv.ace.model.Person;

import java.util.Optional;

/**
 * This class implements PersonDao interface.
 *
 * @author Georgian Vladutu
 */
@Repository
public class PersonDaoImpl extends JpaDaoImpl<Person, Integer> implements PersonDao {

    @Override
    public Optional<Person> existenceCondition(Person person) {
        String ssn = person.getSsn();

        return streamAll()
                .where(p -> p.getSsn().equals(ssn))
                .findAny();
    }
}
