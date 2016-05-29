package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.ucv.ace.dto.group.PreviewGroupDto;
import ro.ucv.ace.dto.group.SaveGroupDto;
import ro.ucv.ace.dto.group.UpdateGroupDto;
import ro.ucv.ace.dto.subject.PreviewSubjectDto;
import ro.ucv.ace.dto.subject.SaveSubjectDto;
import ro.ucv.ace.dto.subject.UpdateSubjectDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.misc.ExceptionMessageManager;
import ro.ucv.ace.service.GroupService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Geo on 27.05.2016.
 */
@RestController
@RequestMapping(value = "/secretaries")
public class SecretaryGroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private ExceptionMessageManager eMM;

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public ResponseEntity<List<PreviewGroupDto>> getAll() {
        List<PreviewGroupDto> previewGroupDtos = groupService.getAllGroups();

        return new ResponseEntity<List<PreviewGroupDto>>(previewGroupDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/groups/{id}", method = RequestMethod.GET)
    public ResponseEntity<PreviewGroupDto> getById(@PathVariable Integer id) throws RestEntityNotFoundException {

        try {
            PreviewGroupDto previewGroupDto = groupService.getById(id);

            return new ResponseEntity<PreviewGroupDto>(previewGroupDto, HttpStatus.OK);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("group.notFound"));
        }
    }

    @RequestMapping(value = "/groups", method = RequestMethod.POST)
    public ResponseEntity<Void> save(@Valid @RequestBody SaveGroupDto saveGroupDto, BindingResult bindingResult) throws RestEntityBindingException, RestEntityAlreadyExistsException, RestForeignKeyNotFoundException {
        if (bindingResult.hasErrors()) {
            throw new RestEntityBindingException(bindingResult.getFieldErrors(), eMM.get("group.binding"));
        }

        try {
            groupService.save(saveGroupDto);
        } catch (ServiceEntityAlreadyExistsException e) {
            throw new RestEntityAlreadyExistsException(eMM.get("group.alreadyExists"));
        } catch (ServiceForeignKeyNotFoundException e) {
            throw new RestForeignKeyNotFoundException(e.getMessage());
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/groups", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateGroupDto updateGroupDto, BindingResult bindingResult) throws RestEntityBindingException, RestEntityNotFoundException, RestForeignKeyNotFoundException {
        if (bindingResult.hasErrors()) {
            throw new RestEntityBindingException(bindingResult.getFieldErrors(), eMM.get("group.binding"));
        }

        try {
            groupService.update(updateGroupDto.getId(), updateGroupDto);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("group.notFound"));
        } catch (ServiceForeignKeyNotFoundException e) {
            throw new RestForeignKeyNotFoundException(e.getMessage());
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/groups/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws RestEntityNotFoundException {

        try {
            groupService.delete(id);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("group.notFound"));
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
