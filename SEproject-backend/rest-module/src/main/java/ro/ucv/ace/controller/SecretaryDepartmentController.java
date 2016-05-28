package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ucv.ace.dto.department.DepartmentDirectorDto;
import ro.ucv.ace.dto.department.DepartmentDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.misc.ExceptionMessageManager;
import ro.ucv.ace.service.DepartmentService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Geo on 27.05.2016.
 */
@RestController
@RequestMapping(value = "/secretaries")
public class SecretaryDepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ExceptionMessageManager eMM;

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public ResponseEntity<List<DepartmentDto>> getAll() {
        List<DepartmentDto> departmentDtoList = departmentService.getAllDepartments();

        return new ResponseEntity<List<DepartmentDto>>(departmentDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/departments/directors", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateDirector(@Valid @RequestBody DepartmentDirectorDto departmentDirectorDto, BindingResult bindingResult) throws RestEntityBindingException, RestEntityNotFoundException, RestForeignKeyNotFoundException {
        if (bindingResult.hasErrors()) {
            throw new RestEntityBindingException(bindingResult.getFieldErrors(), eMM.get("entity.binding"));
        }

        try {
            departmentService.updateDirector(departmentDirectorDto);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("department.director"));
        } catch (ServiceForeignKeyNotFoundException e) {
            throw new RestForeignKeyNotFoundException(e.getMessage());
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
