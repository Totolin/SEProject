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
 * This interface provides methods for manipulating Departments objects.
 *
 * @author Georgian Vladutu
 */
public interface DepartmentService {

    /**
     * Returns the list of departments.
     *
     * @return list of Department
     */
    List<DepartmentDto> getAllDepartments();

    /**
     * Returns the department whose id is id.
     *
     * @param id id of the department
     * @return DepartmentDto
     * @throws ServiceEntityNotFoundException if the department is not found
     */
    DepartmentDto getById(Integer id) throws ServiceEntityNotFoundException;

    /**
     * Deletes the department whose id is id.
     *
     * @param id id of the department
     * @throws ServiceEntityNotFoundException if the department is not found
     */
    void delete(Integer id) throws ServiceEntityNotFoundException;

    /**
     * Saves the department into the repository.
     *
     * @param saveDepartmentDto department to be saved
     * @throws ServiceEntityAlreadyExistsException if the department already exists
     * @throws ServiceForeignKeyNotFoundException  if any of the department components are not found
     */
    void save(SaveDepartmentDto saveDepartmentDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException;

    /**
     * Updates the department whose id is department id.
     *
     * @param id                  id of the department
     * @param updateDepartmentDto department to be updated
     * @throws ServiceEntityNotFoundException     if the department is not found
     * @throws ServiceForeignKeyNotFoundException if any of the department components are not found
     */
    void update(Integer id, UpdateDepartmentDto updateDepartmentDto) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException;

    /**
     * Updates the director of a department.
     *
     * @param departmentDirectorDto contains the directorId and the departmentId
     * @throws ServiceEntityNotFoundException     if the department is not found
     * @throws ServiceForeignKeyNotFoundException if the professor is not found
     */
    void updateDirector(DepartmentDirectorDto departmentDirectorDto) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException;
}
