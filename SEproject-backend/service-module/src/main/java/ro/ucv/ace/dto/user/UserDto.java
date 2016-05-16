package ro.ucv.ace.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * This is a data transfer object.
 *
 * @author Cristian Totolin
 */
@Getter
@Setter
public class UserDto {

    private int id;

    private String username;

    @JsonIgnore
    private String password;

    private String state;

    private String type;

    private String authorization;
}
