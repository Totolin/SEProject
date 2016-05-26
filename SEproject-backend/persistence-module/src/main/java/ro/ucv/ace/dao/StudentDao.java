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

    Student findBySsn(String ssn) throws DaoEntityNotFoundException;

    List<Student> findByGroup(Integer groupId);
}
