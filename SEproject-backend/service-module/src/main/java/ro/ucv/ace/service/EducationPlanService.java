package ro.ucv.ace.service;

import ro.ucv.ace.dto.educationPlan.PreviewEducationPlanDto;
import ro.ucv.ace.dto.educationPlan.SaveEducationPlanDto;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;

import java.util.List;

/**
 * Created by Geo on 22.05.2016.
 */
public interface EducationPlanService {

    void save(SaveEducationPlanDto saveEducationPlanDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException;

    List<PreviewEducationPlanDto> getAll();

    void delete(Integer id) throws ServiceEntityNotFoundException;
}
