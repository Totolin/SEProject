package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.ScheduleDao;
import ro.ucv.ace.dto.ScheduleDto;
import ro.ucv.ace.exception.DaoEntityAlreadyExistsException;
import ro.ucv.ace.exception.DaoForeignKeyNotFoundException;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;
import ro.ucv.ace.model.Schedule;
import ro.ucv.ace.service.ScheduleManagementService;

/**
 * Created by Geo on 06.05.2016.
 */
@Service
@Transactional(rollbackFor = ServiceForeignKeyNotFoundException.class)
public class ScheduleManagementServiceImpl implements ScheduleManagementService {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(ScheduleDto scheduleDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException {
        Schedule schedule = modelMapper.map(scheduleDto, Schedule.class);

        try {
            scheduleDao.save(schedule);
        } catch (DaoEntityAlreadyExistsException e) {
            throw new ServiceEntityAlreadyExistsException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        }
    }
}
