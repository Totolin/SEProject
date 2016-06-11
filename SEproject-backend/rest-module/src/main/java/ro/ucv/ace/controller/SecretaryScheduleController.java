package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ro.ucv.ace.dto.schedule.PreviewScheduleDto;
import ro.ucv.ace.dto.schedule.SaveScheduleDto;
import ro.ucv.ace.dto.schedule.UpdateScheduleDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.misc.ExceptionMessageManager;
import ro.ucv.ace.service.ScheduleManagementService;

import javax.validation.Valid;

/**
 * This class is a REST controller.
 *
 * @author Georgian Vladutu
 */
@RestController
@RequestMapping(value = "/secretaries")
public class SecretaryScheduleController {

    @Autowired
    private ExceptionMessageManager eMM;

    @Autowired
    private ScheduleManagementService scheduleManagementService;


    @RequestMapping(value = "/schedules/{id}", method = RequestMethod.GET)
    public ResponseEntity<PreviewScheduleDto> getById(@PathVariable Integer id) throws RestEntityNotFoundException {
        try {
            PreviewScheduleDto schedule = scheduleManagementService.getById(id);

            return new ResponseEntity<PreviewScheduleDto>(schedule, HttpStatus.OK);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("schedule.notFound"));
        }
    }

    @RequestMapping(value = "/schedules", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateScheduleDto updateScheduleDto, BindingResult bindResult)
            throws RestEntityNotFoundException, RestEntityBindingException, RestForeignKeyNotFoundException {
        if (bindResult.hasErrors()) {
            throw new RestEntityBindingException(bindResult.getFieldErrors(), eMM.get("schedule.binding"));
        }

        try {
            scheduleManagementService.update(updateScheduleDto, updateScheduleDto.getId());
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("schedule.notFound"));
        } catch (ServiceForeignKeyNotFoundException e) {
            throw new RestForeignKeyNotFoundException(e.getMessage());
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/schedules", method = RequestMethod.POST)
    public ResponseEntity<Void> saveSchedule(@Valid @RequestBody SaveScheduleDto saveScheduleDto, BindingResult bindResult) throws RestEntityBindingException, RestEntityAlreadyExistsException, RestForeignKeyNotFoundException, RestEntityNotFoundException {
        if (bindResult.hasErrors()) {
            throw new RestEntityBindingException(bindResult.getFieldErrors(), eMM.get("schedule.binding"));
        }

        try {
            scheduleManagementService.save(saveScheduleDto);
        } catch (ServiceEntityAlreadyExistsException e) {
            throw new RestEntityAlreadyExistsException(eMM.get("schedule.alreadyExists"));
        } catch (ServiceForeignKeyNotFoundException e) {
            throw new RestForeignKeyNotFoundException(e.getMessage());
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("schedule.teach"));
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/schedules/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteSchedule(@PathVariable Integer id) throws RestEntityNotFoundException {
        try {
            scheduleManagementService.delete(id);
        } catch (ServiceEntityNotFoundException e) {
            throw new RestEntityNotFoundException(eMM.get("schedule.notFound"));
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
