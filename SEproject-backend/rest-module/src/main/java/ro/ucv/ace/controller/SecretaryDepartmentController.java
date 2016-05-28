package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ucv.ace.dto.department.DepartmentDto;
import ro.ucv.ace.service.DepartmentService;

import java.util.List;

/**
 * Created by Geo on 27.05.2016.
 */
@RestController
@RequestMapping(value = "/secretaries")
public class SecretaryDepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public ResponseEntity<List<DepartmentDto>> getAll() {
        List<DepartmentDto> departmentDtoList = departmentService.getAllDepartments();

        return new ResponseEntity<List<DepartmentDto>>(departmentDtoList, HttpStatus.OK);
    }
}
