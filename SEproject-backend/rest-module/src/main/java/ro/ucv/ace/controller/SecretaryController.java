package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ucv.ace.dto.ScheduleDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.misc.ExceptionMessageManager;
import ro.ucv.ace.service.ScheduleManagementService;

import javax.validation.Valid;

/**
 * Created by Geo on 06.05.2016.
 */
@RestController
@RequestMapping(value = "/secretaries")
public class SecretaryController {

    @Autowired
    private ExceptionMessageManager eMM;

    @Autowired
    private ScheduleManagementService scheduleManagementService;

    @RequestMapping(value = "/schedules", method = RequestMethod.POST)
    public ResponseEntity<Void> saveSchedule(@Valid @RequestBody ScheduleDto scheduleDto, BindingResult bindResult) throws RestEntityBindingException, RestEntityAlreadyExistsException, RestForeignKeyNotFoundException {
        if (bindResult.hasErrors()) {
            throw new RestEntityBindingException(bindResult.getFieldErrors(), eMM.get("schedule.binding"));
        }

        try {
            scheduleManagementService.save(scheduleDto);
        } catch (ServiceEntityAlreadyExistsException e) {
            throw new RestEntityAlreadyExistsException(eMM.get("schedule.alreadyExists"));
        } catch (ServiceForeignKeyNotFoundException e) {
            throw new RestForeignKeyNotFoundException(e.getMessage());
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
