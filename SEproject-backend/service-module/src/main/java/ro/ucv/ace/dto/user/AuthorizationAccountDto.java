package ro.ucv.ace.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a data transfer object.
 *
 * @author Cristian Totolin
 */
@Getter
@Setter
public class AuthorizationAccountDto {

    private String username;

    private String state;

    @JsonIgnore
    private String password;

    private String type;

    private String authorization;
}
