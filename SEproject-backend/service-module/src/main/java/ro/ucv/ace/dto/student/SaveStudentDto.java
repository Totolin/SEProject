package ro.ucv.ace.dto.student;

import lombok.Getter;
import lombok.Setter;
import ro.ucv.ace.validation.StudentSubgroup;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Geo on 14.05.2016.
 */
@Getter
@Setter
public class SaveStudentDto {

    @Pattern(regexp = "\\b[1-8]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)\\d{4}\\b", message = "{student.ssn}")
    private String ssn;

    @Size(min = 5, max = 30)
    private String firstName;

    @Size(min = 5, max = 30)
    private String lastName;

    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "{student.email}")
    private String email;

    @Size(min = 10, max = 10)
    private String phoneNumber;

    @Size(min = 5, max = 50)
    private String address;

    @Min(1)
    private int groupId;

    @StudentSubgroup
    private String subgroup;
}
