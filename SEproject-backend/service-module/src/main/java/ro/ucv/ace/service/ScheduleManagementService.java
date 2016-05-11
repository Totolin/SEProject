package ro.ucv.ace.service;

import ro.ucv.ace.dto.PreviewScheduleDto;
import ro.ucv.ace.dto.SaveScheduleDto;
import ro.ucv.ace.dto.UpdateScheduleDto;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;

import java.util.List;

/**
 * Created by Geo on 06.05.2016.
 */
public interface ScheduleManagementService {

    void save(SaveScheduleDto saveScheduleDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException;

    List<PreviewScheduleDto> getAll();

    PreviewScheduleDto getById(Integer id) throws ServiceEntityNotFoundException;

    void update(UpdateScheduleDto updateScheduleDto, Integer id) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException;

    void delete(Integer id) throws ServiceEntityNotFoundException;
}
