package ro.ucv.ace.mapper;

import org.modelmapper.PropertyMap;
import ro.ucv.ace.dto.educationPlan.SaveEducationPlanDto;
import ro.ucv.ace.model.EducationPlan;

/**
 * This class is used by ModelMapper to map from SaveEducationPlanDto class to EducationPlan class.
 *
 * @author Georgian Vladutu
 */
public class SingleEducationPlanMap extends PropertyMap<SaveEducationPlanDto, EducationPlan> {

    @Override
    protected void configure() {
        map().setId(0);
        map().getGroup().setId(source.getGroupId());
        map().getSubject().setId(source.getSubjectId());
        map().getProfessor().setId(source.getProfessorId());
        map().setEvaluationMethod(source.getEvaluationMethod());
    }
}
