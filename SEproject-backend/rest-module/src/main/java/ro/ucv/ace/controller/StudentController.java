package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ucv.ace.dto.StudentGrade;
import ro.ucv.ace.exception.RestEntityNotFoundException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.misc.ExceptionMessageManager;
import ro.ucv.ace.service.StudentService;

import java.util.List;

/**
 * Created by Geo on 28.04.2016.
 */
@RestController
@RequestMapping(value = "/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    ExceptionMessageManager eMM;

    @RequestMapping(value = "/{ssn}/grades", method = RequestMethod.GET)
    public ResponseEntity<List<StudentGrade>> getAllGrades(@PathVariable String ssn) throws RestEntityNotFoundException {
        List<StudentGrade> studentGrades;

        try {
            studentGrades = studentService.getAllGrades(ssn);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("student.notFound"));
        }

        return new ResponseEntity<List<StudentGrade>>(studentGrades, HttpStatus.OK);
    }
}
