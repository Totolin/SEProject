package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.ucv.ace.dto.secretary.PreviewSecretaryDto;
import ro.ucv.ace.dto.secretary.SaveSecretaryDto;
import ro.ucv.ace.dto.secretary.UpdateSecretaryDto;
import ro.ucv.ace.enums.UserType;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.misc.ExceptionMessageManager;
import ro.ucv.ace.service.SecretaryService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by ctotolin on 24-Apr-16.
 */
@RestController
@RequestMapping(value = "/admins")
public class AdminController {

    @Autowired
    private SecretaryService secretaryService;

    @Autowired
    private PasswordEncoder pwdEncoder;

    @Autowired
    private ExceptionMessageManager eMM;

    @RequestMapping(value = "/secretaries", method = RequestMethod.GET)
    public ResponseEntity<List<PreviewSecretaryDto>> getAll() throws RestEntityBindingException, RestEntityNotFoundException, RestInvalidPasswordException {

        List<PreviewSecretaryDto> previewSecretaryDtos = secretaryService.getAllSecretaries();

        return new ResponseEntity<List<PreviewSecretaryDto>>(previewSecretaryDtos, HttpStatus.OK);
    }

    @RequestMapping(value = "/secretaries/{id}", method = RequestMethod.GET)
    public ResponseEntity<PreviewSecretaryDto> getUserByUsername(@PathVariable Integer id) throws RestEntityBindingException, RestEntityNotFoundException, RestInvalidPasswordException {

        PreviewSecretaryDto byId = null;
        try {
            byId = secretaryService.getById(id);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("user.notFound"));
        }

        return new ResponseEntity<PreviewSecretaryDto>(byId, HttpStatus.OK);
    }

    @RequestMapping(value = "/secretaries", method = RequestMethod.POST)
    public ResponseEntity<Void> postUserAdd(@Valid @RequestBody SaveSecretaryDto saveSecretaryDto, BindingResult bindResult) throws RestEntityBindingException, RestEntityNotFoundException, RestInvalidPasswordException, RestEntityAlreadyExistsException, RestForeignKeyNotFoundException {

        if (bindResult.hasErrors()) {
            throw new RestEntityBindingException(bindResult.getFieldErrors(), eMM.get("user.binding"));
        }

        saveSecretaryDto.getAccount().setType(UserType.SECRETARY.getType());
        saveSecretaryDto.getAccount().setPassword(pwdEncoder.encode(saveSecretaryDto.getAccount().getPassword()));
        saveSecretaryDto.getAccount().setState("Active");

        try {
            secretaryService.save(saveSecretaryDto);
        } catch (ServiceEntityAlreadyExistsException e) {
            throw new RestEntityAlreadyExistsException(eMM.get("user.alreadyExists"));
        } catch (ServiceForeignKeyNotFoundException e) {
            throw new RestForeignKeyNotFoundException(e.getMessage());
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/secretaries/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) throws RestEntityNotFoundException {
        try {
            secretaryService.delete(id);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("user.notFound"));
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/secretaries", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateSecretaryDto updateSecretaryDto, BindingResult bindingResult) throws RestEntityBindingException, RestEntityNotFoundException, RestForeignKeyNotFoundException {
        if (bindingResult.hasErrors()) {
            throw new RestEntityBindingException(bindingResult.getFieldErrors(), eMM.get("user.binding"));
        }

        updateSecretaryDto.getAccount().setType(UserType.SECRETARY.getType());
        updateSecretaryDto.getAccount().setPassword(pwdEncoder.encode(updateSecretaryDto.getAccount().getPassword()));
        updateSecretaryDto.getAccount().setState("Active");


        try {
            secretaryService.update(updateSecretaryDto, updateSecretaryDto.getId());
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("user.notFound"));
        } catch (ServiceForeignKeyNotFoundException e) {
            throw new RestForeignKeyNotFoundException(e.getMessage());
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
