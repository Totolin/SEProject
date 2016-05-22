package ro.ucv.ace.dto.educationPlan;

import lombok.Getter;
import lombok.Setter;
import ro.ucv.ace.dto.group.PreviewGroupDto;
import ro.ucv.ace.dto.subject.PreviewSubjectDto;

/**
 * Created by Geo on 22.05.2016.
 */
@Getter
@Setter
public class PreviewEducationPlanDto {

    private int id;

    private PreviewGroupDto group;

    private PreviewSubjectDto subject;
}
