package ro.ucv.ace.service;

import ro.ucv.ace.dto.department.DepartmentDirectorDto;
import ro.ucv.ace.dto.department.DepartmentDto;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;

import java.util.List;

/**
 * Created by Geo on 28.05.2016.
 */
public interface DepartmentService {

    List<DepartmentDto> getAllDepartments();

    void updateDirector(DepartmentDirectorDto departmentDirectorDto) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException;
}
