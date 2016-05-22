package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.ucv.ace.dto.professor.StudentGradeDto;
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
    public ResponseEntity<Void> postGrade(@Valid @RequestBody StudentGradeDto studentGradeDto, BindingResult bindResult) throws RestEntityBindingException, RestEntityAlreadyExistsException, RestForeignKeyNotFoundException, ServiceEntityNotFoundException {
        if (bindResult.hasErrors()) {
            throw new RestEntityBindingException(bindResult.getFieldErrors(), eMM.get("professor.grades.binding"));
        }

        try {
            professorService.grade(studentGradeDto);
        } catch (ServiceEntityAlreadyExistsException e) {
            throw new RestEntityAlreadyExistsException(eMM.get("professor.grades.alreadyExists"));
        } catch (ServiceForeignKeyNotFoundException e) {
            throw new RestForeignKeyNotFoundException(e.getMessage());
        } catch (ServiceEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
