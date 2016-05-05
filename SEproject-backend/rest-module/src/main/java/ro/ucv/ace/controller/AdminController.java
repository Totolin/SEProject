package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.ucv.ace.dto.UserCreateDto;
import ro.ucv.ace.dto.UserDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.misc.ExceptionMessageManager;
import ro.ucv.ace.service.UserManagementService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by ctotolin on 24-Apr-16.
 */
@RestController
@RequestMapping(value = "/admins")
public class AdminController {

    @Autowired
    private UserManagementService userService;

    @Autowired
    private PasswordEncoder pwdEncoder;

    @Autowired
    private ExceptionMessageManager eMM;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> getAllUsers() throws RestEntityBindingException, RestEntityNotFoundException, RestInvalidPasswordException {

        List<UserDto> allUsers = null;
        allUsers = userService.getAllUsers();

        return new ResponseEntity<List<UserDto>>(allUsers, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) throws RestEntityBindingException, RestEntityNotFoundException, RestInvalidPasswordException {

        UserDto byUsername = null;
        try {
            byUsername = userService.getByUsername(username);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("user.notFound"));
        }

        return new ResponseEntity<UserDto>(byUsername, HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<Void> postUserAdd(@Valid @RequestBody UserCreateDto user, BindingResult bindResult) throws RestEntityBindingException, RestEntityNotFoundException, RestInvalidPasswordException, RestEntityAlreadyExistsException {

        if (bindResult.hasErrors()) {
            throw new RestEntityBindingException(bindResult.getFieldErrors(), eMM.get("user.binding"));
        }

        user.setPassword(pwdEncoder.encode(user.getPassword()));
        user.setState("Active");

        try {
            userService.addUser(user);
        } catch (ServiceEntityAlreadyExistsException e) {
            throw new RestEntityAlreadyExistsException(eMM.get("user.alreadyExists"));
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
