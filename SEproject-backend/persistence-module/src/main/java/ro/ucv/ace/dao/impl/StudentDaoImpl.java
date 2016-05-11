package ro.ucv.ace.dao.impl;

import org.springframework.stereotype.Repository;
import ro.ucv.ace.dao.StudentDao;
import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.Student;

import java.util.Optional;

/**
 * This class implements StudentDao interface.
 *
 * @author Georgian Vladutu
 */
@Repository
public class StudentDaoImpl extends JpaDaoImpl<Student, Integer> implements StudentDao {

    @Override
    public Optional<Student> existenceCondition(Student student) {
        String ssn = student.getSsn();
        return streamAll()
                .where(s -> s.getSsn().equals(ssn))
                .findAny();
    }

    @Override
    public Student findBySsn(String ssn) throws DaoEntityNotFoundException {
        Optional<Student> studentOptional = streamAll()
                .where(s -> s.getSsn().equals(ssn))
                .findAny();

        if (studentOptional.isPresent()) {
            return studentOptional.get();
        }

        throw new DaoEntityNotFoundException();
    }
}
