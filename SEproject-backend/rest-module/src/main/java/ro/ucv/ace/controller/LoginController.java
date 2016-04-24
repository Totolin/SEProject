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
import ro.ucv.ace.service.LoginService;

import javax.validation.Valid;

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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<UserDto> postLogin(@Valid @RequestBody UserLoginDto userLogin, BindingResult bindResult) {
        //return new ResponseEntity<String>("Hello from the other side test!", HttpStatus.OK);
        if (bindResult.hasErrors()) {
            //throw Exception();
        }

        UserDto byUsername = loginService.getByUsername(userLogin.getUsername());

        if(!pwdEncoder.matches(userLogin.getPassword(),byUsername.getPassword())) {
            // throw excep
        }

        return new ResponseEntity<UserDto>(byUsername, HttpStatus.OK);
    }
}
