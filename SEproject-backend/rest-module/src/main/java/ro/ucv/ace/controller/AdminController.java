package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ucv.ace.dto.UserDto;
import ro.ucv.ace.dto.UserLoginDto;
import ro.ucv.ace.exception.RestEntityBindingException;
import ro.ucv.ace.exception.RestEntityNotFoundException;
import ro.ucv.ace.exception.RestInvalidPasswordException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.misc.ExceptionMessageManager;
import ro.ucv.ace.service.LoginService;
import ro.ucv.ace.service.UserManagementService;

import javax.validation.Valid;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;

/**
 * Created by ctotolin on 24-Apr-16.
 */
@RestController
@RequestMapping(value = "/")
public class AdminController {

    @Autowired
    private UserManagementService userService;

    @Autowired
    private PasswordEncoder pwdEncoder;

    @Autowired
    private ExceptionMessageManager eMM;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> postGetUsers() throws RestEntityBindingException, RestEntityNotFoundException, RestInvalidPasswordException {

        List<UserDto> allUsers = null;
        allUsers = userService.getAllUsers();

        return new ResponseEntity<List<UserDto>>(allUsers, HttpStatus.OK);
    }
}
