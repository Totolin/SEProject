package ro.ucv.ace.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Geo on 28.04.2016.
 */
@Getter
@Setter
public class StudentGrade {

    private int subjectId;

    private String subjectName;

    private int grade;

    private int credits;
}
