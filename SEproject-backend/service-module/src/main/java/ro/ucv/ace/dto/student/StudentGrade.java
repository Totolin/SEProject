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
public class StudentGrade {

    private int subjectId;

    private String subjectName;

    private int grade;

    private int credits;
}
