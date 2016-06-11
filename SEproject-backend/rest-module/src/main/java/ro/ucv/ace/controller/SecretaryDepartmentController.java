package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.ucv.ace.dto.department.DepartmentDirectorDto;
import ro.ucv.ace.dto.department.DepartmentDto;
import ro.ucv.ace.dto.department.SaveDepartmentDto;
import ro.ucv.ace.dto.department.UpdateDepartmentDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.misc.ExceptionMessageManager;
import ro.ucv.ace.service.DepartmentService;

import javax.validation.Valid;
import java.util.List;

/**
 * This class is a REST controller.
 *
 * @author Georgian Vladutu
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

    @RequestMapping(value = "/departments/{id}", method = RequestMethod.GET)
    public ResponseEntity<DepartmentDto> getById(@PathVariable Integer id) throws RestEntityNotFoundException {

        try {
            DepartmentDto departmentDto = departmentService.getById(id);

            return new ResponseEntity<DepartmentDto>(departmentDto, HttpStatus.OK);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("department.notFound"));
        }
    }

    @RequestMapping(value = "/departments", method = RequestMethod.POST)
    public ResponseEntity<Void> save(@Valid @RequestBody SaveDepartmentDto saveDepartmentDto, BindingResult bindingResult) throws RestEntityBindingException, RestEntityAlreadyExistsException, RestForeignKeyNotFoundException {
        if (bindingResult.hasErrors()) {
            throw new RestEntityBindingException(bindingResult.getFieldErrors(), eMM.get("department.binding"));
        }

        try {
            departmentService.save(saveDepartmentDto);
        } catch (ServiceEntityAlreadyExistsException e) {
            throw new RestEntityAlreadyExistsException(eMM.get("department.alreadyExists"));
        } catch (ServiceForeignKeyNotFoundException e) {
            throw new RestForeignKeyNotFoundException(e.getMessage());
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/departments", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateDepartmentDto updateDepartmentDto, BindingResult bindingResult) throws RestEntityBindingException, RestEntityNotFoundException, RestForeignKeyNotFoundException {
        if (bindingResult.hasErrors()) {
            throw new RestEntityBindingException(bindingResult.getFieldErrors(), eMM.get("department.binding"));
        }

        try {
            departmentService.update(updateDepartmentDto.getId(), updateDepartmentDto);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("department.notFound"));
        } catch (ServiceForeignKeyNotFoundException e) {
            throw new RestForeignKeyNotFoundException(e.getMessage());
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/departments/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws RestEntityNotFoundException {

        try {
            departmentService.delete(id);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("department.notFound"));
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
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
