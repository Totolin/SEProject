package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.ucv.ace.dto.student.SaveStudentDto;
import ro.ucv.ace.dto.student.StudentInfoDto;
import ro.ucv.ace.dto.student.UpdateStudentDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.misc.ExceptionMessageManager;
import ro.ucv.ace.service.StudentService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Geo on 15.05.2016.
 */
@RestController
@RequestMapping(value = "/secretaries")
public class SecretaryStudentController {

    @Autowired
    private ExceptionMessageManager eMM;

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public ResponseEntity<List<StudentInfoDto>> getAllStudents() {
        List<StudentInfoDto> students = studentService.getAllStudents();

        return new ResponseEntity<List<StudentInfoDto>>(students, HttpStatus.OK);
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
    public ResponseEntity<StudentInfoDto> getById(@PathVariable Integer id) throws RestEntityNotFoundException {
        try {
            StudentInfoDto student = studentService.getStudentInfo(id);

            return new ResponseEntity<StudentInfoDto>(student, HttpStatus.OK);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("student.notFound"));
        }
    }

    @RequestMapping(value = "/students", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateStudentDto updateStudentDto, BindingResult bindResult)
            throws RestEntityNotFoundException, RestEntityBindingException, RestForeignKeyNotFoundException {
        if (bindResult.hasErrors()) {
            throw new RestEntityBindingException(bindResult.getFieldErrors(), eMM.get("student.binding"));
        }

        try {
            studentService.update(updateStudentDto, updateStudentDto.getId());
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("student.notFound"));
        } catch (ServiceForeignKeyNotFoundException e) {
            throw new RestForeignKeyNotFoundException(e.getMessage());
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/students", method = RequestMethod.POST)
    public ResponseEntity<Void> save(@Valid @RequestBody SaveStudentDto saveStudentDto, BindingResult bindResult) throws RestEntityBindingException, RestEntityAlreadyExistsException, RestForeignKeyNotFoundException {
        if (bindResult.hasErrors()) {
            throw new RestEntityBindingException(bindResult.getFieldErrors(), eMM.get("student.binding"));
        }

        try {
            studentService.save(saveStudentDto);
        } catch (ServiceEntityAlreadyExistsException e) {
            throw new RestEntityAlreadyExistsException(eMM.get("student.alreadyExists"));
        } catch (ServiceForeignKeyNotFoundException e) {
            throw new RestForeignKeyNotFoundException(e.getMessage());
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSchedule(@PathVariable Integer id) throws RestEntityNotFoundException {
        try {
            studentService.delete(id);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("student.notFound"));
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
