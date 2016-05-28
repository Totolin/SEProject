package ro.ucv.ace.dto.subject;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * Created by Geo on 28.05.2016.
 */
@Getter
@Setter
public class SaveProfessorSubjectDto {

    @Min(1)
    private int groupId;

    @Min(1)
    private int professorId;

    @Min(1)
    private int subjectId;

    @Size(min = 5, max = 5000)
    private String evaluationMethod;
}
