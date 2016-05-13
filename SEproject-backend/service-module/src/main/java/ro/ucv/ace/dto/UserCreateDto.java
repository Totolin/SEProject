package ro.ucv.ace.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ro.ucv.ace.validation.UserType;

/**
 * This is a data transfer object.
 *
 * @author Cristian Totolin
 */
@Getter
@Setter
public class UserCreateDto {

    private String username;

    private String password;

    @JsonIgnore
    private String state;

    @UserType
    private String type;
}
