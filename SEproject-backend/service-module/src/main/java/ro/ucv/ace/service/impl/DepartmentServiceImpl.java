package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.DepartmentDao;
import ro.ucv.ace.dto.department.DepartmentDirectorDto;
import ro.ucv.ace.dto.department.DepartmentDto;
import ro.ucv.ace.dto.department.SaveDepartmentDto;
import ro.ucv.ace.dto.department.UpdateDepartmentDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.model.Department;
import ro.ucv.ace.service.DepartmentService;

import java.util.List;

/**
 * Created by Geo on 28.05.2016.
 */
@Service
@Transactional(rollbackFor = ServiceForeignKeyNotFoundException.class)
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentDao.findAll();

        return modelMapper.map(departments, new TypeToken<List<DepartmentDto>>() {
        }.getType());
    }

    @Override
    public DepartmentDto getById(Integer id) throws ServiceEntityNotFoundException {
        try {
            Department department = departmentDao.findOne(id);

            return modelMapper.map(department, DepartmentDto.class);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceEntityNotFoundException {
        try {
            departmentDao.delete(id);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public void save(SaveDepartmentDto saveDepartmentDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException {
        Department department = modelMapper.map(saveDepartmentDto, Department.class);

        try {
            departmentDao.save(department);
        } catch (DaoEntityAlreadyExistsException e) {
            throw new ServiceEntityAlreadyExistsException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        }
    }

    @Override
    public void update(Integer id, UpdateDepartmentDto updateDepartmentDto) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException {
        Department department = modelMapper.map(updateDepartmentDto, Department.class);

        try {
            departmentDao.update(id, department);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        }
    }

    @Override
    public void updateDirector(DepartmentDirectorDto departmentDirectorDto) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException {
        try {
            departmentDao.updateDirector(departmentDirectorDto.getProfessorId(), departmentDirectorDto.getDepartmentId());
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        }
    }
}
