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

    void removeDirector(Integer directorId);

    void updateDirector(int professorId, int departmentId) throws DaoEntityNotFoundException, DaoForeignKeyNotFoundException;
}
