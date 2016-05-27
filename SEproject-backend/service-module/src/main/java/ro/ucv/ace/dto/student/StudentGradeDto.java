package ro.ucv.ace.dto.student;

import lombok.Getter;
import lombok.Setter;

/**
 * This is a data transfer object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class StudentGradeDto {

    private int subjectId;

    private String subjectName;

    private Integer grade;

    private int credits;
}
