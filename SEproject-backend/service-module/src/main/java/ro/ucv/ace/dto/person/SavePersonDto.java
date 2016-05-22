package ro.ucv.ace.dto.person;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Geo on 22.05.2016.
 */
@Getter
@Setter
public class SavePersonDto{

    @Pattern(regexp = "\\b[1-8]\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])(0[1-9]|[1-4]\\d|5[0-2]|99)\\d{4}\\b", message = "{ssn.message}")
    protected String ssn;

    @Size(min = 5, max = 30)
    protected String firstName;

    @Size(min = 5, max = 30)
    protected String lastName;

    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "{email.message}")
    protected String email;

    @Size(min = 10, max = 10)
    @Pattern(regexp = "[0-9]+", message = "{phoneNumber.message}")
    protected String phoneNumber;

    @Size(min = 5, max = 50)
    protected String address;

}
