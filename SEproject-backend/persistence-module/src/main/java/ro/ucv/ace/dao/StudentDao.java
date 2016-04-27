package ro.ucv.ace.dao;

import ro.ucv.ace.model.Student;

/**
 * This interfaces provides methods for working with Student entity explicitly (and STUDENT database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface StudentDao extends JpaDao<Student, String> {
}
