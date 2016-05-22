package ro.ucv.ace.dto.professor;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ctotolin on 22-May-16.
 */
@Getter
@Setter
public class SaveStudentGradeDto {

    private int studentId;

    private int subjectId;

    private int grade;

}
