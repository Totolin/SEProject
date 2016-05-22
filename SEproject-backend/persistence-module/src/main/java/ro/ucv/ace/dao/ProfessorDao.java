package ro.ucv.ace.dao;

import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.Professor;

/**
 * This interfaces provides methods for working with Professor entity explicitly (and PROFESSOR database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface ProfessorDao extends JpaDao<Professor, Integer> {

    Professor findBySsn(String ssn) throws DaoEntityNotFoundException;
}
