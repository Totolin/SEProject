package ro.ucv.ace.service;

import ro.ucv.ace.dto.department.DepartmentDto;

import java.util.List;

/**
 * Created by Geo on 28.05.2016.
 */
public interface DepartmentService {

    List<DepartmentDto> getAllDepartments();
}
