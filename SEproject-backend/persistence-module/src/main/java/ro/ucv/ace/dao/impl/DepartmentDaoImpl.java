package ro.ucv.ace.dao.impl;

import org.springframework.stereotype.Repository;
import ro.ucv.ace.dao.DepartmentDao;
import ro.ucv.ace.exception.DaoEntityNotFoundException;
import ro.ucv.ace.exception.DaoForeignKeyNotFoundException;
import ro.ucv.ace.model.Department;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.Optional;

/**
 * This class implements DepartmentDao interface.
 *
 * @author Georgian Vladutu
 */
@Repository
public class DepartmentDaoImpl extends JpaDaoImpl<Department, Integer> implements DepartmentDao {

    @Override
    public Optional<Department> existenceCondition(Department department) {
        String name = department.getName();

        return streamAll()
                .where(d -> d.getName().equals(name))
                .findAny();
    }


    @Override
    public void removeDirector(Integer directorId) {
        Query query = getEntityManager()
                .createQuery("UPDATE Department d SET d.director.id = NULL WHERE d.director.id = :directorId")
                .setParameter("directorId", directorId);

        query.executeUpdate();
    }

    @Override
    public void updateDirector(int professorId, int departmentId) throws DaoEntityNotFoundException, DaoForeignKeyNotFoundException {
        Query query = getEntityManager()
                .createQuery("UPDATE Department d SET d.director.id = :professorId WHERE d.id = :departmentId")
                .setParameter("professorId", professorId)
                .setParameter("departmentId", departmentId);

        try {
            int updatedRows = query.executeUpdate();

            if (updatedRows == 0) {
                throw new DaoEntityNotFoundException();
            }
        } catch (PersistenceException e) {
            throw new DaoForeignKeyNotFoundException("Unable to find ro.ucv.ace.model.Professor with id " + professorId);
        }
    }
}
