package ro.ucv.ace.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ucv.ace.dao.EducationPlanDao;
import ro.ucv.ace.dto.educationPlan.PreviewEducationPlanDto;
import ro.ucv.ace.dto.educationPlan.SaveEducationPlanDto;
import ro.ucv.ace.exception.*;
import ro.ucv.ace.model.EducationPlan;
import ro.ucv.ace.service.EducationPlanService;

import java.util.List;

/**
 * Created by Geo on 22.05.2016.
 */
@Service
@Transactional(rollbackFor = ServiceForeignKeyNotFoundException.class)
public class EducationPlanServiceImpl implements EducationPlanService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EducationPlanDao educationPlanDao;

    @Override
    public void save(SaveEducationPlanDto saveEducationPlanDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException {
        EducationPlan educationPlan = modelMapper.map(saveEducationPlanDto, EducationPlan.class);

        try {
            educationPlanDao.save(educationPlan);
        } catch (DaoEntityAlreadyExistsException e) {
            throw new ServiceEntityAlreadyExistsException(e);
        } catch (DaoForeignKeyNotFoundException e) {
            throw new ServiceForeignKeyNotFoundException(e);
        }
    }

    @Override
    public List<PreviewEducationPlanDto> getAll() {
        List<EducationPlan> educationPlans = educationPlanDao.findAll();

        return modelMapper.map(educationPlans, new TypeToken<List<PreviewEducationPlanDto>>() {
        }.getType());
    }

    @Override
    public void delete(Integer id) throws ServiceEntityNotFoundException {
        try {
            educationPlanDao.delete(id);
        } catch (DaoEntityNotFoundException e) {
            throw new ServiceEntityNotFoundException(e);
        }
    }
}
