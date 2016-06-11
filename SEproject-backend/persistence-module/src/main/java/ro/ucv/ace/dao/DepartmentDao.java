package ro.ucv.ace.dao;

import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.exception.DaoForeignKeyNotFoundException;
import ro.ucv.ace.model.Department;

/**
 * This interfaces provides methods for working with Department entity explicitly (and DEPARTMENT database table implicitly).
 *
 * @author Georgian Vladutu
 */
public interface DepartmentDao extends JpaDao<Department, Integer> {

    /**
     * Removes the director whose id is directorId.
     *
     * @param directorId id of the director
     */
    void removeDirector(Integer directorId);

    /**
     * Updates the department whose id is departmentId setting it's director. The new director id is professorId.
     *
     * @param professorId  id of the new director
     * @param departmentId id of the department
     * @throws DaoEntityNotFoundException     if the department is not found
     * @throws DaoForeignKeyNotFoundException if the professorId is not valid
     */
    void updateDirector(int professorId, int departmentId) throws DaoEntityNotFoundException, DaoForeignKeyNotFoundException;
}
