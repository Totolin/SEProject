package ro.ucv.ace.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * This is a data transfer object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class UserLoginDto {

    @Size(min = 5, max = 30)
    private String username;

    @Size(min = 6, max = 20)
    private String password;
}
