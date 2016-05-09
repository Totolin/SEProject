package ro.ucv.ace.service;

import ro.ucv.ace.dto.ScheduleDto;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;

/**
 * Created by Geo on 06.05.2016.
 */
public interface ScheduleManagementService {

    void save(ScheduleDto scheduleDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException;
}
