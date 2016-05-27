package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.ucv.ace.dto.user.UserImageDto;
import ro.ucv.ace.exception.RestEntityBindingException;
import ro.ucv.ace.exception.RestEntityNotFoundException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.misc.ExceptionMessageManager;
import ro.ucv.ace.service.UserManagementService;

import javax.validation.Valid;

/**
 * Created by Geo on 27.05.2016.
 */
@RestController
@RequestMapping("/users")
public class ImageController {

    @Autowired
    private ExceptionMessageManager eMM;

    @Autowired
    private UserManagementService userManagementService;

    @RequestMapping(value = "/images", method = RequestMethod.POST)
    public ResponseEntity<Void> upload(@Valid @RequestBody UserImageDto userImageDto, BindingResult bindingResult) throws RestEntityBindingException, RestEntityNotFoundException {
        if (bindingResult.hasErrors()) {
            throw new RestEntityBindingException(bindingResult.getFieldErrors(), eMM.get("user.binding"));
        }

        try {
            userManagementService.uploadImage(userImageDto);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("user.notFound"));
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/images", method = RequestMethod.GET)
    public ResponseEntity<UserImageDto> getImage(@PathVariable Integer id) throws RestEntityNotFoundException {
        try {
            UserImageDto userImageDto = userManagementService.getImage(id);

            return new ResponseEntity<UserImageDto>(userImageDto, HttpStatus.OK);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("user.notFound"));
        }
    }
}
