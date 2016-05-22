package ro.ucv.ace.dto.professor;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by ctotolin on 22-May-16.
 */
@Getter
@Setter
public class SaveStudentGradeDto {

    @Min(1)
    private int studentId;

    @Min(1)
    private int subjectId;

    @Min(1)
    @Max(10)
    private int grade;

}
