package ro.ucv.ace.dto.professor;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by Geo on 30.05.2016.
 */
@Getter
@Setter
public class UpdateStudentGradeDto {

    @Min(1)
    private int studentId;

    @Min(1)
    private int subjectId;

    @Min(1)
    @Max(10)
    private int grade;
}
