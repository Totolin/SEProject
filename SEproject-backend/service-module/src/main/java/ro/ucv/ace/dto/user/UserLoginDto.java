package ro.ucv.ace.dto.user;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * This class represents a data transfer object.
 *
 * @author Cristian Totolin
 */
@Getter
@Setter
public class UserLoginDto {

    @Size(min = 5, max = 30)
    private String username;

    @Size(min = 5, max = 20)
    private String password;
}
