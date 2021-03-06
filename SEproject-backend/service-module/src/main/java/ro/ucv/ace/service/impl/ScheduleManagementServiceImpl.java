package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.EducationPlanDao;
import ro.ucv.ace.dao.ScheduleDao;
import ro.ucv.ace.dto.schedule.PreviewScheduleDto;
import ro.ucv.ace.dto.schedule.SaveScheduleDto;
import ro.ucv.ace.dto.schedule.UpdateScheduleDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.model.EducationPlan;
import ro.ucv.ace.model.Schedule;
import ro.ucv.ace.service.ScheduleManagementService;

import java.util.List;

/**
 * Created by Geo on 06.05.2016.
 */
@Service
@Transactional(rollbackFor = ServiceForeignKeyNotFoundException.class)
public class ScheduleManagementServiceImpl implements ScheduleManagementService {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private EducationPlanDao educationPlanDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(SaveScheduleDto saveScheduleDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException, ServiceEntityNotFoundException {
        Schedule schedule = modelMapper.map(saveScheduleDto, Schedule.class);

        try {
            EducationPlan educationPlan = educationPlanDao.findByGroupAndProfessorAndSubject(
                    saveScheduleDto.getGroupId(), saveScheduleDto.getProfessorId(), saveScheduleDto.getSubjectId());
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }

        try {
            scheduleDao.save(schedule);
        } catch (DaoEntityAlreadyExistsException e) {
            throw new ServiceEntityAlreadyExistsException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        }
    }

    @Override
    public List<PreviewScheduleDto> getAll() {
        List<Schedule> schedules = scheduleDao.findAll();

        return modelMapper.map(schedules, new TypeToken<List<PreviewScheduleDto>>() {
        }.getType());
    }

    @Override
    public List<PreviewScheduleDto> getByGroup(Integer groupId) {
        List<Schedule> schedules = scheduleDao.findByGroup(groupId);

        return modelMapper.map(schedules, new TypeToken<List<PreviewScheduleDto>>() {
        }.getType());
    }

    @Override
    public PreviewScheduleDto getById(Integer id) throws ServiceEntityNotFoundException {
        try {
            Schedule schedule = scheduleDao.findOne(id);

            return modelMapper.map(schedule, PreviewScheduleDto.class);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }

    @Override
    public void update(UpdateScheduleDto updateScheduleDto, Integer id) throws ServiceEntityNotFoundException, ServiceForeignKeyNotFoundException {
        Schedule schedule = modelMapper.map(updateScheduleDto, Schedule.class);

        try {
            EducationPlan educationPlan = educationPlanDao.findByGroupAndProfessorAndSubject(
                    updateScheduleDto.getGroupId(), updateScheduleDto.getProfessorId(), updateScheduleDto.getSubjectId());
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }

        try {
            scheduleDao.update(id, schedule);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        }
    }

    @Override
    public void delete(Integer id) throws ServiceEntityNotFoundException {
        try {
            scheduleDao.delete(id);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
