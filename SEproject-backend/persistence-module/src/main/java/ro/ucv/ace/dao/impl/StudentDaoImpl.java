package ro.ucv.ace.dao.impl;

import org.springframework.stereotype.Repository;
import ro.ucv.ace.dao.StudentDao;
import ro.ucv.ace.model.Student;

import java.util.Optional;

/**
 * This class implements StudentDao interface.
 *
 * @author Georgian Vladutu
 */
@Repository
public class StudentDaoImpl extends JpaDaoImpl<Student, String> implements StudentDao {

    @Override
    public Optional<Student> existenceCondition(Student student) {
        return streamAll()
                .where(s -> s.getSsn().equals(student.getSsn()))
                .findAny();
    }
}
