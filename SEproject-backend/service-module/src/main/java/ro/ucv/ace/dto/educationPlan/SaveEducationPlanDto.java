package ro.ucv.ace.dto.educationPlan;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Geo on 22.05.2016.
 */
@Getter
@Setter
public class SaveEducationPlanDto {

    @Min(1)
    private int groupId;

    @Size(min = 1, max = 100)
    private List<Integer> subjectIds;
}
