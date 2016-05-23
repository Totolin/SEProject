package ro.ucv.ace.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import ro.ucv.ace.dto.person.SavePersonDto;
import ro.ucv.ace.validation.UserType;

import javax.validation.constraints.Size;

/**
 * This is a data transfer object.
 *
 * @author Cristian Totolin
 */
@Getter
@Setter
public class UserCreateDto extends SavePersonDto {

    @Size(min = 5, max = 30)
    private String username;

    @Size(min = 5, max = 20)
    private String password;

    @JsonIgnore
    private String state;

    @UserType
    private String type;
}
