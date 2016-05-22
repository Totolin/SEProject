package ro.ucv.ace.dto.professor;

import lombok.Getter;
import lombok.Setter;
import ro.ucv.ace.dto.department.DepartmentDto;

/**
 * Created by ctotolin on 22-May-16.
 */
@Getter
@Setter
public class StudentGradeDto {

    private int studentId;

    private int subjectId;

    private int grade;

}
