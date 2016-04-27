package ro.ucv.ace.dao.impl;

import org.springframework.stereotype.Repository;
import ro.ucv.ace.dao.StudentSubjectDao;
import ro.ucv.ace.model.StudentSubject;

import java.util.Optional;

/**
 * This class implements StudentSubjectDao interface.
 *
 * @author Georgian Vladutu
 */
@Repository
public class StudentSubjectDaoImpl extends JpaDaoImpl<StudentSubject, Integer> implements StudentSubjectDao {

    @Override
    public Optional<StudentSubject> existenceCondition(StudentSubject studentSubject) {
        String studentSsn = studentSubject.getStudent().getSsn();
        String subjectName = studentSubject.getSubject().getName();

        return streamAll()
                .where(ss -> ss.getStudent().getSsn().equals(studentSsn) && ss.getSubject().getName().equals(subjectName))
                .findAny();
    }
}
