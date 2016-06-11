package ro.ucv.ace.dao;

import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.model.Professor;

/**
 * This interfaces provides methods for working with Professor entity explicitly (and PROFESSOR database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface ProfessorDao extends JpaDao<Professor, Integer> {

    /**
     * Returns the professor whose SSN is the same as the method parameter.
     *
     * @param ssn SSN of the professor
     * @return Professor
     * @throws DaoEntityNotFoundException if the professor is not found
     */
    Professor findBySsn(String ssn) throws DaoEntityNotFoundException;
}
