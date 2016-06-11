package ro.ucv.ace.dao;

import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.Student;

import java.util.List;

/**
 * This interfaces provides methods for working with Student entity explicitly (and STUDENT database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface StudentDao extends JpaDao<Student, Integer> {

    /**
     * Returns the student whose SSN is the same as the method parameter.
     *
     * @param ssn SSN of the student
     * @return Student
     * @throws DaoEntityNotFoundException if the student is not found
     */
    Student findBySsn(String ssn) throws DaoEntityNotFoundException;

    /**
     * Returns all Student entities which are part of the group whose id is groupId.
     *
     * @param groupId id of the group
     * @return list of Student
     */
    List<Student> findByGroup(Integer groupId);
}
