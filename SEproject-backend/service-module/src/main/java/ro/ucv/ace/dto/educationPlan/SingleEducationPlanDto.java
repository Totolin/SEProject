package ro.ucv.ace.dto.educationPlan;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Geo on 22.05.2016.
 */
@Getter
@Setter
public class SingleEducationPlanDto {

    private int groupId;

    private int subjectId;

    public SingleEducationPlanDto() {
    }

    public SingleEducationPlanDto(int groupId, int subjectId) {
        this.groupId = groupId;
        this.subjectId = subjectId;
    }
}
