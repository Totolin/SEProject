package ro.ucv.ace.dao.impl;

import org.springframework.stereotype.Repository;
import ro.ucv.ace.dao.DepartmentDao;
import ro.ucv.ace.model.Department;

import java.util.Optional;

/**
 * This class implements DepartmentDao interface.
 *
 * @author Georgian Vladutu
 */
@Repository
public class DepartmentDaoImpl extends JpaDaoImpl<Department, String> implements DepartmentDao {

    @Override
    public Optional<Department> existenceCondition(Department department) {
        return streamAll()
                .where(d -> d.getName().equals(department.getName()))
                .findAny();
    }
}
