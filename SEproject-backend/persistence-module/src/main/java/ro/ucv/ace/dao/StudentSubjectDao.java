package ro.ucv.ace.dao;

import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.StudentSubject;

/**
 * This interfaces provides methods for working with StudentSubject entity explicitly (and STUDENT_SUBJECT database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface StudentSubjectDao extends JpaDao<StudentSubject, Integer> {

    /**
     * Returns the StudentSubject entity which contains a Student entity whose id is studentId and a Subject entity whose
     * id is subjectId.
     *
     * @param studentId id of the student
     * @param subjectId id of the subject
     * @return StudentSubject
     * @throws DaoEntityNotFoundException if the StudentSubject entity is not found
     */
    StudentSubject findByStudentAndSubject(Integer studentId, Integer subjectId) throws DaoEntityNotFoundException;

    /**
     * Updates the grade of the student whose id is studentId for the subject whose id is subjectId.
     *
     * @param studentId id of the student
     * @param subjectId id of the subject
     * @param grade     new grade
     * @throws DaoEntityNotFoundException if the student or the subject are not found
     */
    void updateGrade(int studentId, int subjectId, int grade) throws DaoEntityNotFoundException;
}
