package ro.ucv.ace.dto.professor;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * This class represents a data transfer object.
 *
 * @author Georgian Vladutu
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
