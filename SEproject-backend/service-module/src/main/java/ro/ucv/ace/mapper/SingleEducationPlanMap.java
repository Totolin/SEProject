package ro.ucv.ace.mapper;

import org.modelmapper.PropertyMap;
import ro.ucv.ace.dto.educationPlan.SingleEducationPlanDto;
import ro.ucv.ace.model.EducationPlan;

/**
 * Created by Geo on 22.05.2016.
 */
public class SingleEducationPlanMap extends PropertyMap<SingleEducationPlanDto, EducationPlan> {

    @Override
    protected void configure() {
        map().setId(0);
        map().getGroup().setId(source.getGroupId());
        map().getSubject().setId(source.getSubjectId());
    }
}
