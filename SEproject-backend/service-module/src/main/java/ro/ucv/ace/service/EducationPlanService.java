package ro.ucv.ace.service;

import ro.ucv.ace.dto.educationPlan.PreviewEducationPlanDto;
import ro.ucv.ace.dto.educationPlan.SaveEducationPlanDto;
import ro.ucv.ace.exception.ServiceEntityAlreadyExistsException;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.exception.ServiceForeignKeyNotFoundException;

import java.util.List;

/**
 * This interface provides methods for manipulating EducationPlan objects.
 *
 * @author Cristian Totolin
 */
public interface EducationPlanService {

    /**
     * Saves the educationPlan into the reposiroy.
     *
     * @param saveEducationPlanDto educationPlan to be saved
     * @throws ServiceEntityAlreadyExistsException if the educationPlan already exists
     * @throws ServiceForeignKeyNotFoundException  if any of the educationPlan components are not found
     */
    void save(SaveEducationPlanDto saveEducationPlanDto) throws ServiceEntityAlreadyExistsException, ServiceForeignKeyNotFoundException;

    /**
     * Returns all EducationPlan entities.
     *
     * @return list of PreviewEducationPlanDto
     */
    List<PreviewEducationPlanDto> getAll();

    /**
     * Deletes the educationPlan whose id is id.
     *
     * @param id id of the educationPlan
     * @throws ServiceEntityNotFoundException id the educationPlan is not found
     */
    void delete(Integer id) throws ServiceEntityNotFoundException;
}
