package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.ucv.ace.dto.group.PreviewGroupDto;
import ro.ucv.ace.dto.professor.ProfessorInfoDto;
import ro.ucv.ace.dto.professor.SaveStudentGradeDto;
import ro.ucv.ace.dto.professor.UpdateStudentGradeDto;
import ro.ucv.ace.dto.student.StudentInfoWithGradeDto;
import ro.ucv.ace.dto.subject.PreviewSubjectDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.misc.ExceptionMessageManager;
import ro.ucv.ace.service.ProfessorService;
import ro.ucv.ace.service.ProfessorSubjectService;

import javax.validation.Valid;
import java.util.List;

/**
 * This class is a REST controller.
 *
 * @author Georgian Vladutu
 */
@RestController
@RequestMapping(value = "/professors")
public class ProfessorsController {

    @Autowired
    private ProfessorSubjectService professorSubjectService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    ExceptionMessageManager eMM;

    @RequestMapping(value = "/grades", method = RequestMethod.POST)
    public ResponseEntity<Void> postGrade(@Valid @RequestBody SaveStudentGradeDto saveStudentGradeDto, BindingResult bindResult) throws RestEntityBindingException, RestEntityAlreadyExistsException, RestEntityNotFoundException {
        if (bindResult.hasErrors()) {
            throw new RestEntityBindingException(bindResult.getFieldErrors(), eMM.get("professor.grades.binding"));
        }


        try {
            professorSubjectService.saveGrade(saveStudentGradeDto);
        } catch (ServiceEntityAlreadyExistsException e) {
            throw new RestEntityAlreadyExistsException(eMM.get("professor.grades.alreadyExists"));
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("professor.grades.error"));
        }


        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/grades", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateGrade(@Valid @RequestBody UpdateStudentGradeDto updateStudentGradeDto, BindingResult bindingResult) throws RestEntityBindingException, RestEntityNotFoundException {
        if (bindingResult.hasErrors()) {
            throw new RestEntityBindingException(bindingResult.getFieldErrors(), eMM.get("professor.grades.binding"));
        }

        try {
            professorSubjectService.updateGrade(updateStudentGradeDto);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("professor.grades.update"));
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
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

    @RequestMapping(value = "/subjects/{subjectId}/groups/{groupId}/students", method = RequestMethod.GET)
    public ResponseEntity<List<StudentInfoWithGradeDto>> getAllStudents(@PathVariable Integer subjectId, @PathVariable Integer groupId) {
        List<StudentInfoWithGradeDto> all = professorSubjectService.getAllByGroup(subjectId, groupId);

        return new ResponseEntity<List<StudentInfoWithGradeDto>>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/info", method = RequestMethod.GET)
    public ResponseEntity<ProfessorInfoDto> getStudentInfo(@PathVariable Integer id) throws RestEntityNotFoundException {
        ProfessorInfoDto professor;
        try {
            professor = professorService.getProfessorInfo(id);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("professor.notFound"));
        }

        return new ResponseEntity<ProfessorInfoDto>(professor, HttpStatus.OK);
    }
}
