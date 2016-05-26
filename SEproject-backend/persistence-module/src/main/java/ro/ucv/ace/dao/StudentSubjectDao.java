package ro.ucv.ace.dao;

import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.StudentSubject;

/**
 * This interfaces provides methods for working with StudentSubject entity explicitly (and STUDENT_SUBJECT database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface StudentSubjectDao extends JpaDao<StudentSubject, Integer> {

    StudentSubject findByStudentAndSubject(Integer studentId, Integer subjectId) throws DaoEntityNotFoundException;
}
