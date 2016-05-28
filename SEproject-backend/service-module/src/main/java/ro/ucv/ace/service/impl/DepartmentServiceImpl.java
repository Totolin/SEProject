package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.DepartmentDao;
import ro.ucv.ace.dto.department.DepartmentDto;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;
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
}
