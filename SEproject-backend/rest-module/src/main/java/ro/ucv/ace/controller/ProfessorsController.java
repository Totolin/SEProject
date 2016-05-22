package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ucv.ace.dto.professor.SaveStudentGradeDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.misc.ExceptionMessageManager;
import ro.ucv.ace.service.ProfessorService;

import javax.validation.Valid;

/**
 * Created by Geo on 28.04.2016.
 */
@RestController
@RequestMapping(value = "/professors")
public class ProfessorsController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    ExceptionMessageManager eMM;

    @RequestMapping(value = "/grades", method = RequestMethod.POST)
    public ResponseEntity<Void> postGrade(@Valid @RequestBody SaveStudentGradeDto saveStudentGradeDto, BindingResult bindResult) throws RestEntityBindingException, RestEntityAlreadyExistsException, RestForeignKeyNotFoundException, ServiceEntityNotFoundException {
        if (bindResult.hasErrors()) {
            throw new RestEntityBindingException(bindResult.getFieldErrors(), eMM.get("professor.grades.binding"));
        }

        try {
            professorService.grade(saveStudentGradeDto);
        } catch (ServiceEntityAlreadyExistsException e) {
            throw new RestEntityAlreadyExistsException(eMM.get("professor.grades.alreadyExists"));
        } catch (ServiceForeignKeyNotFoundException e) {
            throw new RestForeignKeyNotFoundException(e.getMessage());
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
