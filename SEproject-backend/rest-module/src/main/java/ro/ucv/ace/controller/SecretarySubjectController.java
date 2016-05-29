package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.ucv.ace.dto.department.UpdateDepartmentDto;
import ro.ucv.ace.dto.subject.PreviewSubjectDto;
import ro.ucv.ace.dto.subject.SaveSubjectDto;
import ro.ucv.ace.dto.subject.UpdateSubjectDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.misc.ExceptionMessageManager;
import ro.ucv.ace.service.SubjectService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Geo on 27.05.2016.
 */
@RestController
@RequestMapping(value = "/secretaries")
public class SecretarySubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ExceptionMessageManager eMM;

    @RequestMapping(value = "/subjects", method = RequestMethod.GET)
    public ResponseEntity<List<PreviewSubjectDto>> getAll() {
        List<PreviewSubjectDto> previewSubjectDtos = subjectService.getAllSubjects();

        return new ResponseEntity<List<PreviewSubjectDto>>(previewSubjectDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/subjects/{id}", method = RequestMethod.GET)
    public ResponseEntity<PreviewSubjectDto> getById(@PathVariable Integer id) throws RestEntityNotFoundException {

        try {
            PreviewSubjectDto subjectDto = subjectService.getById(id);

            return new ResponseEntity<PreviewSubjectDto>(subjectDto, HttpStatus.OK);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("subject.notFound"));
        }
    }

    @RequestMapping(value = "/subjects", method = RequestMethod.POST)
    public ResponseEntity<Void> save(@Valid @RequestBody SaveSubjectDto saveSubjectDto, BindingResult bindingResult) throws RestEntityBindingException, RestEntityAlreadyExistsException, RestForeignKeyNotFoundException {
        if (bindingResult.hasErrors()) {
            throw new RestEntityBindingException(bindingResult.getFieldErrors(), eMM.get("subject.binding"));
        }

        try {
            subjectService.save(saveSubjectDto);
        } catch (ServiceEntityAlreadyExistsException e) {
            throw new RestEntityAlreadyExistsException(eMM.get("subject.alreadyExists"));
        } catch (ServiceForeignKeyNotFoundException e) {
            throw new RestForeignKeyNotFoundException(e.getMessage());
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/subjects", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateSubjectDto updateSubjectDto, BindingResult bindingResult) throws RestEntityBindingException, RestEntityNotFoundException, RestForeignKeyNotFoundException {
        if (bindingResult.hasErrors()) {
            throw new RestEntityBindingException(bindingResult.getFieldErrors(), eMM.get("subject.binding"));
        }

        try {
            subjectService.update(updateSubjectDto.getId(), updateSubjectDto);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("subject.notFound"));
        } catch (ServiceForeignKeyNotFoundException e) {
            throw new RestForeignKeyNotFoundException(e.getMessage());
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/subjects/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws RestEntityNotFoundException {

        try {
            subjectService.delete(id);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("subjects.notFound"));
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}