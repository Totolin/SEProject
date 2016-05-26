package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.ucv.ace.dto.group.PreviewGroupDto;
import ro.ucv.ace.dto.professor.SaveStudentGradeDto;
import ro.ucv.ace.dto.student.StudentInfoDto;
import ro.ucv.ace.dto.subject.PreviewSubjectDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.misc.ExceptionMessageManager;
import ro.ucv.ace.service.ProfessorSubjectService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Geo on 28.04.2016.
 */
@RestController
@RequestMapping(value = "/professors")
public class ProfessorsController {

    @Autowired
    private ProfessorSubjectService professorSubjectService;

    @Autowired
    ExceptionMessageManager eMM;

    @RequestMapping(value = "/grades", method = RequestMethod.POST)
    public ResponseEntity<Void> postGrade(@Valid @RequestBody SaveStudentGradeDto saveStudentGradeDto, BindingResult bindResult) throws RestEntityBindingException, RestEntityAlreadyExistsException, RestForeignKeyNotFoundException, ServiceEntityNotFoundException {
        if (bindResult.hasErrors()) {
            throw new RestEntityBindingException(bindResult.getFieldErrors(), eMM.get("professor.grades.binding"));
        }

        try {
            professorSubjectService.grade(saveStudentGradeDto);
        } catch (ServiceEntityAlreadyExistsException e) {
            throw new RestEntityAlreadyExistsException(eMM.get("professor.grades.alreadyExists"));
        } catch (ServiceForeignKeyNotFoundException e) {
            throw new RestForeignKeyNotFoundException(e.getMessage());
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/subjects", method = RequestMethod.GET)
    public ResponseEntity<List<PreviewSubjectDto>> getAllSubjects(@PathVariable Integer id) {
        List<PreviewSubjectDto> all = professorSubjectService.getAllSubjectsByProfessor(id);

        return new ResponseEntity<List<PreviewSubjectDto>>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "/{professorId}/subjects/{subjectId}/groups", method = RequestMethod.GET)
    public ResponseEntity<List<PreviewGroupDto>> getAllGroups(@PathVariable Integer professorId, @PathVariable Integer subjectId) {
        List<PreviewGroupDto> all = professorSubjectService.getAllGroupsByProfessorAndSubject(professorId, subjectId);

        return new ResponseEntity<List<PreviewGroupDto>>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "/groups/{id}/students", method = RequestMethod.GET)
    public ResponseEntity<List<StudentInfoDto>> getAllStudents(@PathVariable Integer id) {
        List<StudentInfoDto> all = professorSubjectService.getAllByGroup(id);

        return new ResponseEntity<List<StudentInfoDto>>(all, HttpStatus.OK);
    }
}
