package ro.ucv.ace.dto.professor;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * This class represents a data transfer object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class SaveProfessorDto {

    @Pattern(regexp = "\\b[1-8]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)\\d{4}\\b", message = "{ssn.message}")
    private String ssn;

    @Size(min = 5, max = 30)
    private String firstName;

    @Size(min = 5, max = 30)
    private String lastName;

    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "{email.message}")
    private String email;

    @Size(min = 10, max = 10)
    @Pattern(regexp = "[0-9]+", message = "{phoneNumber.message}")
    private String phoneNumber;

    @Size(min = 5, max = 50)
    private String address;

    @Size(min = 3, max = 20)
    private String position;

    @Size(min = 1, max = 20)
    private String office;

    @Min(1)
    private int departmentId;

}
