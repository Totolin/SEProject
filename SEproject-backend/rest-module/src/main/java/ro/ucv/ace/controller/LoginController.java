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
import ro.ucv.ace.dto.user.UserDto;
import ro.ucv.ace.dto.user.UserLoginDto;
import ro.ucv.ace.exception.RestEntityBindingException;
import ro.ucv.ace.exception.RestEntityNotFoundException;
import ro.ucv.ace.exception.RestInvalidPasswordException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.misc.ExceptionMessageManager;
import ro.ucv.ace.service.LoginService;

import javax.validation.Valid;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * Created by ctotolin on 24-Apr-16.
 */
@RestController
@RequestMapping(value = "/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private PasswordEncoder pwdEncoder;

    @Autowired
    private ExceptionMessageManager eMM;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<UserDto> postLogin(@Valid @RequestBody UserLoginDto userLogin, BindingResult bindResult) throws RestEntityBindingException, RestEntityNotFoundException, RestInvalidPasswordException {
        if (bindResult.hasErrors()) {
            throw new RestEntityBindingException(bindResult.getFieldErrors(), eMM.get("user.binding"));
        }
        UserDto byUsername = null;
        try {
            byUsername = loginService.getByUsername(userLogin.getUsername());
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("user.notFound"));
        }

        if (!pwdEncoder.matches(userLogin.getPassword(), byUsername.getPassword())) {
            throw new RestInvalidPasswordException(eMM.get("user.invalidPassword"));
        }

        String token = userLogin.getUsername() + ":" + userLogin.getPassword();
        byte[] bytes = Base64.getEncoder().encode(token.getBytes());
        byUsername.setAuthorization("Basic " + new String(bytes, Charset.forName("UTF-8")));

        return new ResponseEntity<UserDto>(byUsername, HttpStatus.OK);
    }
}
