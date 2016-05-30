package ro.ucv.ace.mapper;

import org.modelmapper.PropertyMap;
import ro.ucv.ace.dto.educationPlan.SaveEducationPlanDto;
import ro.ucv.ace.model.EducationPlan;

/**
 * Created by Geo on 22.05.2016.
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
