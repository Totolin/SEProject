package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.ucv.ace.dto.professor.ProfessorDto;
import ro.ucv.ace.dto.professor.SaveProfessorDto;
import ro.ucv.ace.dto.professor.UpdateProfessorDto;
import ro.ucv.ace.dto.subject.PreviewProfessorSubjectDto;
import ro.ucv.ace.dto.subject.SaveProfessorSubjectDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.misc.ExceptionMessageManager;
import ro.ucv.ace.service.ProfessorService;
import ro.ucv.ace.service.ProfessorSubjectService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Geo on 15.05.2016.
 */
@RestController
@RequestMapping("/secretaries")
public class SecretaryProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private ProfessorSubjectService professorSubjectService;

    @Autowired
    private ExceptionMessageManager eMM;

    @RequestMapping(value = "/professors", method = RequestMethod.GET)
    public ResponseEntity<List<ProfessorDto>> getAll() {
        List<ProfessorDto> professors = professorService.getAll();

        return new ResponseEntity<List<ProfessorDto>>(professors, HttpStatus.OK);
    }

    @RequestMapping(value = "/professors/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProfessorDto> getById(@PathVariable Integer id) throws RestEntityNotFoundException {
        ProfessorDto professor;
        try {
            professor = professorService.getById(id);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("professor.notFound"));
        }

        return new ResponseEntity<ProfessorDto>(professor, HttpStatus.OK);
    }

    @RequestMapping(value = "/professors", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateProfessorDto updateProfessorDto, BindingResult bindResult)
            throws RestEntityNotFoundException, RestEntityBindingException, RestForeignKeyNotFoundException {
        if (bindResult.hasErrors()) {
            throw new RestEntityBindingException(bindResult.getFieldErrors(), eMM.get("professor.binding"));
        }

        try {
            professorService.update(updateProfessorDto, updateProfessorDto.getId());
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("professor.notFound"));
        } catch (ServiceForeignKeyNotFoundException e) {
            throw new RestForeignKeyNotFoundException(e.getMessage());
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/professors", method = RequestMethod.POST)
    public ResponseEntity<Void> save(@Valid @RequestBody SaveProfessorDto saveProfessorDto, BindingResult bindResult) throws RestEntityBindingException, RestEntityAlreadyExistsException, RestForeignKeyNotFoundException {
        if (bindResult.hasErrors()) {
            throw new RestEntityBindingException(bindResult.getFieldErrors(), eMM.get("professor.binding"));
        }

        try {
            professorService.save(saveProfessorDto);
        } catch (ServiceEntityAlreadyExistsException e) {
            throw new RestEntityAlreadyExistsException(eMM.get("professor.alreadyExists"));
        } catch (ServiceForeignKeyNotFoundException e) {
            throw new RestForeignKeyNotFoundException(e.getMessage());
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/professors/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSchedule(@PathVariable Integer id) throws RestEntityNotFoundException {
        try {
            professorService.delete(id);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("professor.notFound"));
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/professors/{id}/subjects", method = RequestMethod.GET)
    public ResponseEntity<List<PreviewProfessorSubjectDto>> getAllSubjects(@PathVariable Integer id) {
        List<PreviewProfessorSubjectDto> all = professorSubjectService.getAllProfessorSubjects(id);

        return new ResponseEntity<List<PreviewProfessorSubjectDto>>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "/professors/subjects", method = RequestMethod.POST)
    public ResponseEntity<Void> saveProfessorSubject(@Valid @RequestBody SaveProfessorSubjectDto saveProfessorSubjectDto, BindingResult bindingResult) throws RestEntityBindingException, RestEntityAlreadyExistsException, RestForeignKeyNotFoundException {
        if (bindingResult.hasErrors()) {
            throw new RestEntityBindingException(bindingResult.getFieldErrors(), eMM.get("professorSubject.binding"));
        }

        try {
            professorSubjectService.save(saveProfessorSubjectDto);
        } catch (ServiceEntityAlreadyExistsException e) {
            throw new RestEntityAlreadyExistsException(eMM.get("professorSubject.alreadyExists"));
        } catch (ServiceForeignKeyNotFoundException e) {
            throw new RestForeignKeyNotFoundException(e.getMessage());
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/professors/subjects/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProfessorSubject(@PathVariable Integer id) throws RestEntityNotFoundException {

        try {
            professorSubjectService.delete(id);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("professorSubject.notFound"));
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
