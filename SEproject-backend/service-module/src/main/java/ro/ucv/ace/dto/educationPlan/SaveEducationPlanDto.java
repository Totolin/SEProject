package ro.ucv.ace.dto.educationPlan;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * Created by Geo on 22.05.2016.
 */
@Getter
@Setter
public class SaveEducationPlanDto {

    @Min(1)
    private int groupId;

    @Min(1)
    private int subjectId;

    @Min(1)
    private int professorId;

    @Size(min = 3, max = 500)
    private String evaluationMethod;
}
