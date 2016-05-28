package ro.ucv.ace.service;

import ro.ucv.ace.dto.department.DepartmentDirectorDto;
import ro.ucv.ace.dto.department.DepartmentDto;
import ro.ucv.ace.dto.department.SaveDepartmentDto;
import ro.ucv.ace.dto.department.UpdateDepartmentDto;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;

import java.util.List;

/**
 * Created by Geo on 28.05.2016.
 */
public interface DepartmentService {

    List<DepartmentDto> getAllDepartments();

    DepartmentDto getById(Integer id) throws ServiceEntityNotFoundException;

    void delete(Integer id) throws ServiceEntityNotFoundException;

    void save(SaveDepartmentDto saveDepartmentDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException;

    void update(Integer id, UpdateDepartmentDto updateDepartmentDto) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException;

    void updateDirector(DepartmentDirectorDto departmentDirectorDto) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException;
}
