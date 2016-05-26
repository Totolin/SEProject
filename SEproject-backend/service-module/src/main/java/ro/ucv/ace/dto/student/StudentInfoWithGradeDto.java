package ro.ucv.ace.dto.student;

import lombok.Getter;
import lombok.Setter;
import ro.ucv.ace.dto.group.PreviewGroupDto;

/**
 * Created by Geo on 26.05.2016.
 */
@Getter
@Setter
public class StudentInfoWithGradeDto {

    private int id;

    private String ssn;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String address;

    private PreviewGroupDto group;

    private String subgroup;

    private boolean paid;

    private Integer grade;
}
