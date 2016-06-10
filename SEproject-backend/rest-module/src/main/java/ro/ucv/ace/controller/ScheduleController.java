package ro.ucv.ace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ucv.ace.dto.schedule.PreviewScheduleDto;
import ro.ucv.ace.service.ScheduleManagementService;

import java.util.List;

/**
 * Created by Geo on 10.06.2016.
 */
@RestController
@RequestMapping(value = "/")
public class ScheduleController {

    @Autowired
    private ScheduleManagementService scheduleManagementService;

    @RequestMapping(value = "/schedules", method = RequestMethod.GET)
    public ResponseEntity<List<PreviewScheduleDto>> getAllSchedule() {
        List<PreviewScheduleDto> schedules = scheduleManagementService.getAll();

        return new ResponseEntity<List<PreviewScheduleDto>>(schedules, HttpStatus.OK);
    }

    @RequestMapping(value = "/groups/{id}/schedules", method = RequestMethod.GET)
    public ResponseEntity<List<PreviewScheduleDto>> getSchedulesByGroup(@PathVariable Integer id) {
        List<PreviewScheduleDto> schedules = scheduleManagementService.getByGroup(id);

        return new ResponseEntity<List<PreviewScheduleDto>>(schedules, HttpStatus.OK);
    }
}
