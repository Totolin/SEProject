package ro.ucv.ace.service;

import ro.ucv.ace.dto.schedule.PreviewScheduleDto;
import ro.ucv.ace.dto.schedule.SaveScheduleDto;
import ro.ucv.ace.dto.schedule.UpdateScheduleDto;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;

import java.util.List;

/**
 * This interface provides methods for manipulating Schedule objects.
 *
 * @author Georgian Vladutu
 */
public interface ScheduleManagementService {

    /**
     * Saves the schedule into the repository.
     *
     * @param saveScheduleDto schedule to be saved
     * @throws ServiceEntityAlreadyExistsException if the schedule already exists
     * @throws ServiceForeignKeyNotFoundException  if any of the schedule components are not found
     * @throws ServiceEntityNotFoundException      if the professor does not teach the subject to the group
     */
    void save(SaveScheduleDto saveScheduleDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException, ServiceEntityNotFoundException;

    /**
     * Returns all the schedules.
     *
     * @return list of PreviewScheduleDto
     */
    List<PreviewScheduleDto> getAll();

    /**
     * Returns all the schedules for the group whose id is groupId.
     *
     * @param groupId id of the group
     * @return list of PreviewScheduleDto
     */
    List<PreviewScheduleDto> getByGroup(Integer groupId);

    /**
     * Returns the schedule whose id is id.
     *
     * @param id id of the schedule
     * @return PreviewScheduleDto
     * @throws ServiceEntityNotFoundException if the schedule is not found
     */
    PreviewScheduleDto getById(Integer id) throws ServiceEntityNotFoundException;

    /**
     * Updates the schedule whose id is id.
     *
     * @param updateScheduleDto schedule to be updated
     * @param id                id of the schedule
     * @throws ServiceEntityNotFoundException     if the schedule is not found or the professor does not teach the subject to that group
     * @throws ServiceForeignKeyNotFoundException if any of the schedule components are not found
     */
    void update(UpdateScheduleDto updateScheduleDto, Integer id) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException;

    /**
     * Deletes the schedule whose id is id.
     *
     * @param id id of the schedule
     * @throws ServiceEntityNotFoundException if the schedule is not found
     */
    void delete(Integer id) throws ServiceEntityNotFoundException;
}
